package com.microshop.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo/a06-insecure-design")
public class InsecureDesign_A06 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        
        String clientProvidedTotal = req.getParameter("total_amount");
        
        resp.getWriter().println("<h3>Thanh toán thành công (A06)</h3>");
        resp.getWriter().println("<p>Đã ghi nhận thanh toán số tiền: " + clientProvidedTotal + " VNĐ</p>");
        resp.getWriter().println("<p>Trạng thái đơn hàng: Đã thanh toán.</p>");
    }
}