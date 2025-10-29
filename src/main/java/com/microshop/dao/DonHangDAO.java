package com.microshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.microshop.context.DBContext;
import com.microshop.model.DonHang;

public class DonHangDAO implements CrudDAO<DonHang, Integer> {

    // --- Map ResultSet → DonHang ---
    private DonHang mapResultSetToDonHang(ResultSet rs) throws SQLException {
        DonHang dh = new DonHang();
        dh.setMaDonHang(rs.getInt("MaDonHang"));
        dh.setMaNguoiDung(rs.getInt("MaNguoiDung"));
        dh.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
        dh.setGiaMua(rs.getBigDecimal("GiaMua"));

        Timestamp thoiGianMua = rs.getTimestamp("NgayMua"); // Nếu trong DB là ThoiGianMua thì đổi tên
        if (thoiGianMua != null) {
            dh.setThoiGianMua(thoiGianMua.toLocalDateTime());
        }

        dh.setTrangThai(rs.getString("TrangThai"));

        Timestamp thoiGianTao = rs.getTimestamp("ThoiGianTao");
        if (thoiGianTao != null) {
            dh.setThoiGianTao(thoiGianTao.toLocalDateTime());
        }

        return dh;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    // --- GET ALL ---
    @Override
    public List<DonHang> getAll() {
        List<DonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDonHang(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi lấy tất cả đơn hàng: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // --- GET BY ID ---
    @Override
    public DonHang getById(Integer id) {
        DonHang result = null;
        String sql = "SELECT * FROM DONHANG WHERE MaDonHang = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToDonHang(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi lấy đơn hàng ID: " + id);
            e.printStackTrace();
        }
        return result;
    }

    // --- INSERT ---
    @Override
    public Integer insert(DonHang entity) {
        String sql = """
            INSERT INTO DONHANG 
            (MaNguoiDung, MaTaiKhoan, GiaMua, NgayMua, TrangThai, ThoiGianTao)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, entity.getMaNguoiDung());
            ps.setInt(2, entity.getMaTaiKhoan());
            ps.setBigDecimal(3, entity.getGiaMua());
            ps.setTimestamp(4, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);
            ps.setString(5, entity.getTrangThai());
            ps.setTimestamp(6, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : Timestamp.valueOf(LocalDateTime.now()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return null;

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi thêm đơn hàng: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // --- UPDATE ---
    @Override
    public boolean update(DonHang entity) {
        String sql = """
            UPDATE DONHANG 
            SET MaNguoiDung=?, MaTaiKhoan=?, GiaMua=?, NgayMua=?, TrangThai=?, ThoiGianTao=? 
            WHERE MaDonHang=?
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entity.getMaNguoiDung());
            ps.setInt(2, entity.getMaTaiKhoan());
            ps.setBigDecimal(3, entity.getGiaMua());
            ps.setTimestamp(4, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);
            ps.setString(5, entity.getTrangThai());
            ps.setTimestamp(6, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(7, entity.getMaDonHang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi cập nhật đơn hàng ID: " + entity.getMaDonHang());
            e.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM DONHANG WHERE MaDonHang=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi xóa đơn hàng ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    // ==================== CUSTOM METHODS ====================
    public List<DonHang> getByMaNguoiDung(Integer maNguoiDung) throws SQLException {
        String sql = "SELECT * FROM DonHang WHERE MaNguoiDung = ?";
        List<DonHang> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maNguoiDung);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToDonHang(rs));
            }
        }
        return list;
    }

    public DonHang getByMaTaiKhoan(Integer maTaiKhoan) throws SQLException {
        String sql = "SELECT * FROM DonHang WHERE MaTaiKhoan = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapResultSetToDonHang(rs);
            }
        }
        return null;
    }

    public boolean updateTrangThai(Integer maDonHang, String trangThaiMoi, LocalDateTime thoiGianMua) throws SQLException {
        String sql = "UPDATE DonHang SET TrangThai = ?, ThoiGianMua = ? WHERE MaDonHang = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThaiMoi);
            ps.setTimestamp(2, Timestamp.valueOf(thoiGianMua));
            ps.setInt(3, maDonHang);

            return ps.executeUpdate() > 0;
        }
    }
}
