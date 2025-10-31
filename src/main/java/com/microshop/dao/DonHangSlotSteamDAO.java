package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.DonHangSlotSteam;
//import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DonHangSlotSteamDAO implements CrudDAO<DonHangSlotSteam, Integer> {

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    private DonHangSlotSteam mapResultSetToDonHangSlotSteam(ResultSet rs) throws SQLException {
        DonHangSlotSteam donHang = new DonHangSlotSteam();
        donHang.setMaDonHangSlot(rs.getObject("MaDonHangSlot", Integer.class));
        donHang.setMaNguoiDung(rs.getObject("MaNguoiDung", Integer.class));
        donHang.setMaGameSteam(rs.getObject("MaGameSteam", Integer.class));
        donHang.setMaTaiKhoanSteam(rs.getObject("MaTaiKhoanSteam", Integer.class));
        donHang.setGiaMua(rs.getBigDecimal("GiaMua"));

        Timestamp thoiGianMua = rs.getTimestamp("ThoiGianMua");
        if (thoiGianMua != null) {
            donHang.setThoiGianMua(thoiGianMua.toLocalDateTime());
        }

        donHang.setTrangThai(rs.getString("TrangThai"));

        Timestamp thoiGianTao = rs.getTimestamp("ThoiGianTao");
        if (thoiGianTao != null) {
            donHang.setThoiGianTao(thoiGianTao.toLocalDateTime());
        }
        return donHang;
    }

    @Override
    public List<DonHangSlotSteam> getAll() throws SQLException {
        List<DonHangSlotSteam> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG_SLOT_STEAM";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToDonHangSlotSteam(rs));
            }
        }
        return list;
    }

    @Override
    public DonHangSlotSteam getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM DONHANG_SLOT_STEAM WHERE MaDonHangSlot = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDonHangSlotSteam(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer insert(DonHangSlotSteam entity) throws SQLException {
        String sql = """
            INSERT INTO DONHANG_SLOT_STEAM 
            (MaNguoiDung, MaGameSteam, MaTaiKhoanSteam, GiaMua, ThoiGianMua, TrangThai, ThoiGianTao)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setObject(1, entity.getMaNguoiDung());
            ps.setObject(2, entity.getMaGameSteam());
            ps.setObject(3, entity.getMaTaiKhoanSteam());
            ps.setBigDecimal(4, entity.getGiaMua());

            ps.setTimestamp(5, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);
            ps.setString(6, entity.getTrangThai());
            ps.setTimestamp(7, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : Timestamp.valueOf(LocalDateTime.now()));

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

    @Override
    public boolean update(DonHangSlotSteam entity) throws SQLException {
        String sql = """
            UPDATE DONHANG_SLOT_STEAM
            SET MaNguoiDung = ?, MaGameSteam = ?, MaTaiKhoanSteam = ?, GiaMua = ?, 
                ThoiGianMua = ?, TrangThai = ?, ThoiGianTao = ?
            WHERE MaDonHangSlot = ?
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, entity.getMaNguoiDung());
            ps.setObject(2, entity.getMaGameSteam());
            ps.setObject(3, entity.getMaTaiKhoanSteam());
            ps.setBigDecimal(4, entity.getGiaMua());

            ps.setTimestamp(5, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);

            ps.setString(6, entity.getTrangThai());

            ps.setTimestamp(7, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : null);

            ps.setObject(8, entity.getMaDonHangSlot());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM DONHANG_SLOT_STEAM WHERE MaDonHangSlot = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<DonHangSlotSteam> getByMaNguoiDung(Integer maNguoiDung) throws SQLException {
        List<DonHangSlotSteam> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG_SLOT_STEAM WHERE MaNguoiDung = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maNguoiDung);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHangSlotSteam(rs));
                }
            }
        }
        return list;
    }

    public List<DonHangSlotSteam> getByMaTaiKhoanSteam(Integer maTaiKhoanSteam) throws SQLException {
        List<DonHangSlotSteam> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG_SLOT_STEAM WHERE MaTaiKhoanSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoanSteam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHangSlotSteam(rs));
                }
            }
        }
        return list;
    }

    public List<DonHangSlotSteam> getByMaGameSteam(Integer maGameSteam) throws SQLException {
        List<DonHangSlotSteam> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG_SLOT_STEAM WHERE MaGameSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maGameSteam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHangSlotSteam(rs));
                }
            }
        }
        return list;
    }

    public boolean updateTrangThai(Integer maDonHangSlot, String trangThaiMoi, LocalDateTime thoiGianMua) throws SQLException {
        String sql = "UPDATE DONHANG_SLOT_STEAM SET TrangThai = ?, ThoiGianMua = ? WHERE MaDonHangSlot = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThaiMoi);
            ps.setTimestamp(2, (thoiGianMua != null ? Timestamp.valueOf(thoiGianMua) : null));
            ps.setObject(3, maDonHangSlot);

            return ps.executeUpdate() > 0;
        }
    }

    public List<DonHangSlotSteam> getByTrangThai(String trangThai) throws SQLException {
        List<DonHangSlotSteam> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG_SLOT_STEAM WHERE TrangThai = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHangSlotSteam(rs));
                }
            }
        }
        return list;
    }
}
