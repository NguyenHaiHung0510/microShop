package com.microshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet xử lý yêu cầu đăng xuất người dùng.
 * Ánh xạ tới đường dẫn /logout
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException {
        
        // 1. Lấy phiên làm việc (Session) hiện tại. Tham số true/false không quan trọng
        // vì chúng ta sẽ xóa nó ngay sau đó.
        HttpSession session = request.getSession(false); // Lấy session hiện tại (nếu có)

        if (session != null) {
            // 2. Xóa bỏ (Invalidate) phiên làm việc.
            // Thao tác này sẽ xóa tất cả các thuộc tính đã lưu (bao gồm cả "user")
            // và kết thúc session trên server.
            session.invalidate(); 
        }

        // 3. Chuyển hướng người dùng về trang đăng nhập hoặc trang chủ công cộng.
        // Sử dụng sendRedirect để thay đổi hoàn toàn URL trong trình duyệt.
        // Chuyển hướng về trang login sau khi đăng xuất
        response.sendRedirect(request.getContextPath() + "/login");
    }

    // Không cần triển khai doPost() cho hành động logout, vì nó thường được gọi bằng GET
    // (nhấn vào link) để tránh gây khó khăn cho người dùng.
}