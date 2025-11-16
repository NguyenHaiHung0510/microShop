package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoanSteam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanSteamDAO implements CrudDAO<TaiKhoanSteam, Integer> {

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    private TaiKhoanSteam mapResultSetToTaiKhoanSteam(ResultSet rs) throws SQLException {
        TaiKhoanSteam tk = new TaiKhoanSteam();
        tk.setMaTaiKhoanSteam(rs.getObject("MaTaiKhoanSteam", Integer.class));
        tk.setTenDangNhapSteam(rs.getString("TenDangNhapSteam"));
        tk.setMatKhauSteam(rs.getString("MatKhauSteam"));
        tk.setTongSoSlot(rs.getObject("TongSoSlot", Integer.class));
        tk.setSoSlotDaBan(rs.getObject("SoSlotDaBan", Integer.class));
        return tk;
    }

    @Override
    public List<TaiKhoanSteam> getAll() throws SQLException {
        List<TaiKhoanSteam> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN_STEAM";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToTaiKhoanSteam(rs));
            }
        }
        return list;
    }

    @Override
    public TaiKhoanSteam getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN_STEAM WHERE MaTaiKhoanSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTaiKhoanSteam(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer insert(TaiKhoanSteam entity) throws SQLException {
        String sql = """
            INSERT INTO TAIKHOAN_STEAM 
            (TenDangNhapSteam, MatKhauSteam, TongSoSlot, SoSlotDaBan)
            VALUES (?, ?, ?, ?)
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getTenDangNhapSteam());
            ps.setString(2, entity.getMatKhauSteam());
            ps.setObject(3, entity.getTongSoSlot());
            ps.setObject(4, entity.getSoSlotDaBan() != null ? entity.getSoSlotDaBan() : 0);
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
    public boolean update(TaiKhoanSteam entity) throws SQLException {
        String sql = """
            UPDATE TAIKHOAN_STEAM
            SET TenDangNhapSteam = ?, MatKhauSteam = ?, TongSoSlot = ?, SoSlotDaBan = ?
            WHERE MaTaiKhoanSteam = ?
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getTenDangNhapSteam());
            ps.setString(2, entity.getMatKhauSteam());
            ps.setObject(3, entity.getTongSoSlot());
            ps.setObject(4, entity.getSoSlotDaBan());
            ps.setObject(5, entity.getMaTaiKhoanSteam());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM TAIKHOAN_STEAM WHERE MaTaiKhoanSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * SỬA LỖI (BUG 1): Nâng cấp hàm để chống Race Condition. Chỉ tăng/giảm nếu
     * hợp lệ và trả về boolean.
     */
    public boolean updateSoSlotDaBan(Integer maTaiKhoanSteam, int soLuongThayDoi) throws SQLException {
        String sql;

        if (soLuongThayDoi > 0) {
            // Logic khi THÊM slot (mua hàng): Chỉ update NẾU SoSlotDaBan < TongSoSlot
            sql = "UPDATE TAIKHOAN_STEAM SET SoSlotDaBan = SoSlotDaBan + ? WHERE MaTaiKhoanSteam = ? AND SoSlotDaBan < TongSoSlot";
        } else {
            // Logic khi TRỪ slot (hủy đơn): Chỉ update NẾU SoSlotDaBan > 0
            sql = "UPDATE TAIKHOAN_STEAM SET SoSlotDaBan = SoSlotDaBan + ? WHERE MaTaiKhoanSteam = ? AND SoSlotDaBan > 0";
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongThayDoi);
            ps.setObject(2, maTaiKhoanSteam);
            return ps.executeUpdate() > 0; // Trả về true nếu CÓ dòng bị ảnh hưởng
        }
    }

    // =========================================================================
    // HÀM MỚI (SỬA BUG 1)
    // =========================================================================
    /**
     * HÀM MỚI: Overload hàm updateSoSlotDaBan để dùng trong Transaction. Hàm
     * này nhận một Connection có sẵn.
     */
    public boolean updateSoSlotDaBan(Connection conn, Integer maTaiKhoanSteam, int soLuongThayDoi) throws SQLException {
        String sql;

        if (soLuongThayDoi > 0) {
            // Logic khi THÊM slot (mua hàng): Chỉ update NẾU SoSlotDaBan < TongSoSlot
            sql = "UPDATE TAIKHOAN_STEAM SET SoSlotDaBan = SoSlotDaBan + ? WHERE MaTaiKhoanSteam = ? AND SoSlotDaBan < TongSoSlot";
        } else {
            // Logic khi TRỪ slot (hủy đơn): Chỉ update NẾU SoSlotDaBan > 0
            sql = "UPDATE TAIKHOAN_STEAM SET SoSlotDaBan = SoSlotDaBan + ? WHERE MaTaiKhoanSteam = ? AND SoSlotDaBan > 0";
        }

        // KHÔNG dùng try-with-resources cho 'conn'
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongThayDoi);
            ps.setObject(2, maTaiKhoanSteam);
            return ps.executeUpdate() > 0; // Trả về true nếu CÓ dòng bị ảnh hưởng
        }
    }

    public TaiKhoanSteam getByTenDangNhap(String tenDangNhap) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN_STEAM WHERE TenDangNhapSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTaiKhoanSteam(rs);
                }
            }
        }
        return null;
    }
}
