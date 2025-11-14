package com.microshop.controller;

import com.microshop.context.DBContext;
import com.microshop.dao.BaiVietGioiThieuDAO;
import com.microshop.dao.GameSteamDAO;
import com.microshop.model.BaiVietGioiThieu;
import com.microshop.model.GameSteam;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
@WebServlet(name = "AdminSteamGameServlet", urlPatterns = {
    "/admin/products/steam",
    "/admin/steam/add",
    "/admin/steam/edit",
    "/admin/steam/save",
    "/admin/steam/delete"
})
public class AdminSteamGameServlet extends HttpServlet {

    private GameSteamDAO gameSteamDAO;
    private BaiVietGioiThieuDAO baiVietDAO;
    private String uploadPath; // Đường dẫn vật lý tuyệt đối tới .../uploads

    @Override
    public void init() {
        gameSteamDAO = new GameSteamDAO();
        baiVietDAO = new BaiVietGioiThieuDAO();

        // Lấy đường dẫn vật lý trên server để lưu file
        uploadPath = getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "images" + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
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
        } catch (ServletException | IOException | SQLException e) {
            // Kiểm tra xem có phải lỗi do file quá lớn không
            if (e instanceof IOException || e instanceof IllegalStateException || e instanceof ServletException) {
                Logger.getLogger(AdminSteamGameServlet.class.getName()).log(Level.WARNING, "Lỗi upload file: " + e.getMessage());

                // Gửi thông báo lỗi về form
                request.setAttribute("errorMessage", "Lỗi: File upload quá lớn (Tối đa 10MB) hoặc request quá nặng.");

                try {
                    // cần tải lại dữ liệu cho form (nếu là form edit)
                    // Hoặc đơn giản là forward về trang form rỗng (nếu là add)
                    // Để đơn giản, chuyển hướng về trang danh sách
                    listSteamGames(request, response); // Tạm thời chuyển về trang list
                } catch (SQLException ex) {
                    System.getLogger(AdminSteamGameServlet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } else {
                throw new ServletException(e);
            }
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
        request.setAttribute("baiViet1", baiViets.size() > 0 ? baiViets.get(0) : new BaiVietGioiThieu());
        request.setAttribute("baiViet2", baiViets.size() > 1 ? baiViets.get(1) : new BaiVietGioiThieu());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/form_steam_game.jsp");
        dispatcher.forward(request, response);
    }

    // POST: /admin/steam/delete
    private void deleteSteamGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Xóa file ảnh trước khi xóa bản ghi
        GameSteam game = gameSteamDAO.getById(id);
        if (game != null && game.getDuongDanAnh() != null && !game.getDuongDanAnh().isEmpty()) {
            deleteFile(game.getDuongDanAnh());
        }

        gameSteamDAO.delete(id); // CSDL tự xóa bài viết vì ON DELETE CASCADE
        response.sendRedirect(request.getContextPath() + "/admin/products/steam");
    }

    // POST: /admin/steam/save
    private void saveSteamGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        Connection conn = null;
        String newImagePath = null;

        try {
            Part filePart = request.getPart("fileAnh");
            String anhHienTai = request.getParameter("anhHienTai"); // Lấy ảnh cũ (nếu có)

            if (filePart != null && filePart.getSize() > 0) {
                // 1. Có file mới: Lưu file mới
                newImagePath = saveFile(filePart);

                // 2. Nếu có ảnh cũ (anhHienTai), xóa nó đi
                if (anhHienTai != null && !anhHienTai.isEmpty()) {
                    deleteFile(anhHienTai);
                }
            } else {
                // 3. Không có file mới: Giữ lại ảnh cũ
                newImagePath = anhHienTai;
            }

            conn = DBContext.getConnection();
            conn.setAutoCommit(false);

            String maGameParam = request.getParameter("maGameSteam");
            Integer maGameSteam = (maGameParam == null || maGameParam.isEmpty()) ? null : Integer.parseInt(maGameParam);
            String tenGame = request.getParameter("tenGame");
            BigDecimal giaGoc = new BigDecimal(request.getParameter("giaGoc"));
            BigDecimal giaBan = new BigDecimal(request.getParameter("giaBan"));
            String moTaGame = request.getParameter("moTaGame");
            String idVideoTrailer = request.getParameter("idVideoTrailer");

            GameSteam game = new GameSteam();
            game.setTenGame(tenGame);
            game.setGiaGoc(giaGoc);
            game.setGiaBan(giaBan);
            game.setMoTaGame(moTaGame);
            game.setIdVideoTrailer(idVideoTrailer);
            game.setDuongDanAnh(newImagePath);

            Integer gameIdToUse;

            if (maGameSteam == null) {
                game.setLuotXem(0);
                gameIdToUse = gameSteamDAO.insert(game, conn);
            } else {
                game.setMaGameSteam(maGameSteam);
                gameSteamDAO.update(game, conn);
                gameIdToUse = maGameSteam;
            }

            // Xử lý Bài Viết 1
            String maBaiViet1Param = request.getParameter("maBaiViet1");
            Integer maBaiViet1 = (maBaiViet1Param == null || maBaiViet1Param.isEmpty() || "0".equals(maBaiViet1Param)) ? null : Integer.parseInt(maBaiViet1Param);
            String tieuDe1 = request.getParameter("tieuDe1");
            String noiDung1 = request.getParameter("noiDung1");
            BaiVietGioiThieu bv1 = new BaiVietGioiThieu();
            bv1.setMaGameSteam(gameIdToUse);
            bv1.setTieuDeBaiViet(tieuDe1);
            bv1.setNoiDung(noiDung1);
            if (maBaiViet1 == null && !tieuDe1.isEmpty()) {
                baiVietDAO.insert(bv1, conn);
            } else if (maBaiViet1 != null) {
                bv1.setMaBaiViet(maBaiViet1);
                baiVietDAO.update(bv1, conn);
            }

            // Xử lý Bài Viết 2
            String maBaiViet2Param = request.getParameter("maBaiViet2");
            Integer maBaiViet2 = (maBaiViet2Param == null || maBaiViet2Param.isEmpty() || "0".equals(maBaiViet2Param)) ? null : Integer.parseInt(maBaiViet2Param);
            String tieuDe2 = request.getParameter("tieuDe2");
            String noiDung2 = request.getParameter("noiDung2");
            BaiVietGioiThieu bv2 = new BaiVietGioiThieu();
            bv2.setMaGameSteam(gameIdToUse);
            bv2.setTieuDeBaiViet(tieuDe2);
            bv2.setNoiDung(noiDung2);
            if (maBaiViet2 == null && !tieuDe2.isEmpty()) {
                baiVietDAO.insert(bv2, conn);
            } else if (maBaiViet2 != null) {
                bv2.setMaBaiViet(maBaiViet2);
                baiVietDAO.update(bv2, conn);
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
            // Nếu có lỗi, file mới có thể đã được lưu -> xóa nó đi
            if (newImagePath != null && !newImagePath.equals(request.getParameter("anhHienTai"))) {
                deleteFile(newImagePath);
            }
            throw new ServletException(e); // Ném lỗi ra ngoài để `doPost` xử lý
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/products/steam");
    }

    private String saveFile(Part filePart) throws IOException {
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExtension = "";
        int i = originalFileName.lastIndexOf('.');
        if (i > 0) {
            fileExtension = originalFileName.substring(i); // Vd: ".jpg"
        }
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        // Đường dẫn vật lý tuyệt đối
        String filePath = uploadPath + File.separator + newFileName;
        filePart.write(filePath);

        // Trả về đường dẫn tương đối (dùng cho CSDL và <img>)
        return "assets/images/uploads/" + newFileName;
    }

    private void deleteFile(String dbPath) {
        if (dbPath == null || dbPath.isEmpty()) {
            return;
        }

        try {
            // Lấy tên file từ đường dẫn CSDL (vd: assets/images/uploads/uuid.jpg -> uuid.jpg)
            String fileName = Paths.get(dbPath).getFileName().toString();

            // Tạo đường dẫn vật lý tuyệt đối
            String absolutePath = uploadPath + File.separator + fileName;

            File fileToDelete = new File(absolutePath);
            if (fileToDelete.exists()) {
                fileToDelete.delete();
            }
        } catch (Exception e) {
            // Ghi log lỗi, nhưng không dừng chương trình
            Logger.getLogger(AdminSteamGameServlet.class.getName()).log(Level.WARNING, "Không thể xóa file cũ: " + dbPath, e);
        }
    }
}
