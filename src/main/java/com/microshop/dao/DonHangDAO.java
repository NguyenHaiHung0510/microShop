package com.microshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.microshop.context.DBContext;
import com.microshop.model.DonHang;

public class DonHangDAO implements CrudDAO<DonHang, Integer> {

    // --- Map ResultSet → DonHang ---
    private DonHang mapResultSetToDonHang(ResultSet rs) throws SQLException {
        DonHang dh = new DonHang();
        dh.setMaDonHang(rs.getInt("MaDonHang"));
        dh.setMaNguoiDung(rs.getInt("MaNguoiDung"));
        dh.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
        dh.setGiaMua(rs.getBigDecimal("GiaMua"));

        Timestamp thoiGianMua = rs.getTimestamp("ThoiGianMua");
        if (thoiGianMua != null) {
            dh.setThoiGianMua(thoiGianMua.toLocalDateTime());
        }

        dh.setTrangThai(rs.getString("TrangThai"));

        Timestamp thoiGianTao = rs.getTimestamp("ThoiGianTao");
        if (thoiGianTao != null) {
            dh.setThoiGianTao(thoiGianTao.toLocalDateTime());
        }

        return dh;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    // --- GET ALL ---
    // SỬA: Thêm 'throws SQLException' và xóa try-catch
    @Override
    public List<DonHang> getAll() throws SQLException {
        List<DonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDonHang(rs));
            }
        }
        return list;
    }

    // --- GET BY ID ---
    // SỬA: Thêm 'throws SQLException' và xóa try-catch
    @Override
    public DonHang getById(Integer id) throws SQLException {
        DonHang result = null;
        String sql = "SELECT * FROM DONHANG WHERE MaDonHang = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToDonHang(rs);
                }
            }
        }
        return result;
    }

    // --- INSERT ---
    // SỬA: Thêm 'throws SQLException' và xóa try-catch
    @Override
    public Integer insert(DonHang entity) throws SQLException {
        String sql = """
            INSERT INTO DONHANG 
            (MaNguoiDung, MaTaiKhoan, GiaMua, ThoiGianMua, TrangThai, ThoiGianTao)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, entity.getMaNguoiDung());
            ps.setInt(2, entity.getMaTaiKhoan());
            ps.setBigDecimal(3, entity.getGiaMua());
            ps.setTimestamp(4, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);
            ps.setString(5, entity.getTrangThai());
            ps.setTimestamp(6, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : Timestamp.valueOf(LocalDateTime.now()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return null;
    }

    // --- UPDATE ---
    // SỬA: Thêm 'throws SQLException' và xóa try-catch
    @Override
    public boolean update(DonHang entity) throws SQLException {
        String sql = """
            UPDATE DONHANG 
            SET MaNguoiDung=?, MaTaiKhoan=?, GiaMua=?, ThoiGianMua=?, TrangThai=?, ThoiGianTao=? 
            WHERE MaDonHang=?
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entity.getMaNguoiDung());
            ps.setInt(2, entity.getMaTaiKhoan());
            ps.setBigDecimal(3, entity.getGiaMua());
            ps.setTimestamp(4, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);
            ps.setString(5, entity.getTrangThai());
            ps.setTimestamp(6, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(7, entity.getMaDonHang());

            return ps.executeUpdate() > 0;
        }
    }

    // --- DELETE ---
    // SỬA: Thêm 'throws SQLException' và xóa try-catch
    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM DONHANG WHERE MaDonHang=?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<DonHang> getByMaNguoiDung(Integer maNguoiDung) throws SQLException {
        String sql = "SELECT * FROM DonHang WHERE MaNguoiDung = ?";
        List<DonHang> list = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maNguoiDung);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHang(rs));
                }
            }
        }
        return list;
    }

    public DonHang getByMaTaiKhoan(Integer maTaiKhoan) throws SQLException {
        String sql = "SELECT * FROM DonHang WHERE MaTaiKhoan = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDonHang(rs);
                }
            }
        }
        return null;
    }

    public boolean updateTrangThai(Integer maDonHang, String trangThaiMoi, LocalDateTime thoiGianMua) throws SQLException {
        String sql = "UPDATE DonHang SET TrangThai = ?, ThoiGianMua = ? WHERE MaDonHang = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThaiMoi);
            ps.setTimestamp(2, Timestamp.valueOf(thoiGianMua));
            ps.setInt(3, maDonHang);

            return ps.executeUpdate() > 0;
        }
    }
}
