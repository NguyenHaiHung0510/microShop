package com.microshop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.microshop.dao.NguoiDungDAO;
import com.microshop.model.NguoiDung;
import com.microshop.util.PasswordUtils;
import com.microshop.util.PhoneValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; // nếu bạn tách riêng file băm mật khẩu
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
        if (password == null || password.length() < 8) {
            errorMessage = "Mật khẩu phải có ít nhất 8 ký tự.";
        } else if (!password.equals(confirmPassword)) {
            errorMessage = "Mật khẩu và xác nhận mật khẩu không khớp.";
        }

        // 2. Kiểm tra số điện thoại hợp lệ
        if (errorMessage == null) {
            String phoneError = PhoneValidator.validatePhone(sdt);
            if (phoneError != null) {
                errorMessage = phoneError;
            }
        }

        // 3. Kiểm tra username, email và số điện thoại hoặc đã tồn tại chưa
        if (errorMessage == null) {
            try {
                if (nguoiDungDAO.getByTenDangNhap(username) != null) {
                    errorMessage = "Tên đăng nhập đã tồn tại.";
                } else if (nguoiDungDAO.getByEmail(email) != null) {
                    errorMessage = "Email đã được sử dụng.";
                } else if (nguoiDungDAO.getBySoDienThoai(sdt) != null) {
                    errorMessage = "Số điện thoại đã được sử dụng.";
                }
            } catch (SQLException e) {
                errorMessage = "Lỗi hệ thống: " + e.getMessage();
            }
        }

        // 4. Nếu có lỗi -> forward lại register.jsp
        if (errorMessage != null) {
            request.setAttribute("registerError", errorMessage);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // 5. Nếu hợp lệ -> tạo đối tượng NguoiDung và lưu vào DB (email có thể null)
        NguoiDung newUser = new NguoiDung();
        newUser.setTenDangNhap(username);
        newUser.setEmail((email == null || email.isEmpty()) ? null : email); // Để trống
        newUser.setSoDienThoai((sdt == null || sdt.isEmpty()) ? null : sdt); // Để trống
        newUser.setMatKhau(PasswordUtils.hashPassword(password)); // TODO: Hash mật khẩu trong thực tế
        newUser.setVaiTro("USER"); // mặc định user
        newUser.setTongTienDaChi(BigDecimal.ZERO);
        newUser.setMaHangThanhVien(1);
        newUser.setThoiGianTao(LocalDateTime.now());

        try {
            Integer id = nguoiDungDAO.insert(newUser);
            if (id != null) {
                // Lấy session để kiểm tra xem có URL gốc (redirect sau đăng nhập) không
                var session = request.getSession();
                String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
                session.removeAttribute("redirectAfterLogin"); // Xóa sau khi dùng

                if (redirectUrl != null && !redirectUrl.contains("/home")) {
                    // Nếu có trang gốc, quay lại đó và gửi kèm thông báo đăng ký thành công
                    response.sendRedirect(redirectUrl + (redirectUrl.contains("?") ? "&" : "?") + "registered=success");
                } else {
                    // Nếu không có redirect, quay về login như bình thường
                    response.sendRedirect(request.getContextPath() + "/login?registered=success");
                }
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
