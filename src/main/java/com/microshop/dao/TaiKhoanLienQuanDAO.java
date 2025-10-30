package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanLienQuan;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanLienQuanDAO implements CrudDAO<TaiKhoanLienQuan, Integer> {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    
    // Constructor 1: Dùng cho ứng dụng chạy thật
    public TaiKhoanLienQuanDAO() {
        this.taiKhoanDAO = new TaiKhoanDAO();
    }
    
    // Constructor 2: Dùng cho Unit Test (package-private)
    TaiKhoanLienQuanDAO(TaiKhoanDAO taiKhoanDAO) {
        this.taiKhoanDAO = taiKhoanDAO;
    }

    @Override
    public List<TaiKhoanLienQuan> getAll() throws SQLException {
        List<TaiKhoanLienQuan> list = new ArrayList<>();
        String sql = """
            SELECT tk.*, tklq.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_LIENQUAN tklq ON tk.MaTaiKhoan = tklq.MaTaiKhoan
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToTaiKhoanLienQuan(rs));
            }
        }
        return list;
    }

    @Override
    public TaiKhoanLienQuan getById(Integer maTaiKhoan) throws SQLException {
        String sql = """
            SELECT tk.*, tklq.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_LIENQUAN tklq ON tk.MaTaiKhoan = tklq.MaTaiKhoan
            WHERE tk.MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTaiKhoanLienQuan(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer insert(TaiKhoanLienQuan acc) throws SQLException {
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
            throw new SQLException("Insert TAIKHOAN (cha) thất bại, không thể tạo TAIKHOAN_LIENQUAN (con).");
        }
        acc.setMaTaiKhoan(maTaiKhoan);

        String sql = """
            INSERT INTO TAIKHOAN_LIENQUAN
            (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, acc.getMaTaiKhoan());
            ps.setString(2, acc.getTenDangNhap());
            ps.setString(3, acc.getMatKhau());
            ps.setString(4, acc.getHangRank());
            ps.setObject(5, acc.getSoTuong());
            ps.setObject(6, acc.getSoTrangPhuc());
            ps.setObject(7, acc.getBacNgoc());
            ps.setString(8, acc.getLoaiDangKy());
            ps.executeUpdate();
        }

        return maTaiKhoan;
    }

    @Override
    public boolean update(TaiKhoanLienQuan acc) throws SQLException {
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
            UPDATE TAIKHOAN_LIENQUAN
            SET TenDangNhap = ?, MatKhau = ?, HangRank = ?, SoTuong = ?, SoTrangPhuc = ?, BacNgoc = ?, LoaiDangKy = ?
            WHERE MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getTenDangNhap());
            ps.setString(2, acc.getMatKhau());
            ps.setString(3, acc.getHangRank());
            ps.setObject(4, acc.getSoTuong());
            ps.setObject(5, acc.getSoTrangPhuc());
            ps.setObject(6, acc.getBacNgoc());
            ps.setString(7, acc.getLoaiDangKy());
            ps.setObject(8, acc.getMaTaiKhoan());

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

    public List<TaiKhoanLienQuan> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoanLienQuan> list = new ArrayList<>();
        String sql = """
            SELECT tk.*, tklq.* FROM TAIKHOAN_LIENQUAN tklq
            JOIN TAIKHOAN tk ON tklq.MaTaiKhoan = tk.MaTaiKhoan
            WHERE tk.TrangThai = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanLienQuan(rs));
                }
            }
        }
        return list;
    }

    private TaiKhoanLienQuan mapResultSetToTaiKhoanLienQuan(ResultSet rs) throws SQLException {
        TaiKhoanLienQuan acc = new TaiKhoanLienQuan();

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
        acc.setHangRank(rs.getString("HangRank"));
        acc.setSoTuong(rs.getObject("SoTuong", Integer.class));
        acc.setSoTrangPhuc(rs.getObject("SoTrangPhuc", Integer.class));
        acc.setBacNgoc(rs.getObject("BacNgoc", Integer.class));
        acc.setLoaiDangKy(rs.getString("LoaiDangKy"));
        return acc;
    }

    // Đã fix các lỗi by Hưng:
    // Sửa logic delete để chỉ cần gọi delete của lớp DAO cha thôi,
    // "ON DELETE CASCADE" trong schema sẽ đảm bảo tài khoản tương ứng bị xóa
    // Xóa insertAndReturnId và chỉ dùng insert đúng như yêu cầu là phải trả về đối tượng hoặc null
    // Sửa getAll/getById của TaiKhoanLienQuanDAO để JOIN 2 bảng ( code cũ thiếu JOIN với bảng gốc )
    // Sửa hết setInt/getInt sang setObject/getObject để xử lý null an toàn
    // Xóa getByMaDanhMuc, theo thiết kế của CSDL thì hàm này là vô nghĩa, TaiKhoanDAO.getByMaDanhMuc(lien quan) mới là cách dùng đúng
}
