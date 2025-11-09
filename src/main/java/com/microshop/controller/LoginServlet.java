package com.microshop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microshop.dao.NguoiDungDAO;
import com.microshop.model.NguoiDung;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    // Khởi tạo DAO để truy cập DB (Giả định)
    private final NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException {
        
        // Hiển thị form đăng nhập
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    //---------------------------------------------------------
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException {
        
        // 1. Thu thập dữ liệu từ request
        String tenDangNhap = request.getParameter("username"); // Tên trường input trong form
        String matKhau = request.getParameter("password");     // Tên trường input trong form
        
        // 2. Xác thực người dùng (Giả định NguoiDungDAO có phương thức checkLogin)
        NguoiDung user = null;
        try {
            user = nguoiDungDAO.getByTenDangNhap(tenDangNhap);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (user != null) {
            String matKhauDB = user.getMatKhau();
            boolean isBCrypt = PasswordUtils.isHashed(matKhauDB);
            boolean verified = false;

            if (isBCrypt) {
                // Đã là BCrypt → kiểm tra bằng BCrypt
                verified = PasswordUtils.verifyPassword(matKhau, matKhauDB);
            } else {
                // Chưa băm → kiểm tra bằng so sánh chuỗi
                if (matKhau.equals(matKhauDB)) {
                    verified = true;
                    String hashed = PasswordUtils.hashPassword(matKhau);
                    user.setMatKhau(hashed);
                    try {
                        nguoiDungDAO.update(user);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (verified) {
                // Xác thực thành công
                // 3.1. Tạo và thiết lập Session cho người dùng
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Lưu đối tượng NguoiDung vào Session
                session.setMaxInactiveInterval(60 * 5);
                
                // 3.2. Chuyển hướng đến trang chủ hoặc trang sau đăng nhập
                // Sử dụng sendRedirect để thay đổi URL trong trình duyệt
                response.sendRedirect(request.getContextPath() + "/home"); // Giả định có Controller /home
                return;
            }
        
        }

    // Xác thực thất bại
    // 3.3. Thêm thông báo lỗi vào request scope
    request.setAttribute("loginError", "Tên đăng nhập hoặc mật khẩu không chính xác.");
    
    // 3.4. Chuyển tiếp lại về trang login.jsp (để hiển thị form và thông báo lỗi)
    // Sử dụng forward để giữ lại thông báo lỗi trong request scope
    request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
