package com.microshop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microshop.dao.NguoiDungDAO;
import com.microshop.model.NguoiDung;
import com.microshop.util.PasswordUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ProfileEditServlet", urlPatterns = {"/profile/edit"})
public class ProfileEditServlet extends HttpServlet {
    
    // Khởi tạo DAO
    private final NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    
    // --- Phương thức GET: Hiển thị Form Chỉnh sửa ---
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            request.getRequestDispatcher("/profile_edit_form.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    // --- Phương thức POST: Xử lý Cập nhật Thông tin ---
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Kiểm tra Session và Đăng nhập
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        NguoiDung currentUser = (NguoiDung) session.getAttribute("user");
        
        // 2. Thu thập dữ liệu từ Form
        String newEmail = request.getParameter("email");
        String newSdt = request.getParameter("sdt");
        String oldPassword = request.getParameter("old_password"); // THÊM MẬT KHẨU CŨ
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");
        
        // 3. Xác thực Dữ liệu và Cập nhật Model
        String errorMessage = null;
        
        // --- Xác thực Mật khẩu ---
        // Nếu người dùng có ý định thay đổi mật khẩu (đã nhập Mật khẩu Mới)
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            
            // 3.1. KIỂM TRA MẬT KHẨU CŨ CÓ BỊ BỎ TRỐNG KHÔNG
            if (oldPassword == null || oldPassword.trim().isEmpty()) {
                errorMessage = "Vui lòng nhập mật khẩu hiện tại để thay đổi mật khẩu mới.";
                
            // 3.2. KIỂM TRA MẬT KHẨU CŨ CÓ KHỚP VỚI MẬT KHẨU TRONG DB/SESSION KHÔNG
            // CẢNH BÁO: Trong thực tế, bạn phải dùng hàm xác minh HASH (bcrypt/Argon2)
            // So sánh mật khẩu cũ bằng cơ chế BCrypt
            } else if (!PasswordUtils.verifyPassword(oldPassword, currentUser.getMatKhau())) {
                errorMessage = "Mật khẩu hiện tại không chính xác.";
                
            // 3.3. KIỂM TRA MẬT KHẨU MỚI VÀ XÁC NHẬN CÓ KHỚP KHÔNG
            } else if (confirmPassword == null || !newPassword.equals(confirmPassword)) {
                errorMessage = "Mật khẩu mới và xác nhận mật khẩu không khớp.";
                
            }
            else if (PasswordUtils.verifyPassword(newPassword, currentUser.getMatKhau())){
                errorMessage = "Mật khẩu mới và mật khẩu cũ không được giống nhau.";
            }
            else {
                // Cập nhật mật khẩu mới vào Model
                // TODO: PHẢI HASH MẬT KHẨU Ở ĐÂY TRƯỚC KHI GÁN!
                currentUser.setMatKhau(PasswordUtils.hashPassword(newPassword));
            }
        }
        
        // --- Xử lý Cập nhật Thông tin khác nếu không có lỗi mật khẩu ---
        if (errorMessage == null) {
            // Cập nhật các trường khác vào đối tượng Model
            currentUser.setEmail(newEmail);
            currentUser.setSoDienThoai(newSdt);
            // Cập nhật NgaySinh nếu có (cần xử lý định dạng/convert)
            
            try {
                // 4. Gọi DAO để thực hiện cập nhật vào Database
                boolean success = nguoiDungDAO.update(currentUser); 
                
                if (success) {
                    // 5. Cập nhật thành công: Cập nhật lại Session và Chuyển hướng PRG
                    session.setAttribute("user", currentUser);
                    response.sendRedirect(request.getContextPath() + "/profile?update=success");
                    return;
                } else {
                    errorMessage = "Lỗi hệ thống khi lưu dữ liệu. Vui lòng thử lại.";
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProfileEditServlet.class.getName()).log(Level.SEVERE, null, ex);
                errorMessage = "Lỗi cơ sở dữ liệu: Không thể cập nhật thông tin.";
            }
        }
        
        // 6. Xử lý Thất bại: Forward lại Form với thông báo lỗi
        request.setAttribute("errorEditMessage", errorMessage);
        request.getRequestDispatcher("/profile_edit_form.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet xử lý việc cập nhật thông tin hồ sơ người dùng.";
    }
}