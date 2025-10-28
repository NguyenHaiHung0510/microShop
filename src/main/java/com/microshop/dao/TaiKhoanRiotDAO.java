package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanRiot;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanRiotDAO implements CrudDAO<TaiKhoanRiot, Integer> {

    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    // ---------------------- GET ALL ----------------------
    @Override
    public List<TaiKhoanRiot> getAll() throws SQLException {
        List<TaiKhoanRiot> list = new ArrayList<>();

        String sql = """
            SELECT tk.*, r.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_RIOT r ON tk.maTaiKhoan = r.maTaiKhoan
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToTaiKhoanRiot(rs));
            }
        }

        return list;
    }

    // ---------------------- GET BY ID ----------------------
    @Override
    public TaiKhoanRiot getById(Integer maTaiKhoan) throws SQLException {
        String sql = """
            SELECT tk.*, r.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_RIOT r ON tk.maTaiKhoan = r.maTaiKhoan
            WHERE tk.maTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTaiKhoanRiot(rs);
                }
            }
        }

        return null;
    }

    // ---------------------- INSERT ----------------------
    @Override
    public Integer insert(TaiKhoanRiot acc) throws SQLException {
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

        // Insert vào bảng con TAIKHOAN_RIOT
        String sql = """
            INSERT INTO TAIKHOAN_RIOT
            (maTaiKhoan, tenDangNhap, matKhau, capDoRiot, soTuongLMHT, soTrangPhucLMHT, 
             soDaSacLMHT, soBieuCamLMHT, soBieuTuongLMHT, hangRankLMHT, khungRankLMHT,
             soThuCungTFT, soSanDauTFT, soChuongLucTFT)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ps.setString(2, acc.getTenDangNhap());
            ps.setString(3, acc.getMatKhau());
            ps.setInt(4, acc.getCapDoRiot());
            ps.setInt(5, acc.getSoTuongLMHT());
            ps.setInt(6, acc.getSoTrangPhucLMHT());
            ps.setInt(7, acc.getSoDaSacLMHT());
            ps.setInt(8, acc.getSoBieuCamLMHT());
            ps.setInt(9, acc.getSoBieuTuongLMHT());
            ps.setString(10, acc.getHangRankLMHT());
            ps.setString(11, acc.getKhungRankLMHT());
            ps.setInt(12, acc.getSoThuCungTFT());
            ps.setInt(13, acc.getSoSanDauTFT());
            ps.setInt(14, acc.getSoChuongLucTFT());
            ps.executeUpdate();
        }

        return maTaiKhoan;
    }

    // ---------------------- UPDATE ----------------------
    @Override
    public boolean update(TaiKhoanRiot acc) throws SQLException {
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
            UPDATE TAIKHOAN_RIOT
            SET tenDangNhap = ?, matKhau = ?, capDoRiot = ?, soTuongLMHT = ?, soTrangPhucLMHT = ?, 
                soDaSacLMHT = ?, soBieuCamLMHT = ?, soBieuTuongLMHT = ?, hangRankLMHT = ?, 
                khungRankLMHT = ?, soThuCungTFT = ?, soSanDauTFT = ?, soChuongLucTFT = ?
            WHERE maTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getTenDangNhap());
            ps.setString(2, acc.getMatKhau());
            ps.setInt(3, acc.getCapDoRiot());
            ps.setInt(4, acc.getSoTuongLMHT());
            ps.setInt(5, acc.getSoTrangPhucLMHT());
            ps.setInt(6, acc.getSoDaSacLMHT());
            ps.setInt(7, acc.getSoBieuCamLMHT());
            ps.setInt(8, acc.getSoBieuTuongLMHT());
            ps.setString(9, acc.getHangRankLMHT());
            ps.setString(10, acc.getKhungRankLMHT());
            ps.setInt(11, acc.getSoThuCungTFT());
            ps.setInt(12, acc.getSoSanDauTFT());
            ps.setInt(13, acc.getSoChuongLucTFT());
            ps.setInt(14, acc.getMaTaiKhoan());

            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------- DELETE ----------------------
    @Override
    public boolean delete(Integer maTaiKhoan) throws SQLException {
        // Xóa bảng con trước
        String sql = "DELETE FROM TAIKHOAN_RIOT WHERE maTaiKhoan = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTaiKhoan);
            ps.executeUpdate();
        }

        // Sau đó xóa bảng cha
        return taiKhoanDAO.delete(maTaiKhoan);
    }

    // ---------------------- GET BY MA DANH MUC ----------------------
    public List<TaiKhoanRiot> getByMaDanhMuc(Integer maDanhMuc) throws SQLException {
        List<TaiKhoanRiot> list = new ArrayList<>();

        String sql = """
            SELECT tk.*, r.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_RIOT r ON tk.maTaiKhoan = r.maTaiKhoan
            WHERE tk.maDanhMuc = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDanhMuc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanRiot(rs));
                }
            }
        }

        return list;
    }

    // ---------------------- GET BY TRANG THAI ----------------------
    public List<TaiKhoanRiot> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoanRiot> list = new ArrayList<>();

        String sql = """
            SELECT tk.*, r.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_RIOT r ON tk.maTaiKhoan = r.maTaiKhoan
            WHERE tk.trangThai = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanRiot(rs));
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
    private TaiKhoanRiot mapResultSetToTaiKhoanRiot(ResultSet rs) throws SQLException {
        TaiKhoanRiot acc = new TaiKhoanRiot();

        // ----- Thuộc tính từ bảng TAIKHOAN -----
        acc.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
        acc.setMaDanhMuc(rs.getInt("maDanhMuc"));
        acc.setGiaGoc(rs.getBigDecimal("giaGoc"));
        acc.setGiaBan(rs.getBigDecimal("giaBan"));
        acc.setTrangThai(rs.getString("trangThai"));
        acc.setDiemNoiBat(rs.getString("diemNoiBat"));
        acc.setLuotXem(rs.getInt("luotXem"));
        Timestamp ts = rs.getTimestamp("thoiGianDang");
        if (ts != null) acc.setThoiGianDang(ts.toLocalDateTime());

        // ----- Thuộc tính từ bảng TAIKHOAN_RIOT -----
        acc.setTenDangNhap(rs.getString("tenDangNhap"));
        acc.setMatKhau(rs.getString("matKhau"));
        acc.setCapDoRiot(rs.getInt("capDoRiot"));
        acc.setSoTuongLMHT(rs.getInt("soTuongLMHT"));
        acc.setSoTrangPhucLMHT(rs.getInt("soTrangPhucLMHT"));
        acc.setSoDaSacLMHT(rs.getInt("soDaSacLMHT"));
        acc.setSoBieuCamLMHT(rs.getInt("soBieuCamLMHT"));
        acc.setSoBieuTuongLMHT(rs.getInt("soBieuTuongLMHT"));
        acc.setHangRankLMHT(rs.getString("hangRankLMHT"));
        acc.setKhungRankLMHT(rs.getString("khungRankLMHT"));
        acc.setSoThuCungTFT(rs.getInt("soThuCungTFT"));
        acc.setSoSanDauTFT(rs.getInt("soSanDauTFT"));
        acc.setSoChuongLucTFT(rs.getInt("soChuongLucTFT"));
        return acc;
    }
}
