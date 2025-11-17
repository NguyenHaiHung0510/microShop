package com.microshop.controller;

import com.microshop.dao.GameSteamDAO;
import com.microshop.dao.TaiKhoanFreeFireDAO;
import com.microshop.dao.TaiKhoanLienQuanDAO;
import com.microshop.dao.TaiKhoanRiotDAO;
import com.microshop.model.GameSteam;
//import com.microshop.model.TaiKhoan; 
import com.microshop.model.TaiKhoanFreeFire;
import com.microshop.model.TaiKhoanLienQuan;
import com.microshop.model.TaiKhoanRiot;
// Tạm để insert ảnh cho tài khoản test
import com.microshop.model.AnhTaiKhoan;
import com.microshop.dao.AnhTaiKhoanDAO;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    private TaiKhoanLienQuanDAO taiKhoanLienQuanDAO;
    private TaiKhoanFreeFireDAO taiKhoanFreeFireDAO;
    private TaiKhoanRiotDAO taiKhoanRiotDAO;
    private GameSteamDAO gameSteamDAO;

    @Override
    public void init() throws ServletException {
        this.taiKhoanLienQuanDAO = new TaiKhoanLienQuanDAO();
        this.taiKhoanFreeFireDAO = new TaiKhoanFreeFireDAO();
        this.taiKhoanRiotDAO = new TaiKhoanRiotDAO();
        this.gameSteamDAO = new GameSteamDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<TaiKhoanLienQuan> listLienQuan = taiKhoanLienQuanDAO.getByTrangThai("DANG_BAN");

            List<TaiKhoanFreeFire> listFreeFire = taiKhoanFreeFireDAO.getByTrangThai("DANG_BAN");

            List<TaiKhoanRiot> listRiot = taiKhoanRiotDAO.getByTrangThai("DANG_BAN");

            // Lấy Game Steam dùng fastGetAll để tải nhanh, không lấy mô tả
            List<GameSteam> listSteam = gameSteamDAO.fastGetAll();

            // Giới hạn mỗi danh sách chỉ 8 sản phẩm để hiển thị (vì DAO chưa có LIMIT)
            // Đây là cách dùng Java Steam API tốt hơn, thay cho cách dùng substring cũ
            List<TaiKhoanLienQuan> tmpLienQuan = listLienQuan.stream().limit(8).collect(Collectors.toList());
            List<TaiKhoanFreeFire> tmpFreeFire = listFreeFire.stream().limit(8).collect(Collectors.toList());
            List<TaiKhoanRiot> tmpRiot = listRiot.stream().limit(8).collect(Collectors.toList());
            List<GameSteam> tmpSteam = listSteam.stream().limit(8).collect(Collectors.toList());
            for(TaiKhoanLienQuan x : tmpLienQuan){
                System.out.println(x);
            }
            
            request.setAttribute("listLienQuan", tmpLienQuan);
            request.setAttribute("listFreeFire", tmpFreeFire);
            request.setAttribute("listRiot", tmpRiot);
            request.setAttribute("listSteam", tmpSteam);

            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi truy vấn cơ sở dữ liệu.");
        }
    }
}
