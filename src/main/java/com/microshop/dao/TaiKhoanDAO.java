package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO implements CrudDAO<TaiKhoan, Integer> {

    // ---------------------- CRUD chuẩn ----------------------

    @Override
    public List<TaiKhoan> getAll() throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapResultSetToTaiKhoan(rs));
        }
        return list;
    }

    @Override
    public TaiKhoan getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN WHERE MaTaiKhoan = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapResultSetToTaiKhoan(rs);
            }
        }
        return null;
    }

    @Override
    public Integer insert(TaiKhoan newAcc) throws SQLException {
        String sql = """
                INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, newAcc.getMaDanhMuc());
            ps.setBigDecimal(2, newAcc.getGiaGoc());
            ps.setBigDecimal(3, newAcc.getGiaBan());
            ps.setString(4, newAcc.getTrangThai());
            ps.setString(5, newAcc.getDiemNoiBat());
            ps.setInt(6, newAcc.getLuotXem());
            ps.setTimestamp(7, Timestamp.valueOf(
                    newAcc.getThoiGianDang() != null ? newAcc.getThoiGianDang() : LocalDateTime.now())
            );
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return null;
    }

    @Override
    public boolean update(TaiKhoan entity) throws SQLException {
        String sql = """
                UPDATE TAIKHOAN
                SET MaDanhMuc = ?, GiaGoc = ?, GiaBan = ?, TrangThai = ?, DiemNoiBat = ?, LuotXem = ?, thoiGianDang = ?
                WHERE MaTaiKhoan = ?
                """;
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entity.getMaDanhMuc());
            ps.setBigDecimal(2, entity.getGiaGoc());
            ps.setBigDecimal(3, entity.getGiaBan());
            ps.setString(4, entity.getTrangThai());
            ps.setString(5, entity.getDiemNoiBat());
            ps.setInt(6, entity.getLuotXem());
            ps.setTimestamp(7, Timestamp.valueOf(entity.getThoiGianDang()));
            ps.setInt(8, entity.getMaTaiKhoan());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM TAIKHOAN WHERE MaTaiKhoan = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------- Hàm mở rộng riêng ----------------------

    /** Lấy danh sách tài khoản theo mã danh mục */
    public List<TaiKhoan> getByMaDanhMuc(Integer maDanhMuc) throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN WHERE MaDanhMuc = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDanhMuc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToTaiKhoan(rs));
            }
        }
        return list;
    }

    /** Lấy danh sách tài khoản theo trạng thái */
    public List<TaiKhoan> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN WHERE TrangThai = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToTaiKhoan(rs));
            }
        }
        return list;
    }

    /** Cập nhật trạng thái tài khoản */
    public void updateTrangThai(Integer maTaiKhoan, String trangThaiMoi) throws SQLException {
        String sql = "UPDATE TAIKHOAN SET TrangThai = ? WHERE MaTaiKhoan = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThaiMoi);
            ps.setInt(2, maTaiKhoan);
            ps.executeUpdate();
        }
    }

    // ---------------------- Hàm tiện ích ----------------------

    private TaiKhoan mapResultSetToTaiKhoan(ResultSet rs) throws SQLException {
        TaiKhoan tk = new TaiKhoan();
        tk.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
        tk.setMaDanhMuc(rs.getInt("MaDanhMuc"));
        tk.setGiaGoc(rs.getBigDecimal("GiaGoc"));
        tk.setGiaBan(rs.getBigDecimal("GiaBan"));
        tk.setTrangThai(rs.getString("TrangThai"));
        tk.setDiemNoiBat(rs.getString("DiemNoiBat"));
        tk.setLuotXem(rs.getInt("LuotXem"));
        Timestamp ts = rs.getTimestamp("ThoiGianDang");
        if (ts != null) tk.setThoiGianDang(ts.toLocalDateTime());
        return tk;
    }
    public int insertAndReturnId(TaiKhoan acc) throws SQLException {
        String sql = """
            INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, acc.getMaDanhMuc());
            ps.setBigDecimal(2, acc.getGiaGoc());
            ps.setBigDecimal(3, acc.getGiaBan());
            ps.setString(4, acc.getTrangThai());
            ps.setString(5, acc.getDiemNoiBat());
            ps.setInt(6, acc.getLuotXem());
            ps.setTimestamp(7, Timestamp.valueOf(acc.getThoiGianDang()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

}
