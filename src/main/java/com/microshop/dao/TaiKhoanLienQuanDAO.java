package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanLienQuan;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO cho bảng TAIKHOAN_LIENQUAN
 * Kế thừa logic từ TaiKhoanDAO (cha) và triển khai CRUD đầy đủ.
 */
public class TaiKhoanLienQuanDAO implements CrudDAO<TaiKhoanLienQuan, Integer> {

    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    // ---------------------- GET ALL ----------------------
    @Override
    public List<TaiKhoanLienQuan> getAll() throws SQLException {
        List<TaiKhoanLienQuan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN_LIENQUAN";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TaiKhoanLienQuan acc = mapResultSetToTaiKhoanLienQuan(rs);
                list.add(acc);
            }
        }

        return list;
    }

    // ---------------------- GET BY ID ----------------------
    @Override
    public TaiKhoanLienQuan getById(Integer maTaiKhoan) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN_LIENQUAN WHERE maTaiKhoan = ?";
        TaiKhoanLienQuan acc = null;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    acc = mapResultSetToTaiKhoanLienQuan(rs);
                }
            }
        }

        return acc;
    }

    // ---------------------- INSERT ----------------------
    @Override
    public Integer insert(TaiKhoanLienQuan acc) throws SQLException {
        // 1️⃣ Insert vào bảng cha trước
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

        // 2️⃣ Insert vào bảng con
        String sql = """
            INSERT INTO TAIKHOAN_LIENQUAN
            (maTaiKhoan, tenDangNhap, matKhau, hangRank, soTuong, soTrangPhuc, bacNgoc, loaiDangKy)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, acc.getMaTaiKhoan());
            ps.setString(2, acc.getTenDangNhap());
            ps.setString(3, acc.getMatKhau());
            ps.setString(4, acc.getHangRank());
            ps.setInt(5, acc.getSoTuong());
            ps.setInt(6, acc.getSoTrangPhuc());
            ps.setInt(7, acc.getBacNgoc());
            ps.setString(8, acc.getLoaiDangKy());
            ps.executeUpdate();
        }

        return maTaiKhoan;
    }

    // ---------------------- UPDATE ----------------------
    @Override
    public boolean update(TaiKhoanLienQuan acc) throws SQLException {
        // 1️⃣ Update bảng cha
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

        // 2️⃣ Update bảng con
        String sql = """
            UPDATE TAIKHOAN_LIENQUAN
            SET tenDangNhap = ?, matKhau = ?, hangRank = ?, soTuong = ?, soTrangPhuc = ?, bacNgoc = ?, loaiDangKy = ?
            WHERE maTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getTenDangNhap());
            ps.setString(2, acc.getMatKhau());
            ps.setString(3, acc.getHangRank());
            ps.setInt(4, acc.getSoTuong());
            ps.setInt(5, acc.getSoTrangPhuc());
            ps.setInt(6, acc.getBacNgoc());
            ps.setString(7, acc.getLoaiDangKy());
            ps.setInt(8, acc.getMaTaiKhoan());

            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------- DELETE ----------------------
    @Override
    public boolean delete(Integer maTaiKhoan) throws SQLException {
        // Xóa ở bảng con trước để tránh lỗi khóa ngoại
        String sql = "DELETE FROM TAIKHOAN_LIENQUAN WHERE maTaiKhoan = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTaiKhoan);
            ps.executeUpdate();
        }

        return taiKhoanDAO.delete(maTaiKhoan);
    }

    // ---------------------- GET BY MA DANH MUC ----------------------
    public List<TaiKhoanLienQuan> getByMaDanhMuc(Integer maDanhMuc) throws SQLException {
        List<TaiKhoanLienQuan> list = new ArrayList<>();
        String sql = """
            SELECT tklq.* FROM TAIKHOAN_LIENQUAN tklq
            JOIN TAIKHOAN tk ON tklq.maTaiKhoan = tk.maTaiKhoan
            WHERE tk.maDanhMuc = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDanhMuc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanLienQuan(rs));
                }
            }
        }

        return list;
    }

    // ---------------------- GET BY TRANG THAI ----------------------
    public List<TaiKhoanLienQuan> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoanLienQuan> list = new ArrayList<>();
        String sql = """
            SELECT tklq.* FROM TAIKHOAN_LIENQUAN tklq
            JOIN TAIKHOAN tk ON tklq.maTaiKhoan = tk.maTaiKhoan
            WHERE tk.trangThai = ?
            """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanLienQuan(rs));
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
    private TaiKhoanLienQuan mapResultSetToTaiKhoanLienQuan(ResultSet rs) throws SQLException {
        TaiKhoanLienQuan acc = new TaiKhoanLienQuan();
        acc.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
        acc.setTenDangNhap(rs.getString("tenDangNhap"));
        acc.setMatKhau(rs.getString("matKhau"));
        acc.setHangRank(rs.getString("hangRank"));
        acc.setSoTuong(rs.getInt("soTuong"));
        acc.setSoTrangPhuc(rs.getInt("soTrangPhuc"));
        acc.setBacNgoc(rs.getInt("bacNgoc"));
        acc.setLoaiDangKy(rs.getString("loaiDangKy"));
        return acc;
    }
}