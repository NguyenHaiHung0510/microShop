package com.microshop.controller;

import com.microshop.dao.GameSteamDAO;
import com.microshop.dao.BaiVietGioiThieuDAO;
import com.microshop.dao.GameTaiKhoanSteamDAO; // SỬA: Thêm DAO
import com.microshop.model.GameSteam;
import com.microshop.model.BaiVietGioiThieu;
import com.microshop.model.TaiKhoanSteam; // SỬA: Thêm Model

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/shop/steam", "/shop/steam/detail"})
public class SteamGameServlet extends HttpServlet {

    private GameSteamDAO gameSteamDAO;
    private BaiVietGioiThieuDAO baiVietDAO;
    private GameTaiKhoanSteamDAO gameTkSteamDAO; // SỬA: Thêm DAO

    @Override
    public void init() throws ServletException {
        gameSteamDAO = new GameSteamDAO();
        baiVietDAO = new BaiVietGioiThieuDAO();
        gameTkSteamDAO = new GameTaiKhoanSteamDAO(); // SỬA: Khởi tạo DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        try {
            if (path.equals("/shop/steam/detail")) {
                handleDetail(request, response);
            } else {
                handleList(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi truy vấn cơ sở dữ liệu");
        }
    }

    // ------------------- Hiển thị danh sách (Giữ nguyên) -------------------
    private void handleList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<GameSteam> listSteam = gameSteamDAO.fastGetAll();

        int pageSize = 12;
        int currentPage = 1;

        String pageParam = request.getParameter("page");
        if (pageParam == null || pageParam.trim().isEmpty()) {
            pageParam = "1";
        }

        try {
            currentPage = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            return;
        }

        int totalItems = listSteam.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);
        List<GameSteam> pageList = listSteam.subList(start, end);

        request.setAttribute("listSteam", pageList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher rd = request.getRequestDispatcher("/steam_games.jsp");
        rd.forward(request, response);
    }

    // ------------------- Hiển thị chi tiết (Sửa) -------------------
    private void handleDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            return;
        }

        GameSteam gameSteam = gameSteamDAO.getById(id);
        if (gameSteam == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game không tồn tại");
            return;
        }

        // --- SỬA: LOGIC KIỂM TRA HẾT HÀNG ---
        boolean isSoldOut = false;
        // 1. Lấy danh sách tài khoản đã sắp xếp (cân bằng tải)
        List<TaiKhoanSteam> accounts = gameTkSteamDAO.getAllTaiKhoanByMaGameSteamSorted(id);

        if (accounts == null || accounts.isEmpty()) {
            // 2. Nếu không có tài khoản nào chứa game này -> Hết hàng
            isSoldOut = true;
        } else {
            // 3. Lấy tài khoản còn nhiều slot nhất (ở vị trí 0)
            TaiKhoanSteam bestAccount = accounts.get(0);
            if (bestAccount.getSoSlotDaBan() >= bestAccount.getTongSoSlot()) {
                // 4. Nếu tài khoản tốt nhất cũng đã đầy -> Hết hàng
                isSoldOut = true;
            }
        }
        // 5. Gửi cờ (flag) trạng thái hết hàng sang JSP
        request.setAttribute("isSoldOut", isSoldOut);
        // --- KẾT THÚC SỬA ---

        List<BaiVietGioiThieu> listBaiViet = baiVietDAO.getByMaGameSteam(id);
        if (listBaiViet == null) {
            // Không cần báo lỗi, có thể game không có bài viết
        }

        BaiVietGioiThieu baiVietCauHinh = null;
        BaiVietGioiThieu baiVietGame = null;
        BaiVietGioiThieu baiVietThongTin = null;
        if (listBaiViet != null) {
            for (BaiVietGioiThieu baiViet : listBaiViet) {
                if (baiViet.getTieuDeBaiViet() != null
                        && baiViet.getTieuDeBaiViet().trim().equalsIgnoreCase("Cấu hình game:")) {
                    baiVietCauHinh = baiViet;
                } 
                else if (baiViet.getTieuDeBaiViet() != null
                        && baiViet.getTieuDeBaiViet().trim().equalsIgnoreCase("Thông tin game:")){
                    baiVietThongTin = baiViet;
                }
                else if (baiViet.getTieuDeBaiViet() != null) {
                    baiVietGame = baiViet;
                }
            }
        }

        List<GameSteam> listSteamDetail = gameSteamDAO.fastGetAll();

        request.setAttribute("gameSteam", gameSteam);
        request.setAttribute("baiVietCauHinh", baiVietCauHinh);
        request.setAttribute("baiVietGame", baiVietGame);
        request.setAttribute("baiVietThongTin", baiVietThongTin);
        request.setAttribute("listSteamDetail", listSteamDetail);

        RequestDispatcher rd = request.getRequestDispatcher("/steam_game_detail.jsp");
        rd.forward(request, response);
    }
}
