package com.microshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microshop.context.DBContext;
import com.microshop.model.HangThanhVien;

/**
 * DAO cho HangThanhVien implement CrudDAO chuẩn — phiên bản không dùng Logger
 */
public class HangThanhVienDAO implements CrudDAO<HangThanhVien, Integer> {

    // --- Map ResultSet → HangThanhVien ---
    private HangThanhVien mapResultSetToHangThanhVien(ResultSet rs) throws SQLException {
        HangThanhVien h = new HangThanhVien();
        h.setMaHang(rs.getInt("MaHang"));
        h.setTenHang(rs.getString("TenHang"));
        h.setMucChiTieuToiThieu(rs.getBigDecimal("MucChiTieuToiThieu"));
        h.setDuongDanIcon(rs.getString("DuongDanIcon"));
        h.setChietKhau(rs.getBigDecimal("ChietKhau"));
        return h;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    // --- GET ALL ---
    public List<HangThanhVien> getAll() {
        List<HangThanhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM HANGTHANHVIEN";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToHangThanhVien(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi lấy tất cả Hạng Thành Viên: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // --- GET BY ID ---
    public HangThanhVien getById(Integer id) {
        HangThanhVien result = null;
        String sql = "SELECT * FROM HANGTHANHVIEN WHERE MaHang = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToHangThanhVien(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi lấy Hạng Thành Viên theo ID: " + id);
            e.printStackTrace();
        }
        return result;
    }

    // --- INSERT ---
    @Override
    public Integer insert(HangThanhVien entity) {
        String sql = """
            INSERT INTO HANGTHANHVIEN 
            (TenHang, MucChiTieuToiThieu, DuongDanIcon, ChietKhau) 
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getTenHang());
            ps.setBigDecimal(2, entity.getMucChiTieuToiThieu());
            ps.setString(3, entity.getDuongDanIcon());
            ps.setBigDecimal(4, entity.getChietKhau());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return null;

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi tạo Hạng Thành Viên: " + entity.getTenHang());
            e.printStackTrace();
        }
        return null;
    }

    // --- UPDATE ---
    @Override
    public boolean update(HangThanhVien entity) {
        String sql = """
            UPDATE HANGTHANHVIEN 
            SET TenHang=?, MucChiTieuToiThieu=?, DuongDanIcon=?, ChietKhau=? 
            WHERE MaHang=?
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenHang());
            ps.setBigDecimal(2, entity.getMucChiTieuToiThieu());
            ps.setString(3, entity.getDuongDanIcon());
            ps.setBigDecimal(4, entity.getChietKhau());
            ps.setInt(5, entity.getMaHang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi cập nhật Hạng Thành Viên ID: " + entity.getMaHang());
            e.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM HANGTHANHVIEN WHERE MaHang=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi xóa Hạng Thành Viên ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    // --- GET BY PREFIX ---
    public List<HangThanhVien> getByPrefix(String prefix) {
        List<HangThanhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM HANGTHANHVIEN WHERE TenHang LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prefix + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToHangThanhVien(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi tìm Hạng Thành Viên theo prefix: " + prefix);
            e.printStackTrace();
        }

        return list;
    }
}
