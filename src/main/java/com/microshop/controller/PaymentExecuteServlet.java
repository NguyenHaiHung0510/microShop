package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.DonHangSlotSteamDAO;
import com.microshop.dao.GameSteamDAO;
import com.microshop.dao.GameTaiKhoanSteamDAO;
import com.microshop.dao.HangThanhVienDAO;
import com.microshop.dao.NguoiDungDAO; // SỬA: Thêm DAO người dùng
import com.microshop.dao.TaiKhoanDAO;
import com.microshop.dao.TaiKhoanFreeFireDAO;
import com.microshop.dao.TaiKhoanLienQuanDAO;
import com.microshop.dao.TaiKhoanRiotDAO;
import com.microshop.dao.TaiKhoanSteamDAO;
import com.microshop.model.NguoiDung;
import com.microshop.model.TaiKhoan;
import com.microshop.model.DonHang;
import com.microshop.model.DonHangSlotSteam;
import com.microshop.model.GameSteam;
import com.microshop.model.HangThanhVien;
import com.microshop.model.TaiKhoanSteam;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PaymentExecuteServlet", urlPatterns = {"/payment/execute"})
public class PaymentExecuteServlet extends HttpServlet {

    private final TaiKhoanLienQuanDAO lienQuanDAO = new TaiKhoanLienQuanDAO();
    private final TaiKhoanFreeFireDAO freeFireDAO = new TaiKhoanFreeFireDAO();
    private final TaiKhoanRiotDAO riotDAO = new TaiKhoanRiotDAO();
    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private final DonHangDAO donhangDAO = new DonHangDAO();
    private final DonHangSlotSteamDAO donhangsteamDAO = new DonHangSlotSteamDAO();
    private final TaiKhoanSteamDAO tksteamDAO = new TaiKhoanSteamDAO();
    private final GameTaiKhoanSteamDAO gametksteamDAO = new GameTaiKhoanSteamDAO();
    private final GameSteamDAO gamesteamDAO = new GameSteamDAO();
    private final HangThanhVienDAO hangDao = new HangThanhVienDAO();
    private final NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(); // SỬA: Thêm DAO
    private final int NGUONG_HUY_PHUT = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        NguoiDung userCu = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        if (userCu == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");
        String typeParam = request.getParameter("type");
        String redirectURL;

        if (idParam == null || typeParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu thông tin sản phẩm.");
            return;
        }

        try {
            // =================================================================
            // SỬA LỖI SESSION: Làm mới người dùng từ CSDL
            // =================================================================
            NguoiDung user = nguoiDungDAO.getById(userCu.getMaNguoiDung());
            if (user == null) { // Trường hợp hy hữu: tài khoản bị xóa
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            // Cập nhật lại session ngay lập tức
            session.setAttribute("user", user);
            // =================================================================

            int maSanPham = Integer.parseInt(idParam);
            Object sanPham = null;
            DonHang donHangGame = null;
            DonHangSlotSteam donHangSteam = null;

            int type; // 1=Game, 2=Steam
            int maGame = -1;

            HangThanhVien htv = hangDao.getById(user.getMaHangThanhVien()); // Dùng user MỚI
            BigDecimal chietKhauPhanTram = htv.getChietKhau();

            // 1. KIỂM TRA ĐƠN HÀNG TỒN TẠI
            if ("steam".equals(typeParam)) {
                type = 2;
                maGame = maSanPham;
                redirectURL = request.getContextPath() + "/shop/steam/detail?id=" + maSanPham;
                donHangSteam = donhangsteamDAO.getByMaGameVaNguoiDungChoThanhToan(maGame, user.getMaNguoiDung());
                if (donHangSteam != null) {
                    sanPham = tksteamDAO.getById(donHangSteam.getMaTaiKhoanSteam());
                }
            } else { // Game Account
                type = 1;
                redirectURL = request.getContextPath() + "/shop/game/detail?id=" + maSanPham + "&category=" + typeParam;
                donHangGame = donhangDAO.getByMaTaiKhoanVaNguoiDung(maSanPham, user.getMaNguoiDung());
                if (donHangGame != null) {
                    sanPham = getTaiKhoanGame(maSanPham, typeParam);
                }
            }

            // 2. XỬ LÝ KỊCH BẢN
            // --- Kịch bản 1: Đã được Admin duyệt (DA_HOAN_THANH) ---
            if (donHangGame != null && "DA_HOAN_THANH".equals(donHangGame.getTrangThai())) {
                request.setAttribute("paymentSuccessStatus", true);
                request.setAttribute("transactionMethod", "ADMIN_APPROVED");
                request.setAttribute("transactionId", "TXN-" + donHangGame.getMaDonHang());
                request.getRequestDispatcher("/payment_success.jsp").forward(request, response);
                return;
            }

            // --- Kịch bản 2: Đang chờ duyệt (CHO_THANH_TOAN) ---
            if ((donHangGame != null && "CHO_THANH_TOAN".equals(donHangGame.getTrangThai()))
                    || (donHangSteam != null && "CHO_THANH_TOAN".equals(donHangSteam.getTrangThai()))) {

                LocalDateTime thoiGianTao = (donHangGame != null) ? donHangGame.getThoiGianTao() : donHangSteam.getThoiGianTao();
                LocalDateTime thoiGianHetHan = thoiGianTao.plusMinutes(NGUONG_HUY_PHUT);
                Duration remaining = Duration.between(LocalDateTime.now(), thoiGianHetHan);
                long thoiGianConLaiGiay = remaining.getSeconds();

                if (thoiGianConLaiGiay <= 0) {
                    response.sendRedirect(redirectURL + "&status=expired");
                    return;
                }

                BigDecimal giaDaLuuTrongDon = (donHangGame != null) ? donHangGame.getGiaMua() : donHangSteam.getGiaMua();
                BigDecimal giaBanGoc;

                if (type == 1) {
                    giaBanGoc = ((TaiKhoan) sanPham).getGiaBan();
                } else {
                    GameSteam game = gamesteamDAO.getById(maGame);
                    giaBanGoc = game.getGiaBan();
                }
                BigDecimal tienChietKhau = giaBanGoc.subtract(giaDaLuuTrongDon);

                request.setAttribute("sanPhamThanhToan", sanPham);
                request.setAttribute("isWaiting", true);
                request.setAttribute("thoiGianConLai", thoiGianConLaiGiay);
                request.setAttribute("type", type);
                request.setAttribute("maGame", maGame);
                request.setAttribute("tenHang", htv.getTenHang());
                request.setAttribute("giaBanDau", giaBanGoc);
                request.setAttribute("tienChietKhau", tienChietKhau);
                request.setAttribute("giaCuoiCung", giaDaLuuTrongDon);

                request.getRequestDispatcher("/checkout.jsp").forward(request, response);
                return;
            }

            // --- Kịch bản 3: Lần đầu vào mua (Hoặc mua lại Steam) ---
            if (sanPham == null) {
                if (type == 2) {
                    List<TaiKhoanSteam> tk_chua_game = gametksteamDAO.getAllTaiKhoanByMaGameSteamSorted(maSanPham);
                    if (tk_chua_game.isEmpty()) {
                        response.sendRedirect(redirectURL + "&status=no_accounts");
                        return;
                    }
                    sanPham = tk_chua_game.get(0);
                    boolean hetSlot = true;
                    for (TaiKhoanSteam tk : tk_chua_game) {
                        if (tk.getSoSlotDaBan() < tk.getTongSoSlot()) {
                            hetSlot = false;
                            break;
                        }
                    }
                    if (hetSlot) {
                        response.sendRedirect(redirectURL + "&status=sold_out");
                        return;
                    }
                } else {
                    sanPham = getTaiKhoanGame(maSanPham, typeParam);
                }
            }

            if (sanPham == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại.");
                return;
            }

            if (type == 1 && "DA_BAN".equals(((TaiKhoan) sanPham).getTrangThai())) {
                response.sendRedirect(redirectURL + "&status=sold_out");
                return;
            }

            BigDecimal giaBan;
            if (type == 2) {
                GameSteam game = gamesteamDAO.getById(maSanPham);
                giaBan = game.getGiaBan();
            } else {
                giaBan = ((TaiKhoan) sanPham).getGiaBan();
            }

            BigDecimal tienChietKhau = giaBan.multiply(chietKhauPhanTram); // Tính chiết khấu
            BigDecimal giaCuoiCung = giaBan.subtract(tienChietKhau);

            request.setAttribute("sanPhamThanhToan", sanPham);
            request.setAttribute("isWaiting", false);
            request.setAttribute("thoiGianConLai", NGUONG_HUY_PHUT * 60);
            request.setAttribute("type", type);
            request.setAttribute("maGame", maGame);
            request.setAttribute("tenHang", htv.getTenHang());
            request.setAttribute("giaBanDau", giaBan);
            request.setAttribute("tienChietKhau", tienChietKhau);
            request.setAttribute("giaCuoiCung", giaCuoiCung);

            request.getRequestDispatcher("/checkout.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
        } catch (SQLException ex) {
            Logger.getLogger(PaymentExecuteServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi cơ sở dữ liệu.");
        }
    }

    private TaiKhoan getTaiKhoanGame(int maSanPham, String type) throws SQLException {
        if ("1".equals(type) || "game".equals(type)) {
            TaiKhoan tk = taiKhoanDAO.getById(maSanPham);
            if (tk == null) {
                return null;
            }
            int maDanhMuc = tk.getMaDanhMuc();
            if (maDanhMuc == 1) {
                return freeFireDAO.getById(maSanPham);
            }
            if (maDanhMuc == 2) {
                return lienQuanDAO.getById(maSanPham);
            }
            if (maDanhMuc == 3) {
                return riotDAO.getById(maSanPham);
            }
            return tk;
        }
        switch (type.toLowerCase()) {
            case "lienquan":
                return lienQuanDAO.getById(maSanPham);
            case "freefire":
                return freeFireDAO.getById(maSanPham);
            case "riot":
                return riotDAO.getById(maSanPham);
            default:
                return null;
        }
    }
}
