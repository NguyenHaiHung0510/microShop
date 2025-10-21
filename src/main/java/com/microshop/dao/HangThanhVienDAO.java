package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.HangThanhVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Test connection pool, code gpt gen
*/

/**
 * DAO (Data Access Object) - quản lý truy xuất dữ liệu từ bảng HANGTHANHVIEN
 */
public class HangThanhVienDAO {

    /**
     * Lấy toàn bộ danh sách hạng thành viên từ DB
     * @return List<HangThanhVien>
     */
    public List<HangThanhVien> getAll() {
        List<HangThanhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM HANGTHANHVIEN";

        // try-with-resources: tự động đóng Connection, Statement, ResultSet
        try (Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HangThanhVien h = new HangThanhVien();
                h.setMaHang(rs.getInt("MaHang"));
                h.setTenHang(rs.getString("TenHang"));
                h.setMucChiTieuToiThieu(rs.getBigDecimal("MucChiTieuToiThieu"));
                h.setDuongDanIcon(rs.getString("DuongDanIcon"));
                h.setChietKhau(rs.getBigDecimal("ChietKhau"));
                list.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
