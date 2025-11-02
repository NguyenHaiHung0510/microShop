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
            // Làm tạm 1 vài acc để test giao diện
            if (gameSteamDAO.fastGetAll().size() == 0) {
                GameSteam tmpGame1 = new GameSteam(null, "Test Game Steam", "Black meet wukong", new BigDecimal("0.0"),
                        new BigDecimal("100.0"), 100, LocalDateTime.now(), "fake", "assets/images/home_steam.jpg");
                GameSteam tmpGame2 = new GameSteam(null, "Test Game Steam 2", "GTA 7",
                        new BigDecimal("100.0"), new BigDecimal("99.0"), 10000,
                        LocalDateTime.now(), "fake_too", "assets/images/home_steam.jpg");
                gameSteamDAO.insert(tmpGame1);
                gameSteamDAO.insert(tmpGame2);
            }

            if (taiKhoanLienQuanDAO.getAll().size() == 0) {
                TaiKhoanLienQuan tmpTklq1 = new TaiKhoanLienQuan(
                        null, 2, new BigDecimal("1000"), new BigDecimal("999"),
                        "DANG_BAN", "Rank Cao Thủ, 100 Tướng, 300 Trang Phục HOT!",
                        500, LocalDateTime.now().minusDays(1), "images\\home_lienquan.png", "test_acc_lq_1", "1", "Cao Thủ", 100, 300, 90, "Garena"
                );
                TaiKhoanLienQuan tmpTklq2 = new TaiKhoanLienQuan(
                        null, 2, new BigDecimal("2000"), new BigDecimal("1500"),
                        "DANG_BAN", "Rank Bố tướng",
                        500, LocalDateTime.now(), "images\\home_lienquan.png", "test_acc_lq_2", "1", "Bố tướng", 200, 500, 90, "tiktok?"
                );
                TaiKhoanLienQuan tmpTklq3 = new TaiKhoanLienQuan(
                        null, 2, new BigDecimal("5000"), new BigDecimal("3500"),
                        "DANG_BAN", "Top 1 sever mõm",
                        500, LocalDateTime.now().minusDays(1), "images\\home_lienquan.png", "test_acc_lq_3", "1", "Bố tướng", 100, 300, 90, "Garena"
                );

                tmpTklq1.setMaTaiKhoan((taiKhoanLienQuanDAO.insert(tmpTklq1)));
                tmpTklq2.setMaTaiKhoan((taiKhoanLienQuanDAO.insert(tmpTklq2)));
                tmpTklq3.setMaTaiKhoan((taiKhoanLienQuanDAO.insert(tmpTklq3)));

//                AnhTaiKhoan anh1 = new AnhTaiKhoan(null, tmpTklq1.getMaTaiKhoan(), "images\\home_lienquan.png");
//                AnhTaiKhoan anh2 = new AnhTaiKhoan(null, tmpTklq2.getMaTaiKhoan(), "images\\home_lienquan.png");
//                AnhTaiKhoan anh3 = new AnhTaiKhoan(null, tmpTklq3.getMaTaiKhoan(), "images\\home_lienquan.png");
//
//                AnhTaiKhoanDAO tmpAnh = new AnhTaiKhoanDAO();
//                tmpAnh.insert(anh1);
//                tmpAnh.insert(anh2);
//                tmpAnh.insert(anh3);
            }

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

            // Đẩy 4 danh sách này ra JSP
            request.setAttribute("listLienQuan", tmpLienQuan);
            request.setAttribute("listFreeFire", tmpFreeFire);
            request.setAttribute("listRiot", tmpRiot);
            request.setAttribute("listSteam", tmpSteam);

            // Forward sang home.jsp
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi truy vấn cơ sở dữ liệu.");
        }
    }
}
