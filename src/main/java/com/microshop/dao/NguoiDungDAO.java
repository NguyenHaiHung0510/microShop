package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.NguoiDung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO implements CrudDAO<NguoiDung, Integer> {

    // --- Map ResultSet → NguoiDung ---
    private NguoiDung mapResultSetToNguoiDung(ResultSet rs) throws SQLException {
        NguoiDung nd = new NguoiDung();
        nd.setMaNguoiDung(rs.getInt("MaNguoiDung"));
        nd.setTenDangNhap(rs.getString("TenDangNhap"));
        nd.setMatKhau(rs.getString("MatKhau"));
        nd.setEmail(rs.getString("Email"));
        nd.setSoDienThoai(rs.getString("SoDienThoai"));
        nd.setVaiTro(rs.getString("VaiTro"));
        nd.setTongTienDaChi(rs.getBigDecimal("TongTienDaChi"));
        nd.setMaHangThanhVien(rs.getInt("MaHangThanhVien"));
        Timestamp ts = rs.getTimestamp("ThoiGianTao");
        nd.setThoiGianTao(ts != null ? ts.toLocalDateTime() : null);
        return nd;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    // --- GET ALL ---
    public List<NguoiDung> getAll() {
        List<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapResultSetToNguoiDung(rs));

        } catch (SQLException e) {
            System.out.println("Lỗi SQL khi lấy tất cả Người Dùng: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // --- GET BY ID ---
    public NguoiDung getById(Integer id) {
        NguoiDung result = null;
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) result = mapResultSetToNguoiDung(rs);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi SQL khi lấy Người Dùng theo ID: " + id);
            e.printStackTrace();
        }
        return result;
    }

    // --- INSERT ---
    @Override
    public Integer insert(NguoiDung entity) {
        String sql = """
            INSERT INTO NguoiDung 
            (TenDangNhap, MatKhau, Email, SoDienThoai, VaiTro, TongTienDaChi, MaHangThanhVien, ThoiGianTao)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getTenDangNhap());
            ps.setString(2, entity.getMatKhau());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getSoDienThoai());
            ps.setString(5, entity.getVaiTro());
            ps.setBigDecimal(6, entity.getTongTienDaChi());
            ps.setInt(7, entity.getMaHangThanhVien());
            ps.setTimestamp(8, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : null);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return null;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi SQL khi tạo Người Dùng: " + entity.getTenDangNhap());
            e.printStackTrace();
        }
        return null;
    }

    // --- UPDATE ---
    @Override
    public boolean update(NguoiDung entity) {
        String sql = """
            UPDATE NguoiDung 
            SET TenDangNhap=?, MatKhau=?, Email=?, SoDienThoai=?, VaiTro=?, 
                TongTienDaChi=?, MaHangThanhVien=?, ThoiGianTao=?
            WHERE MaNguoiDung=?
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenDangNhap());
            ps.setString(2, entity.getMatKhau());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getSoDienThoai());
            ps.setString(5, entity.getVaiTro());
            ps.setBigDecimal(6, entity.getTongTienDaChi());
            ps.setInt(7, entity.getMaHangThanhVien());
            ps.setTimestamp(8, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : null);
            ps.setInt(9, entity.getMaNguoiDung());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi SQL khi cập nhật Người Dùng ID: " + entity.getMaNguoiDung());
            e.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM NguoiDung WHERE MaNguoiDung=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi SQL khi xóa Người Dùng ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    // --- GET BY PREFIX (optional) ---
    public List<NguoiDung> getByPrefix(String prefix) {
        List<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prefix + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToNguoiDung(rs));
            }

        } catch (SQLException e) {
            System.out.println("Lỗi SQL khi tìm Người Dùng theo prefix: " + prefix);
            e.printStackTrace();
        }

        return list;
    }
}
