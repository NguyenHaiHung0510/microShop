package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.HangThanhVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HangThanhVienDAO implements DAO<HangThanhVien> {

    private static final Logger LOGGER = Logger.getLogger(HangThanhVienDAO.class.getName());

    // --- HÀM ÁNH XẠ DỮ LIỆU ---
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

    // --- LẤY TẤT CẢ ---
    @Override
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
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi lấy tất cả Hạng Thành Viên.", e);
        }
        return list;
    }

    // --- LẤY THEO ID ---
    @Override
    public HangThanhVien getById(String id) {
        HangThanhVien result = null;
        String sql = "SELECT * FROM HANGTHANHVIEN WHERE MaHang = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToHangThanhVien(rs);
                }
            }

        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID không hợp lệ: " + id, e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi lấy theo ID: " + id, e);
        }
        return result;
    }

    // --- TÌM THEO PREFIX ---
    @Override
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
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi tìm theo prefix: " + prefix, e);
        }
        return list;
    }

    // --- THÊM MỚI ---
    @Override
    public boolean create(HangThanhVien entity) {
        String sql = "INSERT INTO HANGTHANHVIEN (TenHang, MucChiTieuToiThieu, DuongDanIcon, ChietKhau) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenHang());
            ps.setBigDecimal(2, entity.getMucChiTieuToiThieu());
            ps.setString(3, entity.getDuongDanIcon());
            ps.setBigDecimal(4, entity.getChietKhau());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi tạo Hạng Thành Viên: " + entity.getTenHang(), e);
            return false;
        }
    }

    // --- CẬP NHẬT ---
    @Override
    public boolean update(HangThanhVien entity) {
        String sql = "UPDATE HANGTHANHVIEN SET TenHang=?, MucChiTieuToiThieu=?, DuongDanIcon=?, ChietKhau=? WHERE MaHang=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenHang());
            ps.setBigDecimal(2, entity.getMucChiTieuToiThieu());
            ps.setString(3, entity.getDuongDanIcon());
            ps.setBigDecimal(4, entity.getChietKhau());
            ps.setInt(5, entity.getMaHang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi cập nhật ID: " + entity.getMaHang(), e);
            return false;
        }
    }

    // --- XÓA ---
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM HANGTHANHVIEN WHERE MaHang = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(id));
            return ps.executeUpdate() > 0;

        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID xóa không hợp lệ: " + id, e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi xóa ID: " + id, e);
        }
        return false;
    }
}
