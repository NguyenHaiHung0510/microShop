package com.microshop.controller;

import com.microshop.context.DBContext;
import com.microshop.dao.DonHangDAO;
import com.microshop.dao.DonHangSlotSteamDAO;
import com.microshop.dao.NguoiDungDAO; // SỬA: Thêm DAO người dùng
import com.microshop.dao.TaiKhoanDAO;
import com.microshop.dao.TaiKhoanSteamDAO;
import com.microshop.model.DonHang;
import com.microshop.model.DonHangSlotSteam;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AdminOrderServlet", urlPatterns = {"/admin/orders", "/admin/order/update"})
public class AdminOrderServlet extends HttpServlet {

    private DonHangDAO donHangDAO;
    private DonHangSlotSteamDAO donHangSlotSteamDAO;
    private TaiKhoanDAO taiKhoanDAO;
    private TaiKhoanSteamDAO taiKhoanSteamDAO;
    private NguoiDungDAO nguoiDungDAO; // SỬA: Thêm NguoiDungDAO

    @Override
    public void init() {
        donHangDAO = new DonHangDAO();
        donHangSlotSteamDAO = new DonHangSlotSteamDAO();
        taiKhoanDAO = new TaiKhoanDAO();
        taiKhoanSteamDAO = new TaiKhoanSteamDAO();
        nguoiDungDAO = new NguoiDungDAO(); // SỬA: Khởi tạo
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/admin/orders")) {
            try {
                listOrders(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/admin/order/update")) {
            try {
                updateOrderStatus(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }

    // (Hàm listOrders giữ nguyên, không thay đổi)
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        // ... (Giữ nguyên code của bạn) ...
        int recordsPerPage = 10;
        int pageGame = 1;
        if (request.getParameter("pageGame") != null) {
            pageGame = Integer.parseInt(request.getParameter("pageGame"));
        }
        List<DonHang> listDonHang = donHangDAO.getAllPaginated(pageGame, recordsPerPage);
        int totalGameOrders = donHangDAO.getTotalCount();
        int pagesGame = (int) Math.ceil(totalGameOrders * 1.0 / recordsPerPage);
        int pageSteam = 1;
        if (request.getParameter("pageSteam") != null) {
            pageSteam = Integer.parseInt(request.getParameter("pageSteam"));
        }
        List<DonHangSlotSteam> listDonHangSteam = donHangSlotSteamDAO.getAllPaginated(pageSteam, recordsPerPage);
        int totalSteamOrders = donHangSlotSteamDAO.getTotalCount();
        int pagesSteam = (int) Math.ceil(totalSteamOrders * 1.0 / recordsPerPage);
        request.setAttribute("listDonHang", listDonHang);
        request.setAttribute("pagesGame", pagesGame);
        request.setAttribute("currentPageGame", pageGame);
        request.setAttribute("listDonHangSteam", listDonHangSteam);
        request.setAttribute("pagesSteam", pagesSteam);
        request.setAttribute("currentPageSteam", pageSteam);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manage_orders.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * SỬA: Nâng cấp toàn bộ hàm này để xử lý logic duyệt/hủy chính xác.
     */
    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status"); // "approve" (Duyệt) hoặc "cancel" (Hủy)

        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false);

            if ("game".equals(type)) {
                DonHang donHang = donHangDAO.getById(id);
                if (donHang != null && "CHO_THANH_TOAN".equals(donHang.getTrangThai())) {

                    if ("approve".equals(status)) {
                        // --- Logic chuyển từ PaymentSuccessServlet về đây ---
                        // 1. Cập nhật đơn hàng
                        donHangDAO.updateTrangThai(id, "DA_HOAN_THANH", LocalDateTime.now());
                        // 2. Cập nhật trạng thái sản phẩm
                        taiKhoanDAO.updateTrangThai(donHang.getMaTaiKhoan(), "DA_BAN");
                        // 3. Cập nhật tổng chi tiêu và hạng thành viên cho người dùng
                        nguoiDungDAO.updateTongTienDaChi(donHang.getMaNguoiDung(), donHang.getGiaMua());
                        nguoiDungDAO.updateHangThanhVien(donHang.getMaNguoiDung());
                        // --- Kết thúc logic mới ---

                    } else if ("cancel".equals(status)) {
                        donHangDAO.updateTrangThai(id, "DA_HUY", null);
                        // Tài khoản game không cần trả lại, vì nó vẫn là "DANG_BAN"
                        // (Logic này của bạn đã đúng)
                    }
                }
            } else if ("steam".equals(type)) {
                DonHangSlotSteam donHangSteam = donHangSlotSteamDAO.getById(id);
                if (donHangSteam != null && "CHO_THANH_TOAN".equals(donHangSteam.getTrangThai())) {

                    if ("approve".equals(status)) {
                        // --- Logic chuyển từ PaymentSuccessServlet về đây ---
                        // 1. Cập nhật đơn hàng
                        donHangSlotSteamDAO.updateTrangThai(id, "DA_HOAN_THANH", LocalDateTime.now());
                        // 2. Slot đã bị tăng tạm thời (SoSlotDaBan + 1) khi tạo đơn,
                        //    giờ đơn hàng hoàn thành nên ta không cần làm gì thêm.
                        // 3. Cập nhật tổng chi tiêu và hạng thành viên cho người dùng
                        nguoiDungDAO.updateTongTienDaChi(donHangSteam.getMaNguoiDung(), donHangSteam.getGiaMua());
                        nguoiDungDAO.updateHangThanhVien(donHangSteam.getMaNguoiDung());
                        // --- Kết thúc logic mới ---

                    } else if ("cancel".equals(status)) {
                        donHangSlotSteamDAO.updateTrangThai(id, "DA_HUY", null);
                        // HOÀN TRẢ SLOT
                        // Khi tạo đơn (PaymentInitiate), SoSlotDaBan đã +1
                        // Giờ hủy đơn, ta phải -1 để hoàn trả slot.
                        // (Logic này của bạn đã đúng)
                        taiKhoanSteamDAO.updateSoSlotDaBan(donHangSteam.getMaTaiKhoanSteam(), -1);
                    }
                }
            }

            conn.commit(); // Lưu tất cả thay đổi (cả đơn hàng, sản phẩm, và người dùng)

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            Logger.getLogger(AdminOrderServlet.class.getName()).log(Level.SEVERE, "Lỗi Transaction khi cập nhật đơn hàng", e);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }
}
