package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.HangThanhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HangThanhVienDAO implements ReadOnlyDAO<HangThanhVien, Integer> {

    private HangThanhVien mapResultSetToHangThanhVien(ResultSet rs) throws SQLException {
        HangThanhVien h = new HangThanhVien();
        h.setMaHang(rs.getObject("MaHang", Integer.class));
        h.setTenHang(rs.getString("TenHang"));
        h.setMucChiTieuToiThieu(rs.getBigDecimal("MucChiTieuToiThieu"));
        h.setDuongDanIcon(rs.getString("DuongDanIcon"));
        h.setChietKhau(rs.getBigDecimal("ChietKhau"));
        return h;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    @Override
    public List<HangThanhVien> getAll() throws SQLException {
        List<HangThanhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM HANGTHANHVIEN";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToHangThanhVien(rs));
            }
        }

        return list;
    }

    @Override
    public HangThanhVien getById(Integer id) throws SQLException {
        HangThanhVien result = null;
        String sql = "SELECT * FROM HANGTHANHVIEN WHERE MaHang = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToHangThanhVien(rs);
                }
            }
        }

        return result;
    }

    // Đã chỉnh sửa by Hưng:
    // Xóa các hàm insert, update, delete (vi phạm yêu cầu ReadOnlyDAO và KHÔNG CÓ TRONG BẢN GIAO VIỆC)
    // Xóa hàm getByPrefix (không được yêu cầu)
    // Implement lại theo yêu cầu
    // Sửa hết try-catch thành throws để cho servlet xử lý
    // dùng getObject/setObject thay cho getInt/setInt cũ để xử lý null
}
