package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.BaiVietGioiThieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BaiVietGioiThieuDAO implements CrudDAO<BaiVietGioiThieu, Integer> {

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    private BaiVietGioiThieu mapResultSetToBaiViet(ResultSet rs) throws SQLException {
        BaiVietGioiThieu baiViet = new BaiVietGioiThieu();
        baiViet.setMaBaiViet(rs.getObject("MaBaiViet", Integer.class));
        baiViet.setMaGameSteam(rs.getObject("MaGameSteam", Integer.class));
        baiViet.setTieuDeBaiViet(rs.getString("TieuDeBaiViet"));
        baiViet.setNoiDung(rs.getString("NoiDung"));

        Timestamp thoiGianTao = rs.getTimestamp("ThoiGianTao");
        if (thoiGianTao != null) {
            baiViet.setThoiGianTao(thoiGianTao.toLocalDateTime());
        }

        Timestamp thoiGianCapNhat = rs.getTimestamp("ThoiGianCapNhatCuoi");
        if (thoiGianCapNhat != null) {
            baiViet.setThoiGianCapNhatCuoi(thoiGianCapNhat.toLocalDateTime());
        }
        return baiViet;
    }

    @Override
    public List<BaiVietGioiThieu> getAll() throws SQLException {
        List<BaiVietGioiThieu> list = new ArrayList<>();
        String sql = "SELECT * FROM BAIVIET_GIOITHIEU";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToBaiViet(rs));
            }
        }
        return list;
    }

    @Override
    public BaiVietGioiThieu getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM BAIVIET_GIOITHIEU WHERE MaBaiViet = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBaiViet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer insert(BaiVietGioiThieu entity) throws SQLException {
        String sql = """
            INSERT INTO BAIVIET_GIOITHIEU 
            (MaGameSteam, TieuDeBaiViet, NoiDung, ThoiGianTao, ThoiGianCapNhatCuoi)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setObject(1, entity.getMaGameSteam());
            ps.setString(2, entity.getTieuDeBaiViet());
            ps.setString(3, entity.getNoiDung());

            LocalDateTime now = LocalDateTime.now();
            ps.setTimestamp(4, Timestamp.valueOf(entity.getThoiGianTao() != null ? entity.getThoiGianTao() : now));
            ps.setTimestamp(5, Timestamp.valueOf(entity.getThoiGianCapNhatCuoi() != null ? entity.getThoiGianCapNhatCuoi() : now));

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
    public boolean update(BaiVietGioiThieu entity) throws SQLException {
        // ThoiGianCapNhatCuoi tự động cập nhật theo Schema CSDL
        String sql = """
            UPDATE BAIVIET_GIOITHIEU
            SET MaGameSteam = ?, TieuDeBaiViet = ?, NoiDung = ?
            WHERE MaBaiViet = ?
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, entity.getMaGameSteam());
            ps.setString(2, entity.getTieuDeBaiViet());
            ps.setString(3, entity.getNoiDung());
            ps.setObject(4, entity.getMaBaiViet());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM BAIVIET_GIOITHIEU WHERE MaBaiViet = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public BaiVietGioiThieu getByMaGameSteam(Integer maGameSteam) throws SQLException {
        String sql = "SELECT * FROM BAIVIET_GIOITHIEU WHERE MaGameSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maGameSteam);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBaiViet(rs);
                }
            }
        }
        return null;
    }

    public boolean deleteByMaGameSteam(Integer maGameSteam) throws SQLException {
        String sql = "DELETE FROM BAIVIET_GIOITHIEU WHERE MaGameSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maGameSteam);
            return ps.executeUpdate() > 0;
        }
    }
}
