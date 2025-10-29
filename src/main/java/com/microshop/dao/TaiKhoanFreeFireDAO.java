package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanFreeFire;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanFreeFireDAO implements CrudDAO<TaiKhoanFreeFire, Integer> {

    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    // ---------------------- GET ALL ----------------------
    @Override
    public List<TaiKhoanFreeFire> getAll() throws SQLException {
        List<TaiKhoanFreeFire> list = new ArrayList<>();

        String sql = """
            SELECT tk.*, ff.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_FREEFIRE ff ON tk.MaTaiKhoan = ff.MaTaiKhoan
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToTaiKhoanFreeFire(rs));
            }
        }

        return list;
    }

    // ---------------------- GET BY ID ----------------------
    @Override
    public TaiKhoanFreeFire getById(Integer maTaiKhoan) throws SQLException {
        String sql = """
            SELECT tk.*, ff.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_FREEFIRE ff ON tk.MaTaiKhoan = ff.MaTaiKhoan
            WHERE tk.MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTaiKhoanFreeFire(rs);
                }
            }
        }

        return null;
    }

    // ---------------------- INSERT ----------------------
    @Override
    public Integer insert(TaiKhoanFreeFire acc) throws SQLException {
        // Insert vào bảng cha TAIKHOAN
        TaiKhoan tk = new TaiKhoan();
        tk.setMaDanhMuc(acc.getMaDanhMuc());
        tk.setGiaGoc(acc.getGiaGoc());
        tk.setGiaBan(acc.getGiaBan());
        tk.setTrangThai(acc.getTrangThai());
        tk.setDiemNoiBat(acc.getDiemNoiBat());
        tk.setLuotXem(acc.getLuotXem());
        tk.setThoiGianDang(acc.getThoiGianDang() != null ? acc.getThoiGianDang() : LocalDateTime.now());

        int maTaiKhoan = taiKhoanDAO.insertAndReturnId(tk);
        acc.setMaTaiKhoan(maTaiKhoan);

        // Insert vào bảng con TAIKHOAN_FREEFIRE
        String sql = """
            INSERT INTO TAIKHOAN_FREEFIRE
            (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ps.setString(2, acc.getTenDangNhap());
            ps.setString(3, acc.getMatKhau());
            ps.setBoolean(4, acc.getCoTheVoCuc());
            ps.setInt(5, acc.getSoSkinSung());
            ps.setString(6, acc.getHangRank());
            ps.setString(7, acc.getLoaiDangKy());
            ps.executeUpdate();
        }

        return maTaiKhoan;
    }

    // ---------------------- UPDATE ----------------------
    @Override
    public boolean update(TaiKhoanFreeFire acc) throws SQLException {
        // Cập nhật bảng cha
        TaiKhoan tk = new TaiKhoan();
        tk.setMaTaiKhoan(acc.getMaTaiKhoan());
        tk.setMaDanhMuc(acc.getMaDanhMuc());
        tk.setGiaGoc(acc.getGiaGoc());
        tk.setGiaBan(acc.getGiaBan());
        tk.setTrangThai(acc.getTrangThai());
        tk.setDiemNoiBat(acc.getDiemNoiBat());
        tk.setLuotXem(acc.getLuotXem());
        tk.setThoiGianDang(acc.getThoiGianDang());
        taiKhoanDAO.update(tk);

        // Cập nhật bảng con
        String sql = """
            UPDATE TAIKHOAN_FREEFIRE
            SET TenDangNhap = ?, MatKhau = ?, CoTheVoCuc = ?, SoSkinSung = ?, HangRank = ?, LoaiDangKy = ?
            WHERE MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getTenDangNhap());
            ps.setString(2, acc.getMatKhau());
            ps.setBoolean(3, acc.getCoTheVoCuc());
            ps.setInt(4, acc.getSoSkinSung());
            ps.setString(5, acc.getHangRank());
            ps.setString(6, acc.getLoaiDangKy());
            ps.setInt(7, acc.getMaTaiKhoan());

            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------- DELETE ----------------------
    @Override
    public boolean delete(Integer maTaiKhoan) throws SQLException {
        // Xóa bảng con trước (tránh lỗi khóa ngoại)
        String sql = "DELETE FROM TAIKHOAN_FREEFIRE WHERE MaTaiKhoan = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTaiKhoan);
            ps.executeUpdate();
        }

        // Sau đó xóa bảng cha
        return taiKhoanDAO.delete(maTaiKhoan);
    }

    // ---------------------- GET BY MA DANH MUC ----------------------
    public List<TaiKhoanFreeFire> getByMaDanhMuc(Integer maDanhMuc) throws SQLException {
        List<TaiKhoanFreeFire> list = new ArrayList<>();

        String sql = """
            SELECT tk.*, ff.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_FREEFIRE ff ON tk.MaTaiKhoan = ff.MaTaiKhoan
            WHERE tk.MaDanhMuc = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDanhMuc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanFreeFire(rs));
                }
            }
        }

        return list;
    }

    // ---------------------- GET BY TRANG THAI ----------------------
    public List<TaiKhoanFreeFire> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoanFreeFire> list = new ArrayList<>();

        String sql = """
            SELECT tk.*, ff.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_FREEFIRE ff ON tk.MaTaiKhoan = ff.MaTaiKhoan
            WHERE tk.TrangThai = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanFreeFire(rs));
                }
            }
        }

        return list;
    }

    // ---------------------- UPDATE TRANG THAI ----------------------
    public void updateTrangThai(Integer maTaiKhoan, String trangThaiMoi) throws SQLException {
        taiKhoanDAO.updateTrangThai(maTaiKhoan, trangThaiMoi);
    }

    // ---------------------- MAPPING HELPER ----------------------
    private TaiKhoanFreeFire mapResultSetToTaiKhoanFreeFire(ResultSet rs) throws SQLException {
        TaiKhoanFreeFire acc = new TaiKhoanFreeFire();
        acc.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
        acc.setMaDanhMuc(rs.getInt("MaDanhMuc"));
        acc.setGiaGoc(rs.getBigDecimal("GiaGoc"));
        acc.setGiaBan(rs.getBigDecimal("GiaBan"));
        acc.setTrangThai(rs.getString("TrangThai"));
        acc.setDiemNoiBat(rs.getString("DiemNoiBat"));
        acc.setLuotXem(rs.getInt("LuotXem"));
        Timestamp ts = rs.getTimestamp("ThoiGianDang");
        if (ts != null) acc.setThoiGianDang(ts.toLocalDateTime());

        acc.setTenDangNhap(rs.getString("TenDangNhap"));
        acc.setMatKhau(rs.getString("MatKhau"));
        acc.setCoTheVoCuc(rs.getBoolean("CoTheVoCuc"));
        acc.setSoSkinSung(rs.getInt("SoSkinSung"));
        acc.setHangRank(rs.getString("HangRank"));
        acc.setLoaiDangKy(rs.getString("LoaiDangKy"));
        return acc;
    }
}
