package com.microshop.demo;
import jakarta.servlet.http.*;
//Mồi cho ZAP Baseline: Information Exposure
public class SystemErrorHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int divideByZero = 100 / 0;
        } catch (Exception e) {
            try {
                resp.setContentType("text/plain");
                // LỖI (ZAP Baseline): In toàn bộ Stack Trace mã nguồn ra trình duyệt
                e.printStackTrace(resp.getWriter());
            } catch (Exception ex) {}
        }
    }
}