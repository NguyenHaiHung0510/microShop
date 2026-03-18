package com.microshop.demo;
import jakarta.servlet.http.*;
//Mồi cho ZAP Full Scan: Reflected XSS
public class ProductSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String keyword = req.getParameter("keyword");
            resp.setContentType("text/html");
            // LỖI (ZAP Full Scan): In thẳng input người dùng ra giao diện không qua bộ lọc
            resp.getWriter().println("<div class='search-results'>Kết quả cho: " + keyword + "</div>");
        } catch (Exception e) {}
    }
}