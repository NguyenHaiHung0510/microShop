package com.microshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microshop.context.DBContext;
import com.microshop.model.AnhTaiKhoan;

public class AnhTaiKhoanDAO {

    private AnhTaiKhoan mapResultSetToAnhTaiKhoan(ResultSet rs) throws SQLException {
        AnhTaiKhoan img = new AnhTaiKhoan();
        img.setMaAnh(rs.getObject("MaAnh", Integer.class));
        img.setMaTaiKhoan(rs.getObject("MaTaiKhoan", Integer.class));
        img.setDuongDanAnh(rs.getString("DuongDanAnh"));
        return img;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    public List<AnhTaiKhoan> getByMaTaiKhoan(Integer maTaiKhoan) throws SQLException {
        List<AnhTaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM ANH_TAIKHOAN WHERE MaTaiKhoan = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToAnhTaiKhoan(rs));
                }
            }
        }
        return list;
    }

    public Integer insert(AnhTaiKhoan newImg) throws SQLException {
        String sql = "INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES (?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setObject(1, newImg.getMaTaiKhoan());
            ps.setString(2, newImg.getDuongDanAnh());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                return null;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return null;
    }

    public boolean deleteByMaAnh(Integer maAnh) throws SQLException {
        String sql = "DELETE FROM ANH_TAIKHOAN WHERE MaAnh = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maAnh);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteByMaTaiKhoan(Integer maTaiKhoan) throws SQLException {
        String sql = "DELETE FROM ANH_TAIKHOAN WHERE MaTaiKhoan = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);
            return ps.executeUpdate() > 0;
        }
    }
}
