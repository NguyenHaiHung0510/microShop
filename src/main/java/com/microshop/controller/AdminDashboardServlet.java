package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.DonHangSlotSteamDAO;
import com.microshop.dao.NguoiDungDAO;
import com.microshop.dao.TaiKhoanDAO;
import com.microshop.model.DonHang;
import com.microshop.model.DonHangSlotSteam; // <-- SỬA: THÊM IMPORT
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminDashboardServlet", urlPatterns = {"/admin/", "/admin/dashboard"})
public class AdminDashboardServlet extends HttpServlet {

    private TaiKhoanDAO taiKhoanDAO;
    private NguoiDungDAO nguoiDungDAO;
    private DonHangDAO donHangDAO;
    private DonHangSlotSteamDAO donHangSlotSteamDAO;

    @Override
    public void init() {
        taiKhoanDAO = new TaiKhoanDAO();
        nguoiDungDAO = new NguoiDungDAO();
        donHangDAO = new DonHangDAO();
        donHangSlotSteamDAO = new DonHangSlotSteamDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Lấy các số liệu thống kê tổng quan (Giữ nguyên)
            int totalTaiKhoan = taiKhoanDAO.getTotalCount();
            int totalNguoiDung = nguoiDungDAO.getTotalCount();
            int totalDonHang = donHangDAO.getTotalCount() + donHangSlotSteamDAO.getTotalCount();
            int totalDonHangCho = donHangDAO.getCountByTrangThai("CHO_THANH_TOAN")
                    + donHangSlotSteamDAO.getCountByTrangThai("CHO_THANH_TOAN");

            request.setAttribute("totalTaiKhoan", totalTaiKhoan);
            request.setAttribute("totalNguoiDung", totalNguoiDung);
            request.setAttribute("totalDonHang", totalDonHang);
            request.setAttribute("totalDonHangCho", totalDonHangCho);

            int page = 1;
            int recordsPerPage = 10; // Sửa: Giảm số lượng bản ghi để hiển thị 2 bảng
            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }

            // 2. Lấy danh sách đơn hàng Game
            List<DonHang> listDonHang = donHangDAO.getAllPaginated(page, recordsPerPage);
            int recCount = donHangDAO.getTotalCount();
            int pageCount = (int) Math.ceil(recCount * 1.0 / recordsPerPage);

            request.setAttribute("listDonHang", listDonHang);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            // --- SỬA 3: Lấy thêm danh sách đơn hàng Steam ---
            // (Chúng ta dùng chung 1 biến 'page' cho cả 2 bảng cho đơn giản)
            List<DonHangSlotSteam> listDonHangSteam = donHangSlotSteamDAO.getAllPaginated(page, recordsPerPage);
            int recCountSteam = donHangSlotSteamDAO.getTotalCount();
            int pageCountSteam = (int) Math.ceil(recCountSteam * 1.0 / recordsPerPage);

            request.setAttribute("listDonHangSteam", listDonHangSteam);
            request.setAttribute("pageCountSteam", pageCountSteam);
            // (Dùng chung currentPage)
            // --- KẾT THÚC SỬA 3 ---

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
