package com.microshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Đổi đường dẫn ánh xạ (urlPatterns) sang /profile để dễ đọc và dễ gọi hơn
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    // Xóa bỏ processRequest và chỉ tập trung vào doGet/doPost

    /**
     * Handles the HTTP <code>GET</code> method.
     * Xử lý yêu cầu hiển thị trang hồ sơ.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Lấy Session hiện tại
        HttpSession session = request.getSession(false); // Không tạo Session mới nếu chưa có
        
        // 2. Kiểm tra xác thực (Authentication Check)
        // Kiểm tra xem đối tượng "user" đã được lưu trong Session hay chưa
        if (session != null && session.getAttribute("user") != null) {
            
            // Người dùng đã đăng nhập: Chuyển tiếp đến trang profile JSP
            // (Đảm bảo file profile.jsp tồn tại trong thư mục webapp)
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
            
        } else {
            
            // Người dùng CHƯA đăng nhập:
            // 3.1. Thiết lập thông báo lỗi (tùy chọn)
            request.setAttribute("errorMessage", "Vui lòng đăng nhập để xem hồ sơ.");
            
            // 3.2. Chuyển hướng về trang đăng nhập
            // (Sử dụng sendRedirect hoặc forward tùy thuộc vào cách LoginServlet được ánh xạ)
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Phương thức này sẽ được dùng để xử lý việc cập nhật thông tin hồ sơ.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Kiểm tra Session/Đăng nhập lại
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("user") != null) {
            // 2. Xử lý Logic Cập nhật Hồ sơ (ví dụ: Thay đổi tên, email, mật khẩu)
            
            // ... (Code gọi DAO để cập nhật dữ liệu) ...
            
            // 3. Chuyển hướng về trang profile sau khi cập nhật thành công
            response.sendRedirect(request.getContextPath() + "/profile?status=success");
        } else {
            // Chưa đăng nhập: Chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý việc hiển thị và cập nhật thông tin hồ sơ người dùng.";
    }
}