package com.microshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.microshop.context.DBContext;
import com.microshop.model.NguoiDung;

public class NguoiDungDAO implements CrudDAO<NguoiDung, Integer> {

    private NguoiDung mapResultSetToNguoiDung(ResultSet rs) throws SQLException {
        NguoiDung nd = new NguoiDung();
        nd.setMaNguoiDung(rs.getObject("MaNguoiDung", Integer.class)); 
        nd.setTenDangNhap(rs.getString("TenDangNhap"));
        nd.setMatKhau(rs.getString("MatKhau"));
        nd.setEmail(rs.getString("Email"));
        nd.setSoDienThoai(rs.getString("SoDienThoai"));
        nd.setVaiTro(rs.getString("VaiTro"));
        nd.setTongTienDaChi(rs.getBigDecimal("TongTienDaChi"));
        nd.setMaHangThanhVien(rs.getObject("MaHangThanhVien", Integer.class)); 
        Timestamp ts = rs.getTimestamp("ThoiGianTao");
        nd.setThoiGianTao(ts != null ? ts.toLocalDateTime() : null);
        return nd;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    @Override
    public List<NguoiDung> getAll() throws SQLException { 
        List<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToNguoiDung(rs));
            }
        }
        
        return list;
    }

    @Override
    public NguoiDung getById(Integer id) throws SQLException { 
        NguoiDung result = null;
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToNguoiDung(rs);
                }
            }
        }
        
        return result;
    }

    @Override
    public Integer insert(NguoiDung entity) throws SQLException { 
        String sql = """
            INSERT INTO NguoiDung 
            (TenDangNhap, MatKhau, Email, SoDienThoai, VaiTro, TongTienDaChi, MaHangThanhVien, ThoiGianTao)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getTenDangNhap());
            ps.setString(2, entity.getMatKhau());
            // Xử lý email rỗng hoặc null (trường hợp nhiều tài khoản không đặt email)
            if (entity.getEmail() == null || entity.getEmail().trim().isEmpty()) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, entity.getEmail().trim());
            }
            ps.setString(4, entity.getSoDienThoai());
            ps.setString(5, entity.getVaiTro());
            ps.setBigDecimal(6, entity.getTongTienDaChi());
            ps.setObject(7, entity.getMaHangThanhVien()); 
            ps.setTimestamp(8, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : null);

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
    public boolean update(NguoiDung entity) throws SQLException { 
        String sql = """
            UPDATE NguoiDung 
            SET TenDangNhap=?, MatKhau=?, Email=?, SoDienThoai=?, VaiTro=?, 
                TongTienDaChi=?, MaHangThanhVien=?, ThoiGianTao=?
            WHERE MaNguoiDung=?
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenDangNhap());
            ps.setString(2, entity.getMatKhau());
            // Xử lý email và số điện thoại rỗng hoặc null (trường hợp nhiều tài khoản không đặt email hoặc số điện thoại)
            if (entity.getEmail() == null || entity.getEmail().trim().isEmpty()) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, entity.getEmail().trim());
            }
            if (entity.getSoDienThoai() == null || entity.getSoDienThoai().trim().isEmpty()) {
                ps.setNull(4, Types.VARCHAR);
            } else {
                ps.setString(4, entity.getSoDienThoai().trim());
            }
            ps.setString(5, entity.getVaiTro());
            ps.setBigDecimal(6, entity.getTongTienDaChi());
            ps.setObject(7, entity.getMaHangThanhVien()); 
            ps.setTimestamp(8, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : null);
            ps.setObject(9, entity.getMaNguoiDung()); 

            return ps.executeUpdate() > 0;
        }
       
    }

    @Override
    public boolean delete(Integer id) throws SQLException { 
        String sql = "DELETE FROM NguoiDung WHERE MaNguoiDung=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id); 
            return ps.executeUpdate() > 0;
        }
    }

    public NguoiDung getByTenDangNhap(String tenDangNhap) throws SQLException {
        NguoiDung result = null;
        String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToNguoiDung(rs);
                }
            }
        }
        return result;
    }

    public NguoiDung getByEmail(String email) throws SQLException {
        NguoiDung result = null;
        if (email == null || email.trim().isEmpty()) return null; //Bỏ qua email rỗng
        String sql = "SELECT * FROM NguoiDung WHERE Email = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToNguoiDung(rs);
                }
            }
        }
        return result;
    }
    
    public NguoiDung getBySoDienThoai(String soDienThoai) throws SQLException {
        NguoiDung result = null;
        if (soDienThoai == null || soDienThoai.trim().isEmpty()) return null; // Bỏ qua số rỗng
        String sql = "SELECT * FROM NguoiDung WHERE SoDienThoai = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soDienThoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToNguoiDung(rs);
                }
            }
        }
        return result;
    }
}

    // Đã chỉnh sửa by Hưng:
    // Xóa hàm getByPrefix (không được yêu cầu)
    // Implement lại theo yêu cầu
    // Sửa hết try-catch thành throws để cho servlet xử lý
    // dùng getObject/setObject thay cho getInt/setInt cũ để xử lý null
    // Thêm 2 hàm được yêu cầu trong bản giao việc
    // Xóa hàm getByPrefix ( not now )
