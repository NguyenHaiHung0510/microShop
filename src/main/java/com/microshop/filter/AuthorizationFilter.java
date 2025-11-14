package com.microshop.filter;

import com.microshop.model.NguoiDung;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

//Lọc tất cả URL có dạng admin và chỉ cho user có VaiTro = "ADMIN" vào
@WebFilter("/admin/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Lấy session, không tạo mới

        NguoiDung user = null;
        if (session != null) {
            user = (NguoiDung) session.getAttribute("user");
        }

        boolean isAdmin = false;
        if (user != null && user.getVaiTro() != null) {
            isAdmin = "ADMIN".equalsIgnoreCase(user.getVaiTro().trim());
        }

        if (!isAdmin) {
            // Nếu không phải admin, chuyển hướng về home
            res.sendRedirect(req.getContextPath() + "/home");
        } else {
            // Là admin, cho phép truy cập
            chain.doFilter(request, response);
        }
    }
}
