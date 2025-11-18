package com.microshop.controller;

import com.microshop.context.DBContext;
import com.microshop.dao.DonHangDAO;
import com.microshop.dao.DonHangSlotSteamDAO;
import com.microshop.dao.GameTaiKhoanSteamDAO;
import com.microshop.dao.TaiKhoanDAO;
import com.microshop.dao.TaiKhoanSteamDAO;
import com.microshop.model.DonHang;
import com.microshop.model.DonHangSlotSteam;
import com.microshop.model.NguoiDung;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanSteam;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PaymentInitiateServlet", urlPatterns = {"/payment/initiate"})
public class PaymentInitiateServlet extends HttpServlet {

    private final DonHangDAO donhangDAO = new DonHangDAO();
    private final TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO();
    private final DonHangSlotSteamDAO donhangslotsteamDAO = new DonHangSlotSteamDAO();
    private final TaiKhoanSteamDAO taikhoansteamDAO = new TaiKhoanSteamDAO();
    private final GameTaiKhoanSteamDAO gametksteamDAO = new GameTaiKhoanSteamDAO();
    private final int NGUONG_HUY_PHUT = 3;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        NguoiDung user = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"error\": \"Vui lòng đăng nhập lại.\"}");
            return;
        }

        Connection conn = null;

        try {
            int maSanPham = Integer.parseInt(request.getParameter("maSanPham"));
            int maGame = Integer.parseInt(request.getParameter("maGame"));
            int type = Integer.parseInt(request.getParameter("type"));
            String giaBan = request.getParameter("giaBan");

            if (type == 1) {
                // --- XỬ LÝ TÀI KHOẢN GAME ---

                // SỬA: Dùng hàm mới, kiểm tra chính xác đơn hàng CHỜ của USER này
                DonHang dh = donhangDAO.getByMaTaiKhoanVaNguoiDungChoThanhToan(maSanPham, user.getMaNguoiDung());

                TaiKhoan tk = taikhoanDAO.getById(maSanPham);
                if (tk != null && "DA_BAN".equals(tk.getTrangThai())) {
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    out.print("{\"error\": \"Sản phẩm này đã được bán. Vui lòng tải lại trang.\"}");
                    return;
                }

                if (dh != null) {
                    // Đã có đơn hàng CHỜ, chỉ cần trả về thời gian còn lại
                    int maDonHang = dh.getMaDonHang();
                    LocalDateTime thoiGianTao = dh.getThoiGianTao();
                    LocalDateTime thoiGianHetHan = thoiGianTao.plusMinutes(NGUONG_HUY_PHUT);
                    Duration remaining = Duration.between(LocalDateTime.now(), thoiGianHetHan);
                    Long thoiGianConLaiGiay = remaining.getSeconds();
                    if (thoiGianConLaiGiay < 0) {
                        thoiGianConLaiGiay = 0L;
                    }

                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("{\"success\": true, \"maDonHang\": " + maDonHang + ", \"thoiGianConLai\": " + thoiGianConLaiGiay + "}");
                    return;
                }

                // SỬA: Kiểm tra xem có ai *khác* đang giữ đơn này không
                DonHang donHangCuaNguoiKhac = donhangDAO.getByMaTaiKhoanChoThanhToan(maSanPham);
                if (donHangCuaNguoiKhac != null) {
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    out.print("{\"error\": \"Sản phẩm này đang được người khác giao dịch.\"}");
                    return;
                }

                // Tạo đơn hàng mới
                DonHang donhang = new DonHang(null,
                        user.getMaNguoiDung(), maSanPham, new BigDecimal(giaBan),
                        null, "CHO_THANH_TOAN", null
                );
                int maDonHang = donhangDAO.insert(donhang);
                // Lưu mã đơn hàng vào session để quản lý
                List<Integer> listMaDonHang = (List<Integer>) session.getAttribute("maDonHangDangXuLy");
                if (listMaDonHang == null) {
                    listMaDonHang = new ArrayList<>();
                }
                listMaDonHang.add(maDonHang);
                session.setAttribute("maDonHangDangXuLy", listMaDonHang);

                long thoiGianConLaiGiay = NGUONG_HUY_PHUT * 60;
                response.setStatus(HttpServletResponse.SC_OK);
                out.print("{\"success\": true, \"maDonHang\": " + maDonHang + ", \"thoiGianConLai\": " + thoiGianConLaiGiay + "}");

            } else {
                // --- XỬ LÝ SLOT STEAM (Đã đúng ở bước trước) ---

                // 1. Kiểm tra xem user đã có đơn hàng chờ cho game này chưa
                DonHangSlotSteam donHangChoCuaUser = donhangslotsteamDAO.getByMaGameVaNguoiDungChoThanhToan(maGame, user.getMaNguoiDung());

                if (donHangChoCuaUser != null) {
                    // Nếu có, trả về thời gian còn lại
                    int maDonHang = donHangChoCuaUser.getMaDonHangSlot();
                    LocalDateTime thoiGianTao = donHangChoCuaUser.getThoiGianTao();
                    LocalDateTime thoiGianHetHan = thoiGianTao.plusMinutes(NGUONG_HUY_PHUT);
                    Duration remaining = Duration.between(LocalDateTime.now(), thoiGianHetHan);
                    Long thoiGianConLaiGiay = remaining.getSeconds();
                    if (thoiGianConLaiGiay < 0) {
                        thoiGianConLaiGiay = 0L;
                    }

                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("{\"success\": true, \"maDonHang\": " + maDonHang + ", \"thoiGianConLai\": " + thoiGianConLaiGiay + "}");
                    return;
                }

                // 2. Nếu không, bắt đầu logic Cân Bằng Tải
                List<TaiKhoanSteam> taiKhoanPhuHop = gametksteamDAO.getAllTaiKhoanByMaGameSteamSorted(maGame);

                TaiKhoanSteam taiKhoanDuocChon = null;
                boolean daTangSlotThanhCong = false;

                // Bắt đầu Transaction
                conn = DBContext.getConnection();
                conn.setAutoCommit(false);

                // 3. Duyệt qua các tài khoản (đã sắp xếp) để tìm slot
                for (TaiKhoanSteam tk : taiKhoanPhuHop) {
                    daTangSlotThanhCong = taikhoansteamDAO.updateSoSlotDaBan(conn, tk.getMaTaiKhoanSteam(), 1);
                    if (daTangSlotThanhCong) {
                        taiKhoanDuocChon = tk;
                        break;
                    }
                }

                if (taiKhoanDuocChon == null) {
                    conn.rollback();
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    out.print("{\"error\": \"Rất tiếc, game này đã tạm thời hết slot. Vui lòng quay lại sau.\"}");
                    return;
                }

                // 4. Tạo đơn hàng
                DonHangSlotSteam donhang = new DonHangSlotSteam(null,
                        user.getMaNguoiDung(), maGame, taiKhoanDuocChon.getMaTaiKhoanSteam(), new BigDecimal(giaBan),
                        null, "CHO_THANH_TOAN", null
                );
                int maDonHang = donhangslotsteamDAO.insert(donhang, conn);

                // 5. Commit Transaction
                conn.commit();
                // Lưu mã đơn hàng vào session để quản lý
                List<Integer> listMaDonHang = (List<Integer>) session.getAttribute("maDonHangSlotSteamDangXuLy");
                if (listMaDonHang == null) {
                    listMaDonHang = new ArrayList<>();
                }
                listMaDonHang.add(maDonHang);
                session.setAttribute("maDonHangSlotSteamDangXuLy", listMaDonHang);

                long thoiGianConLaiGiay = NGUONG_HUY_PHUT * 60;
                response.setStatus(HttpServletResponse.SC_OK);
                out.print("{\"success\": true, \"maDonHang\": " + maDonHang + ", \"thoiGianConLai\": " + thoiGianConLaiGiay + "}");
            }

        } catch (NumberFormatException e) {
            if (conn != null) try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Dữ liệu sản phẩm không hợp lệ.\"}");
        } catch (SQLException e) {
            if (conn != null) try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Lỗi cơ sở dữ liệu khi tạo đơn hàng.\"}");
            Logger.getLogger(PaymentInitiateServlet.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(PaymentInitiateServlet.class.getName()).log(Level.SEVERE, "Lỗi đóng connection", e);
                }
            }
        }
    }
}
