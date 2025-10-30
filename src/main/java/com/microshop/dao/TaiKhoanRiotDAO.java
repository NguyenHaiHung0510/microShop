package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanRiot;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanRiotDAO implements CrudDAO<TaiKhoanRiot, Integer> {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public TaiKhoanRiotDAO() {
        this.taiKhoanDAO = new TaiKhoanDAO();
    }

    // Constructor 2: Dùng cho Unit Test (package-private)
    TaiKhoanRiotDAO(TaiKhoanDAO taiKhoanDAO) {
        this.taiKhoanDAO = taiKhoanDAO;
    }

    @Override
    public List<TaiKhoanRiot> getAll() throws SQLException {
        List<TaiKhoanRiot> list = new ArrayList<>();
        String sql = """
            SELECT tk.*, r.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_RIOT r ON tk.MaTaiKhoan = r.MaTaiKhoan
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToTaiKhoanRiot(rs));
            }
        }
        return list;
    }

    @Override
    public TaiKhoanRiot getById(Integer maTaiKhoan) throws SQLException {
        String sql = """
            SELECT tk.*, r.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_RIOT r ON tk.MaTaiKhoan = r.MaTaiKhoan
            WHERE tk.MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);
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
            throw new SQLException("Insert TAIKHOAN (cha) thất bại, không thể tạo TAIKHOAN_RIOT (con).");
        }
        acc.setMaTaiKhoan(maTaiKhoan);

        String sql = """
            INSERT INTO TAIKHOAN_RIOT
            (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, 
             SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT,
             SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);
            ps.setString(2, acc.getTenDangNhap());
            ps.setString(3, acc.getMatKhau());
            ps.setObject(4, acc.getCapDoRiot());
            ps.setObject(5, acc.getSoTuongLMHT());
            ps.setObject(6, acc.getSoTrangPhucLMHT());
            ps.setObject(7, acc.getSoDaSacLMHT());
            ps.setObject(8, acc.getSoBieuCamLMHT());
            ps.setObject(9, acc.getSoBieuTuongLMHT());
            ps.setString(10, acc.getHangRankLMHT());
            ps.setString(11, acc.getKhungRankLMHT());
            ps.setObject(12, acc.getSoThuCungTFT());
            ps.setObject(13, acc.getSoSanDauTFT());
            ps.setObject(14, acc.getSoChuongLucTFT());
            ps.executeUpdate();
        }
        return maTaiKhoan;
    }

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
        boolean parentUpdated = taiKhoanDAO.update(tk);

        String sql = """
            UPDATE TAIKHOAN_RIOT
            SET TenDangNhap = ?, MatKhau = ?, CapDoRiot = ?, SoTuongLMHT = ?, SoTrangPhucLMHT = ?, 
                SoDaSacLMHT = ?, SoBieuCamLMHT = ?, SoBieuTuongLMHT = ?, HangRankLMHT = ?, 
                KhungRankLMHT = ?, SoThuCungTFT = ?, SoSanDauTFT = ?, SoChuongLucTFT = ?
            WHERE MaTaiKhoan = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getTenDangNhap());
            ps.setString(2, acc.getMatKhau());
            ps.setObject(3, acc.getCapDoRiot());
            ps.setObject(4, acc.getSoTuongLMHT());
            ps.setObject(5, acc.getSoTrangPhucLMHT());
            ps.setObject(6, acc.getSoDaSacLMHT());
            ps.setObject(7, acc.getSoBieuCamLMHT());
            ps.setObject(8, acc.getSoBieuTuongLMHT());
            ps.setString(9, acc.getHangRankLMHT());
            ps.setString(10, acc.getKhungRankLMHT());
            ps.setObject(11, acc.getSoThuCungTFT());
            ps.setObject(12, acc.getSoSanDauTFT());
            ps.setObject(13, acc.getSoChuongLucTFT());
            ps.setObject(14, acc.getMaTaiKhoan());

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

    public List<TaiKhoanRiot> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoanRiot> list = new ArrayList<>();
        String sql = """
            SELECT tk.*, r.*
            FROM TAIKHOAN tk
            JOIN TAIKHOAN_RIOT r ON tk.MaTaiKhoan = r.MaTaiKhoan
            WHERE tk.TrangThai = ?
            """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanRiot(rs));
                }
            }
        }
        return list;
    }

    private TaiKhoanRiot mapResultSetToTaiKhoanRiot(ResultSet rs) throws SQLException {
        TaiKhoanRiot acc = new TaiKhoanRiot();

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
        acc.setCapDoRiot(rs.getObject("CapDoRiot", Integer.class));
        acc.setSoTuongLMHT(rs.getObject("SoTuongLMHT", Integer.class));
        acc.setSoTrangPhucLMHT(rs.getObject("SoTrangPhucLMHT", Integer.class));
        acc.setSoDaSacLMHT(rs.getObject("SoDaSacLMHT", Integer.class));
        acc.setSoBieuCamLMHT(rs.getObject("SoBieuCamLMHT", Integer.class));
        acc.setSoBieuTuongLMHT(rs.getObject("SoBieuTuongLMHT", Integer.class));
        acc.setHangRankLMHT(rs.getString("HangRankLMHT"));
        acc.setKhungRankLMHT(rs.getString("KhungRankLMHT"));
        acc.setSoThuCungTFT(rs.getObject("SoThuCungTFT", Integer.class));
        acc.setSoSanDauTFT(rs.getObject("SoSanDauTFT", Integer.class));
        acc.setSoChuongLucTFT(rs.getObject("SoChuongLucTFT", Integer.class));
        return acc;
    }

    // Đã fix các lỗi by Hưng:
    // Sửa logic delete để chỉ cần gọi delete của lớp DAO cha thôi,
    // "ON DELETE CASCADE" trong schema sẽ đảm bảo tài khoản tương ứng bị xóa
    // Xóa insertAndReturnId và chỉ dùng insert đúng như yêu cầu là phải trả về đối tượng hoặc null
    // Sửa hết setInt/getInt sang setObject/getObject để xử lý null an toàn
    // Xóa getByMaDanhMuc, theo thiết kế của CSDL thì hàm này là vô nghĩa, TaiKhoanDAO.getByMaDanhMuc(riot) mới là cách dùng đúng
}
