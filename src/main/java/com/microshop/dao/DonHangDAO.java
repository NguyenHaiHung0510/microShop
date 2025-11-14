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
import java.math.BigDecimal;

public class DonHangDAO implements CrudDAO<DonHang, Integer> {

    private DonHang mapResultSetToDonHang(ResultSet rs) throws SQLException {
        DonHang dh = new DonHang();
        dh.setMaDonHang(rs.getObject("MaDonHang", Integer.class));
        dh.setMaNguoiDung(rs.getObject("MaNguoiDung", Integer.class));
        dh.setMaTaiKhoan(rs.getObject("MaTaiKhoan", Integer.class));

        dh.setGiaMua(rs.getBigDecimal("GiaMua"));

        Timestamp thoiGianMua = rs.getTimestamp("ThoiGianMua");
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

    @Override
    public List<DonHang> getAll() throws SQLException {
        List<DonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM DONHANG ORDER BY ThoiGianTao DESC";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDonHang(rs));
            }
        }
        return list;
    }

    @Override
    public DonHang getById(Integer id) throws SQLException {
        DonHang result = null;
        String sql = "SELECT * FROM DONHANG WHERE MaDonHang = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToDonHang(rs);
                }
            }
        }
        return result;
    }

    @Override
    public Integer insert(DonHang entity) throws SQLException {
        String sql = """
            INSERT INTO DONHANG 
            (MaNguoiDung, MaTaiKhoan, GiaMua, ThoiGianMua, TrangThai, ThoiGianTao)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setObject(1, entity.getMaNguoiDung());
            ps.setObject(2, entity.getMaTaiKhoan());
            ps.setBigDecimal(3, entity.getGiaMua());
            ps.setTimestamp(4, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);
            ps.setString(5, entity.getTrangThai());
            ps.setTimestamp(6, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : Timestamp.valueOf(LocalDateTime.now()));

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
    public boolean update(DonHang entity) throws SQLException {
        String sql = """
            UPDATE DONHANG 
            SET MaNguoiDung=?, MaTaiKhoan=?, GiaMua=?, ThoiGianMua=?, TrangThai=?, ThoiGianTao=? 
            WHERE MaDonHang=?
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, entity.getMaNguoiDung());
            ps.setObject(2, entity.getMaTaiKhoan());
            ps.setBigDecimal(3, entity.getGiaMua());
            ps.setTimestamp(4, entity.getThoiGianMua() != null ? Timestamp.valueOf(entity.getThoiGianMua()) : null);
            ps.setString(5, entity.getTrangThai());
            ps.setTimestamp(6, entity.getThoiGianTao() != null ? Timestamp.valueOf(entity.getThoiGianTao()) : Timestamp.valueOf(LocalDateTime.now()));
            ps.setObject(7, entity.getMaDonHang());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM DONHANG WHERE MaDonHang=?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<DonHang> getByMaNguoiDung(Integer maNguoiDung) throws SQLException {
        String sql = "SELECT * FROM DonHang WHERE MaNguoiDung = ?";
        List<DonHang> list = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maNguoiDung);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHang(rs));
                }
            }
        }
        return list;
    }

    public List<DonHang> getByMaNguoiDungDaHoanThanh(Integer maNguoiDung) throws SQLException {
        String sql = "SELECT * FROM DonHang WHERE MaNguoiDung = ? AND TrangThai = 'DA_HOAN_THANH'";
        List<DonHang> list = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maNguoiDung);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHang(rs));
                }
            }
        }
        return list;
    }

    public List<DonHang> getByMaTaiKhoan(Integer maTaiKhoan) throws SQLException {
        List<DonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM DonHang WHERE MaTaiKhoan = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHang(rs));
                }
            }
        }
        return list;
    }

    public DonHang getByMaTaiKhoanChoThanhToan(Integer maTaiKhoan) throws SQLException {
        String sql = "SELECT * FROM DonHang WHERE MaTaiKhoan = ? AND TrangThai = 'CHO_THANH_TOAN'";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDonHang(rs);
                }
            }
        }
        return null;
    }

    public boolean updateGiaMua(Integer maDonHang, BigDecimal giaMoi) throws SQLException {
        String sql = "UPDATE DonHang SET GiaMua = ? WHERE MaDonHang = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, giaMoi);
            ps.setObject(2, maDonHang);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateTrangThai(Integer maDonHang, String trangThaiMoi, LocalDateTime thoiGianMua) throws SQLException {
        String sql = "UPDATE DonHang SET TrangThai = ?, ThoiGianMua = ? WHERE MaDonHang = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThaiMoi);
            ps.setTimestamp(2, Timestamp.valueOf(thoiGianMua));
            ps.setObject(3, maDonHang);

            return ps.executeUpdate() > 0;
        }
    }

    public int getTotalCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM DONHANG";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getCountByTrangThai(String trangThai) throws SQLException {
        String sql = "SELECT COUNT(*) FROM DONHANG WHERE TrangThai = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    // Lấy danh sách các đơn từ OFFSET + 1, lấy tối đa LIMIT đơn, trả về theo thứ tự đơn mới hơn xếp trước
    public List<DonHang> getAllPaginated(int page, int recordsPerPage) throws SQLException {
        List<DonHang> list = new ArrayList<>();
        int offset = (page - 1) * recordsPerPage;
        String sql = "SELECT * FROM DONHANG ORDER BY ThoiGianTao DESC LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recordsPerPage);
            ps.setInt(2, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDonHang(rs));
                }
            }
        }
        return list;
    }
}
