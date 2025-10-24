package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.NguoiDung;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;

public class NguoiDungDAO implements DAO<NguoiDung> {

    private static final Logger LOGGER = Logger.getLogger(NguoiDungDAO.class.getName());

    // ===================== HÀM ÁNH XẠ =====================
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

    // ===================== GET ALL =====================
    @Override
    public List<NguoiDung> getAll() {
        List<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToNguoiDung(rs));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi lấy tất cả Người Dùng.", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi chung khi getAll().", e);
        }
        return list;
    }

    // ===================== GET BY ID =====================
    @Override
    public NguoiDung getById(String id) {
        NguoiDung result = null;
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ?";

        try {
            int maNguoiDung = Integer.parseInt(id);

            try (Connection conn = DBContext.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, maNguoiDung);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        result = mapResultSetToNguoiDung(rs);
                    }
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID không hợp lệ: " + id, e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi getById: " + id, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định khi getById.", e);
        }

        return result;
    }

    // ===================== GET BY PREFIX =====================
    @Override
    public List<NguoiDung> getByPrefix(String prefix) {
        List<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap LIKE ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prefix + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToNguoiDung(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi tìm theo prefix: " + prefix, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định khi getByPrefix.", e);
        }

        return list;
    }

    // ===================== CREATE =====================
    @Override
    public boolean create(NguoiDung entity) {
        String sql = """
            INSERT INTO NguoiDung 
            (TenDangNhap, MatKhau, Email, SoDienThoai, VaiTro, TongTienDaChi, MaHangThanhVien, ThoiGianTao)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenDangNhap());
            ps.setString(2, entity.getMatKhau());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getSoDienThoai());
            ps.setString(5, entity.getVaiTro());
            ps.setBigDecimal(6, entity.getTongTienDaChi());
            ps.setInt(7, entity.getMaHangThanhVien());
            ps.setTimestamp(8, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : null);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi tạo người dùng: " + entity.getTenDangNhap(), e);
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi chung khi tạo.", e);
            return false;
        }
    }

    // ===================== UPDATE =====================
    @Override
    public boolean update(NguoiDung entity) {
        String sql = """
            UPDATE NguoiDung 
            SET TenDangNhap = ?, MatKhau = ?, Email = ?, SoDienThoai = ?, VaiTro = ?, 
                TongTienDaChi = ?, MaHangThanhVien = ?, ThoiGianTao = ?
            WHERE MaNguoiDung = ?
        """;

        try (Connection conn = DBContext.getConnection();
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
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi cập nhật ID: " + entity.getMaNguoiDung(), e);
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi chung khi cập nhật.", e);
            return false;
        }
    }

    // ===================== DELETE =====================
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM NguoiDung WHERE MaNguoiDung = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(id));
            return ps.executeUpdate() > 0;

        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID không hợp lệ khi xóa: " + id, e);
            return false;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi xóa ID: " + id, e);
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định khi xóa.", e);
            return false;
        }
    }
}
