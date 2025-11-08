package com.microshop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.microshop.dao.NguoiDungDAO;
import com.microshop.model.NguoiDung;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private final NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Hiển thị form đăng ký
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String sdt = request.getParameter("sdt");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        String errorMessage = null;

        // 1. Kiểm tra mật khẩu
        if (!password.equals(confirmPassword)) {
            errorMessage = "Mật khẩu và xác nhận mật khẩu không khớp.";
        }

        // 2. Kiểm tra username hoặc email đã tồn tại chưa
        if (errorMessage == null) {
            try {
                if (nguoiDungDAO.getByTenDangNhap(username) != null) {
                    errorMessage = "Tên đăng nhập đã tồn tại.";
                } else if (nguoiDungDAO.getByEmail(email) != null) {
                    errorMessage = "Email đã được sử dụng.";
                }
            } catch (SQLException e) {
                errorMessage = "Lỗi hệ thống: " + e.getMessage();
            }
        }

        // 3. Nếu có lỗi -> forward lại register.jsp
        if (errorMessage != null) {
            request.setAttribute("registerError", errorMessage);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // 4. Nếu hợp lệ -> tạo đối tượng NguoiDung và lưu vào DB
        NguoiDung newUser = new NguoiDung();
        newUser.setTenDangNhap(username);
        newUser.setEmail(email);
        newUser.setSoDienThoai(sdt);
        newUser.setMatKhau(password); // TODO: Hash mật khẩu trong thực tế
        newUser.setVaiTro("USER"); // mặc định user
        newUser.setTongTienDaChi(BigDecimal.ZERO);
        newUser.setMaHangThanhVien(null);
        newUser.setThoiGianTao(LocalDateTime.now());

        try {
            Integer id = nguoiDungDAO.insert(newUser);
            if (id != null) {
                // Đăng ký thành công -> redirect về trang login
                response.sendRedirect(request.getContextPath() + "/login?register=success");
            } else {
                request.setAttribute("registerError", "Không thể tạo tài khoản. Vui lòng thử lại.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("registerError", "Lỗi cơ sở dữ liệu: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
