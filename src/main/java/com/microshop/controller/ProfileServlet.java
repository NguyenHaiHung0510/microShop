package com.microshop.controller;

import com.microshop.model.NguoiDung;
import com.microshop.dao.HangThanhVienDAO;
import com.microshop.dao.NguoiDungDAO; // SỬA: Thêm DAO người dùng
import com.microshop.model.HangThanhVien;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    private final HangThanhVienDAO hangDao = new HangThanhVienDAO();
    private final NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        NguoiDung userCu = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        if (userCu != null) {
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

                // Lấy Hạng Thành Viên bằng user MỚI
                HangThanhVien htv = hangDao.getById(user.getMaHangThanhVien());
                request.setAttribute("HangNguoiDung", htv.getTenHang());

                // Chuyển tiếp đến JSP
                request.getRequestDispatcher("/profile.jsp").forward(request, response);

            } catch (SQLException e) {
                Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, "Lỗi SQL khi tải profile", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi truy vấn CSDL");
            }
        } else {
            // Người dùng CHƯA đăng nhập
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            // (Hiện tại chưa dùng, nhưng để đây cho logic /profile/edit sau này)
            response.sendRedirect(request.getContextPath() + "/profile?status=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý việc hiển thị và cập nhật thông tin hồ sơ người dùng.";
    }
}
