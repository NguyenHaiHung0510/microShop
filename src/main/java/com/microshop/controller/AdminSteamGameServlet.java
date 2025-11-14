package com.microshop.controller;

import com.microshop.context.DBContext;
import com.microshop.dao.BaiVietGioiThieuDAO;
import com.microshop.dao.GameSteamDAO;
import com.microshop.model.BaiVietGioiThieu;
import com.microshop.model.GameSteam;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminSteamGameServlet", urlPatterns = {
    "/admin/products/steam", // GET: Hiển thị danh sách
    "/admin/steam/add", // GET: Hiển thị form thêm mới
    "/admin/steam/edit", // GET: Hiển thị form sửa
    "/admin/steam/save", // POST: Xử lý lưu (thêm/sửa)
    "/admin/steam/delete" // POST: Xử lý xóa
})
public class AdminSteamGameServlet extends HttpServlet {

    private GameSteamDAO gameSteamDAO;
    private BaiVietGioiThieuDAO baiVietDAO;

    @Override
    public void init() {
        gameSteamDAO = new GameSteamDAO();
        baiVietDAO = new BaiVietGioiThieuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/admin/steam/add":
                    showNewForm(request, response);
                    break;
                case "/admin/steam/edit":
                    showEditForm(request, response);
                    break;
                default: // "/admin/products/steam"
                    listSteamGames(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/admin/steam/save":
                    saveSteamGame(request, response);
                    break;
                case "/admin/steam/delete":
                    deleteSteamGame(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // GET: /admin/products/steam
    private void listSteamGames(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int page = 1;
        int recordsPerPage = 15;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<GameSteam> listGames = gameSteamDAO.getAllPaginated(page, recordsPerPage);
        int noOfRecords = gameSteamDAO.getTotalCount();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listGames", listGames);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalRecords", noOfRecords);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manage_steam_games.jsp");
        dispatcher.forward(request, response);
    }

    // GET: /admin/steam/add
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("game", new GameSteam());
        request.setAttribute("baiViet1", new BaiVietGioiThieu());
        request.setAttribute("baiViet2", new BaiVietGioiThieu());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/form_steam_game.jsp");
        dispatcher.forward(request, response);
    }

    // GET: /admin/steam/edit?id=...
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GameSteam existingGame = gameSteamDAO.getById(id);
        List<BaiVietGioiThieu> baiViets = baiVietDAO.getByMaGameSteam(id);

        request.setAttribute("game", existingGame);
        // Đảm bảo luôn có 2 bài viết (kể cả là rỗng)
        request.setAttribute("baiViet1", baiViets.size() > 0 ? baiViets.get(0) : new BaiVietGioiThieu());
        request.setAttribute("baiViet2", baiViets.size() > 1 ? baiViets.get(1) : new BaiVietGioiThieu());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/form_steam_game.jsp");
        dispatcher.forward(request, response);
    }

    // POST: /admin/steam/delete
    private void deleteSteamGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gameSteamDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/admin/products/steam");
    }

    // POST: /admin/steam/save
    private void saveSteamGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false);

            // 1. Lấy dữ liệu GameSteam
            String maGameParam = request.getParameter("maGameSteam");
            Integer maGameSteam = (maGameParam == null || maGameParam.isEmpty()) ? null : Integer.parseInt(maGameParam);

            String tenGame = request.getParameter("tenGame");
            BigDecimal giaGoc = new BigDecimal(request.getParameter("giaGoc"));
            BigDecimal giaBan = new BigDecimal(request.getParameter("giaBan"));
            String moTaGame = request.getParameter("moTaGame");
            String idVideoTrailer = request.getParameter("idVideoTrailer");
            String duongDanAnh = request.getParameter("duongDanAnh");

            GameSteam game = new GameSteam();
            game.setTenGame(tenGame);
            game.setGiaGoc(giaGoc);
            game.setGiaBan(giaBan);
            game.setMoTaGame(moTaGame);
            game.setIdVideoTrailer(idVideoTrailer);
            game.setDuongDanAnh(duongDanAnh);

            Integer gameIdToUse;

            if (maGameSteam == null) {
                game.setLuotXem(0);
                gameIdToUse = gameSteamDAO.insert(game, conn); // Dùng hàm insert transaction-aware
            } else {
                game.setMaGameSteam(maGameSteam);
                gameSteamDAO.update(game, conn); // Dùng hàm update transaction-aware
                gameIdToUse = maGameSteam;
            }

            // 2. Xử lý Bài Viết 1
            String maBaiViet1Param = request.getParameter("maBaiViet1");
            Integer maBaiViet1 = (maBaiViet1Param == null || maBaiViet1Param.isEmpty() || "0".equals(maBaiViet1Param)) ? null : Integer.parseInt(maBaiViet1Param);
            String tieuDe1 = request.getParameter("tieuDe1");
            String noiDung1 = request.getParameter("noiDung1");

            BaiVietGioiThieu bv1 = new BaiVietGioiThieu();
            bv1.setMaGameSteam(gameIdToUse);
            bv1.setTieuDeBaiViet(tieuDe1);
            bv1.setNoiDung(noiDung1);

            if (maBaiViet1 == null) {
                baiVietDAO.insert(bv1, conn);
            } else {
                bv1.setMaBaiViet(maBaiViet1);
                baiVietDAO.update(bv1, conn);
            }

            // 3. Xử lý Bài Viết 2
            String maBaiViet2Param = request.getParameter("maBaiViet2");
            Integer maBaiViet2 = (maBaiViet2Param == null || maBaiViet2Param.isEmpty() || "0".equals(maBaiViet2Param)) ? null : Integer.parseInt(maBaiViet2Param);
            String tieuDe2 = request.getParameter("tieuDe2");
            String noiDung2 = request.getParameter("noiDung2");

            BaiVietGioiThieu bv2 = new BaiVietGioiThieu();
            bv2.setMaGameSteam(gameIdToUse);
            bv2.setTieuDeBaiViet(tieuDe2);
            bv2.setNoiDung(noiDung2);

            if (maBaiViet2 == null) {
                baiVietDAO.insert(bv2, conn);
            } else {
                bv2.setMaBaiViet(maBaiViet2);
                baiVietDAO.update(bv2, conn);
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback(); // Có lỗi, hủy bỏ tất cả
            }
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true); // Trả lại auto-commit
                conn.close(); // Trả connection về pool
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/products/steam");
    }
}
