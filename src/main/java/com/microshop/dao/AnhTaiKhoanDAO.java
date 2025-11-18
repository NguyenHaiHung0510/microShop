package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.AnhTaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnhTaiKhoanDAO {

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    private AnhTaiKhoan mapResultSet(ResultSet rs) throws SQLException {
        AnhTaiKhoan anh = new AnhTaiKhoan();
        anh.setMaAnh(rs.getObject("MaAnh", Integer.class));
        anh.setMaTaiKhoan(rs.getObject("MaTaiKhoan", Integer.class));
        anh.setDuongDanAnh(rs.getString("DuongDanAnh"));
        return anh;
    }

    public Integer insert(AnhTaiKhoan entity) throws SQLException {
        try (Connection conn = getConnection()) {
            return insert(entity, conn);
        }
    }

    public Integer insert(AnhTaiKhoan entity, Connection conn) throws SQLException {
        String sql = "INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, entity.getMaTaiKhoan());
            ps.setString(2, entity.getDuongDanAnh());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Trả về MaAnh (PK)
                }
            }
        }
        return null;
    }

    public boolean deleteByMaAnh(Integer maAnh) throws SQLException {
        try (Connection conn = getConnection()) {
            return deleteByMaAnh(maAnh, conn);
        }
    }

    public boolean deleteByMaAnh(Integer maAnh, Connection conn) throws SQLException {
        String sql = "DELETE FROM ANH_TAIKHOAN WHERE MaAnh = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maAnh);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteByMaTaiKhoan(Integer maTaiKhoan) throws SQLException {
        try (Connection conn = getConnection()) {
            return deleteByMaTaiKhoan(maTaiKhoan, conn);
        }
    }

    public boolean deleteByMaTaiKhoan(Integer maTaiKhoan, Connection conn) throws SQLException {
        String sql = "DELETE FROM ANH_TAIKHOAN WHERE MaTaiKhoan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maTaiKhoan);
            return ps.executeUpdate() > 0;
        }
    }

    public List<AnhTaiKhoan> getByMaTaiKhoan(Integer maTaiKhoan) throws SQLException {
        List<AnhTaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM ANH_TAIKHOAN WHERE MaTaiKhoan = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }
        }
        return list;
    }
}
