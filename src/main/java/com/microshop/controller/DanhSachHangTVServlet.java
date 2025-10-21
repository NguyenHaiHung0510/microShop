package com.microshop.controller;

import com.microshop.dao.HangThanhVienDAO;
import com.microshop.model.HangThanhVien;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/*
Test thoi nhé, code gpt gen
*/

/**
 * Servlet hiển thị danh sách hạng thành viên.
 * URL: /hangthanhvien
 */
@WebServlet("/hangthanhvien")
public class DanhSachHangTVServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HangThanhVienDAO dao = new HangThanhVienDAO();
        List<HangThanhVien> danhSach = dao.getAll();

        for (HangThanhVien x : danhSach) System.out.println(x);
        
        // Gửi danh sách sang JSP
        request.setAttribute("dsHang", danhSach);
        RequestDispatcher rd = request.getRequestDispatcher("hangthanhvien.jsp");
        rd.forward(request, response);
    }
}
