package com.microshop.controller;

import com.microshop.context.DBContext;
import com.microshop.dao.DonHangDAO;
import com.microshop.dao.DonHangSlotSteamDAO;
import com.microshop.dao.NguoiDungDAO;
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
    private NguoiDungDAO nguoiDungDAO;

    @Override
    public void init() {
        donHangDAO = new DonHangDAO();
        donHangSlotSteamDAO = new DonHangSlotSteamDAO();
        taiKhoanDAO = new TaiKhoanDAO();
        taiKhoanSteamDAO = new TaiKhoanSteamDAO();
        nguoiDungDAO = new NguoiDungDAO();
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

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
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
                        // 1. Cập nhật đơn hàng
                        donHangDAO.updateTrangThai(id, "DA_HOAN_THANH", LocalDateTime.now());
                        // 2. Cập nhật trạng thái sản phẩm
                        taiKhoanDAO.updateTrangThai(donHang.getMaTaiKhoan(), "DA_BAN");
                        // 3. Cập nhật tổng chi tiêu và hạng thành viên cho người dùng
                        nguoiDungDAO.updateTongTienDaChi(donHang.getMaNguoiDung(), donHang.getGiaMua());
                        nguoiDungDAO.updateHangThanhVien(donHang.getMaNguoiDung());

                    } else if ("cancel".equals(status)) {
                        donHangDAO.updateTrangThai(id, "DA_HUY", null);
                    }
                }
            } else if ("steam".equals(type)) {
                DonHangSlotSteam donHangSteam = donHangSlotSteamDAO.getById(id);
                if (donHangSteam != null && "CHO_THANH_TOAN".equals(donHangSteam.getTrangThai())) {

                    if ("approve".equals(status)) {
                        // 1. Cập nhật đơn hàng
                        donHangSlotSteamDAO.updateTrangThai(id, "DA_HOAN_THANH", LocalDateTime.now());
                        // 2. Cập nhật tổng chi tiêu và hạng thành viên cho người dùng
                        nguoiDungDAO.updateTongTienDaChi(donHangSteam.getMaNguoiDung(), donHangSteam.getGiaMua());
                        nguoiDungDAO.updateHangThanhVien(donHangSteam.getMaNguoiDung());

                    } else if ("cancel".equals(status)) {
                        donHangSlotSteamDAO.updateTrangThai(id, "DA_HUY", null);
                        taiKhoanSteamDAO.updateSoSlotDaBan(donHangSteam.getMaTaiKhoanSteam(), -1);
                    }
                }
            }

            conn.commit();

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
