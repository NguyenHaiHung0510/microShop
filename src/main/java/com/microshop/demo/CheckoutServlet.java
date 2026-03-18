package com.microshop.demo;
import jakarta.servlet.http.*;
//Mồi cho ZAP Full Scan & Codacy: Insecure Design (Business Logic Flaw)
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // LỖI (ZAP/Codacy): Tin tưởng tuyệt đối vào giá tiền (cartTotal) client gửi lên
            String strAmount = req.getParameter("cartTotalAmount");
            int finalAmount = Integer.parseInt(strAmount);
            System.out.println("Thực hiện thanh toán: " + finalAmount + " VND");
        } catch (Exception e) {}
    }
}