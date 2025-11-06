package com.microshop.controller;

import com.microshop.dao.TaiKhoanLienQuanDAO; // Cần thiết để lấy chi tiết Tài khoản Liên Quân
import com.microshop.dao.TaiKhoanFreeFireDAO; // Giả định có DAO cho các game khác
import com.microshop.model.TaiKhoanLienQuan; // Giả định Model cụ thể
import com.microshop.model.TaiKhoan; // Giả định Model chung
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/shop/game/detail"})
public class GameDetailServlet extends HttpServlet {
    
    // Khởi tạo các DAO cần thiết
    private final TaiKhoanLienQuanDAO lienQuanDAO = new TaiKhoanLienQuanDAO();
    // private final TaiKhoanFreeFireDAO freeFireDAO = new TaiKhoanFreeFireDAO(); 
    // ... Khởi tạo các DAO khác nếu cần

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Lấy tham số từ URL
        String idParam = request.getParameter("id");
        String category = request.getParameter("category");
        
        if (idParam == null || category == null) {
            // Trường hợp lỗi: Thiếu tham số
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu ID tài khoản hoặc danh mục.");
            return;
        }

        try {
            int maTaiKhoan = Integer.parseInt(idParam);
            Object taiKhoanChiTiet = null; // Dùng Object vì Model có thể khác nhau (LQ, FF, Steam...)
            
            // 2. PHÂN LUỒNG: Gọi DAO tương ứng dựa trên 'category'
            switch (category.toLowerCase()) {
                case "lienquan":
                    // Giả định: getById(id) trả về TaiKhoanLienQuan
                    taiKhoanChiTiet = lienQuanDAO.getById(maTaiKhoan); 
                    break;
                case "freefire":
                    // taiKhoanChiTiet = freeFireDAO.getById(maTaiKhoan);
                    break;
                // ... Xử lý cho các category khác (riot, steam) ...
                default:
                    // Xử lý nếu category không hợp lệ
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Danh mục không tồn tại.");
                    return;
            }

            // 3. Xử lý kết quả
            if (taiKhoanChiTiet != null) {
                // Lưu đối tượng chi tiết vào Request Scope
                request.setAttribute("taiKhoanChiTiet", taiKhoanChiTiet);
                request.setAttribute("category", category);

                // Chuyển tiếp đến trang JSP hiển thị chi tiết sản phẩm
                request.getRequestDispatcher("/game_detail.jsp").forward(request, response);
            } else {
                // Không tìm thấy ID tài khoản trong DB
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy tài khoản với ID: " + maTaiKhoan);
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID tài khoản không hợp lệ.");
        } catch (SQLException ex) {
            Logger.getLogger(GameDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi cơ sở dữ liệu.");
        }
    }
}