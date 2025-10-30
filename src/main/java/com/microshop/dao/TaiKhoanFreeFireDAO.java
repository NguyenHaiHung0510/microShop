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

    @Override
    public List<TaiKhoanFreeFire> getAll() throws SQLException {
        List<TaiKhoanFreeFire> list = new ArrayList<>();
        String sql = """
            SELECT tk.*, ff.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_FREEFIRE ff ON tk.MaTaiKhoan = ff.MaTaiKhoan
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToTaiKhoanFreeFire(rs));
            }
        }
        return list;
    }

    @Override
    public TaiKhoanFreeFire getById(Integer maTaiKhoan) throws SQLException {
        String sql = """
            SELECT tk.*, ff.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_FREEFIRE ff ON tk.MaTaiKhoan = ff.MaTaiKhoan
            WHERE tk.MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTaiKhoanFreeFire(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer insert(TaiKhoanFreeFire acc) throws SQLException {
        TaiKhoan tk = new TaiKhoan();
        tk.setMaDanhMuc(acc.getMaDanhMuc());
        tk.setGiaGoc(acc.getGiaGoc());
        tk.setGiaBan(acc.getGiaBan());
        tk.setTrangThai(acc.getTrangThai());
        tk.setDiemNoiBat(acc.getDiemNoiBat());
        tk.setLuotXem(acc.getLuotXem());
        tk.setThoiGianDang(acc.getThoiGianDang() != null ? acc.getThoiGianDang() : LocalDateTime.now());

        Integer maTaiKhoan = taiKhoanDAO.insert(tk);
        if (maTaiKhoan == null) {
            throw new SQLException("Insert TAIKHOAN (cha) thất bại, không thể tạo TAIKHOAN_FREEFIRE (con).");
        }
        acc.setMaTaiKhoan(maTaiKhoan);

        String sql = """
            INSERT INTO TAIKHOAN_FREEFIRE
            (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);
            ps.setString(2, acc.getTenDangNhap());
            ps.setString(3, acc.getMatKhau());
            ps.setBoolean(4, acc.getCoTheVoCuc() != null ? acc.getCoTheVoCuc() : false);
            ps.setObject(5, acc.getSoSkinSung());
            ps.setString(6, acc.getHangRank());
            ps.setString(7, acc.getLoaiDangKy());
            ps.executeUpdate();
        }
        return maTaiKhoan;
    }

    @Override
    public boolean update(TaiKhoanFreeFire acc) throws SQLException {
        TaiKhoan tk = new TaiKhoan();
        tk.setMaTaiKhoan(acc.getMaTaiKhoan());
        tk.setMaDanhMuc(acc.getMaDanhMuc());
        tk.setGiaGoc(acc.getGiaGoc());
        tk.setGiaBan(acc.getGiaBan());
        tk.setTrangThai(acc.getTrangThai());
        tk.setDiemNoiBat(acc.getDiemNoiBat());
        tk.setLuotXem(acc.getLuotXem());
        tk.setThoiGianDang(acc.getThoiGianDang());
        boolean parentUpdated = taiKhoanDAO.update(tk);

        String sql = """
            UPDATE TAIKHOAN_FREEFIRE
            SET TenDangNhap = ?, MatKhau = ?, CoTheVoCuc = ?, SoSkinSung = ?, HangRank = ?, LoaiDangKy = ?
            WHERE MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getTenDangNhap());
            ps.setString(2, acc.getMatKhau());
            ps.setBoolean(3, acc.getCoTheVoCuc() != null ? acc.getCoTheVoCuc() : false);
            ps.setObject(4, acc.getSoSkinSung());
            ps.setString(5, acc.getHangRank());
            ps.setString(6, acc.getLoaiDangKy());
            ps.setObject(7, acc.getMaTaiKhoan());

            boolean childUpdated = ps.executeUpdate() > 0;
            return parentUpdated || childUpdated;
        }
    }

    @Override
    public boolean delete(Integer maTaiKhoan) throws SQLException {
        return taiKhoanDAO.delete(maTaiKhoan);
    }

    public void updateTrangThai(Integer maTaiKhoan, String trangThaiMoi) throws SQLException {
        taiKhoanDAO.updateTrangThai(maTaiKhoan, trangThaiMoi);
    }

    public List<TaiKhoanFreeFire> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoanFreeFire> list = new ArrayList<>();
        String sql = """
            SELECT tk.*, ff.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_FREEFIRE ff ON tk.MaTaiKhoan = ff.MaTaiKhoan
            WHERE tk.TrangThai = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanFreeFire(rs));
                }
            }
        }
        return list;
    }

    private TaiKhoanFreeFire mapResultSetToTaiKhoanFreeFire(ResultSet rs) throws SQLException {
        TaiKhoanFreeFire acc = new TaiKhoanFreeFire();

        acc.setMaTaiKhoan(rs.getObject("MaTaiKhoan", Integer.class));
        acc.setMaDanhMuc(rs.getObject("MaDanhMuc", Integer.class));
        acc.setGiaGoc(rs.getBigDecimal("GiaGoc"));
        acc.setGiaBan(rs.getBigDecimal("GiaBan"));
        acc.setTrangThai(rs.getString("TrangThai"));
        acc.setDiemNoiBat(rs.getString("DiemNoiBat"));
        acc.setLuotXem(rs.getObject("LuotXem", Integer.class));
        Timestamp ts = rs.getTimestamp("ThoiGianDang");
        if (ts != null) {
            acc.setThoiGianDang(ts.toLocalDateTime());
        }

        acc.setTenDangNhap(rs.getString("TenDangNhap"));
        acc.setMatKhau(rs.getString("MatKhau"));
        acc.setCoTheVoCuc(rs.getBoolean("CoTheVoCuc"));
        acc.setSoSkinSung(rs.getObject("SoSkinSung", Integer.class));
        acc.setHangRank(rs.getString("HangRank"));
        acc.setLoaiDangKy(rs.getString("LoaiDangKy"));
        return acc;
    }

    // Đã fix các lỗi by Hưng:
    // Sửa logic delete để chỉ cần gọi delete của lớp DAO cha thôi,
    // "ON DELETE CASCADE" trong schema sẽ đảm bảo tài khoản tương ứng bị xóa
    // Xóa insertAndReturnId và chỉ dùng insert đúng như yêu cầu là phải trả về đối tượng hoặc null
    // Sửa hết setInt/getInt sang setObject/getObject để xử lý null an toàn
    // Xóa getByMaDanhMuc, theo thiết kế của CSDL thì hàm này là vô nghĩa, TaiKhoanDAO.getByMaDanhMuc(ff) mới là cách dùng đúng
}
