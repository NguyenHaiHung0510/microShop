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

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToTaiKhoan(rs));
            }
        }
        return list;
    }
    
    @Override
    public TaiKhoan getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN WHERE MaTaiKhoan = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTaiKhoan(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer insert(TaiKhoan newAcc) throws SQLException {
        String sql = """
                INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setObject(1, newAcc.getMaDanhMuc());
            ps.setBigDecimal(2, newAcc.getGiaGoc());
            ps.setBigDecimal(3, newAcc.getGiaBan());
            ps.setString(4, newAcc.getTrangThai());
            ps.setString(5, newAcc.getDiemNoiBat());
            ps.setObject(6, newAcc.getLuotXem());
            ps.setTimestamp(7, Timestamp.valueOf(
                    newAcc.getThoiGianDang() != null ? newAcc.getThoiGianDang() : LocalDateTime.now())
            );
            ps.setString(8, newAcc.getDuongDanAnh());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(TaiKhoan entity) throws SQLException {
        String sql = """
                UPDATE TAIKHOAN
                SET MaDanhMuc = ?, GiaGoc = ?, GiaBan = ?, TrangThai = ?, DiemNoiBat = ?, LuotXem = ?, ThoiGianDang = ?, DuongDanAnh = ?
                WHERE MaTaiKhoan = ?
                """;
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, entity.getMaDanhMuc());
            ps.setBigDecimal(2, entity.getGiaGoc());
            ps.setBigDecimal(3, entity.getGiaBan());
            ps.setString(4, entity.getTrangThai());
            ps.setString(5, entity.getDiemNoiBat());
            ps.setObject(6, entity.getLuotXem());
            ps.setTimestamp(7, entity.getThoiGianDang() != null ? Timestamp.valueOf(entity.getThoiGianDang()) : null);
            ps.setString(8, entity.getDuongDanAnh());
            ps.setObject(9, entity.getMaTaiKhoan());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM TAIKHOAN WHERE MaTaiKhoan = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------- Hàm mở rộng riêng ----------------------
    public List<TaiKhoan> getByMaDanhMuc(Integer maDanhMuc) throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN WHERE MaDanhMuc = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maDanhMuc);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoan(rs));
                }
            }
        }
        return list;
    }

    public List<TaiKhoan> getByTrangThai(String trangThai) throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN WHERE TrangThai = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoan(rs));
                }
            }
        }
        return list;
    }
    
    public List<TaiKhoan> getAllByList(List<Integer> maTaiKhoan) throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        TaiKhoanLienQuanDAO lqdao = new TaiKhoanLienQuanDAO();
        TaiKhoanFreeFireDAO ffdao = new TaiKhoanFreeFireDAO();
        TaiKhoanRiotDAO rtdao = new TaiKhoanRiotDAO();
        
        for(int id : maTaiKhoan){
            TaiKhoan x = lqdao.getById(id), y = ffdao.getById(id), z = rtdao.getById(id);
            if(x != null) list.add(x);
            else if(y != null) list.add(y);
            else list.add(z);
        }
        
        return list;
    }

    public void updateTrangThai(Integer maTaiKhoan, String trangThaiMoi) throws SQLException {
        String sql = "UPDATE TAIKHOAN SET TrangThai = ? WHERE MaTaiKhoan = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThaiMoi);

            ps.setObject(2, maTaiKhoan);
            ps.executeUpdate();
        }
    }

    // ---------------------- Hàm tiện ích ----------------------
    private TaiKhoan mapResultSetToTaiKhoan(ResultSet rs) throws SQLException {
        TaiKhoan tk = new TaiKhoan();

        tk.setMaTaiKhoan(rs.getObject("MaTaiKhoan", Integer.class));
        tk.setMaDanhMuc(rs.getObject("MaDanhMuc", Integer.class));
        tk.setGiaGoc(rs.getBigDecimal("GiaGoc"));
        tk.setGiaBan(rs.getBigDecimal("GiaBan"));
        tk.setTrangThai(rs.getString("TrangThai"));
        tk.setDiemNoiBat(rs.getString("DiemNoiBat"));
        tk.setLuotXem(rs.getObject("LuotXem", Integer.class));

        Timestamp ts = rs.getTimestamp("ThoiGianDang");
        if (ts != null) {
            tk.setThoiGianDang(ts.toLocalDateTime());
        }

        tk.setDuongDanAnh(rs.getString("DuongDanAnh"));
        return tk;
    }

    // Đã fix các lỗi by Hưng:
    // Xóa hàm insertAndReturnId, chỉ dùng insert trả về id đã tạo hoặc null
    // Sửa hết setInt/getInt sang setObject/getObject để xử lý cả null
    
    // Cập nhật 02/11/2025: Thêm thuộc tính DuongDanAnh cho TaiKhoan
}
