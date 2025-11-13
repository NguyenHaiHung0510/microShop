package com.microshop.controller;

import com.microshop.dao.*;
import com.microshop.model.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/shop/game", "/shop/game/detail"})
public class TaiKhoanGameServlet extends HttpServlet {

    private final TaiKhoanLienQuanDAO lienQuanDAO = new TaiKhoanLienQuanDAO();
    private final TaiKhoanFreeFireDAO freeFireDAO = new TaiKhoanFreeFireDAO();
    private final TaiKhoanRiotDAO riotDAO = new TaiKhoanRiotDAO();
    private final AnhTaiKhoanDAO anhDAO = new AnhTaiKhoanDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();
        try {
            if (path.equals("/shop/game/detail")) {
                handleDetail(request, response);
            } else {
                handleList(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi truy vấn CSDL");
        }
    }

    // --------- Danh sách tài khoản theo category ---------
    private void handleList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String category = request.getParameter("category");
        if (category == null) category = "lienquan"; // mặc định

        List<? extends TaiKhoan> dsTaiKhoan = switch (category.toLowerCase()) {
            case "lienquan" -> lienQuanDAO.getByTrangThai("DANG_BAN");
            case "freefire" -> freeFireDAO.getByTrangThai("DANG_BAN");
            case "riot" -> riotDAO.getByTrangThai("DANG_BAN");
            default -> null;
        };

        int currentPage = 1;
        int pageSize = 4; // tạm thời là 1 trang 4 tk
        String pageParam = request.getParameter("page");
        if (pageParam == null || pageParam.trim().isEmpty())
        {
            pageParam = "1";
        }
        try{
            currentPage = Integer.parseInt(pageParam);
        }
        catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            return;
        }
        
        int totalItems = dsTaiKhoan.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);
        List<? extends TaiKhoan> pageList = dsTaiKhoan.subList(start, end);
        
        request.setAttribute("category", category);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("dsTaiKhoan", pageList);

        RequestDispatcher rd = request.getRequestDispatcher("/taikhoangame.jsp");
        rd.forward(request, response);
    }

    // --------- Chi tiết tài khoản ---------
    private void handleDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String idParam = request.getParameter("id");
        String category = request.getParameter("category");

        if (idParam == null || category == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id hoặc category.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
            return;
        }

        TaiKhoan taiKhoan = switch (category.toLowerCase()) {
            case "lienquan" -> lienQuanDAO.getById(id);
            case "freefire" -> freeFireDAO.getById(id);
            case "riot" -> riotDAO.getById(id);
            default -> null;
        };

        if (taiKhoan == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tài khoản không tồn tại.");
            return;
        }

        List<AnhTaiKhoan> dsAnh = anhDAO.getByMaTaiKhoan(id);

        request.setAttribute("category", category);
        request.setAttribute("taiKhoan", taiKhoan);
        request.setAttribute("dsAnh", dsAnh);

        RequestDispatcher rd = request.getRequestDispatcher("/taikhoangame_chitiet.jsp");
        rd.forward(request, response);
    }
}