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
import com.microshop.model.GameSteam;

public class GameSteamDAO implements CrudDAO<GameSteam, Integer> {

    private GameSteam mapResultSetToGameSteam(ResultSet rs) throws SQLException {
        GameSteam g = new GameSteam();
        g.setMaGameSteam(rs.getObject("MaGameSteam", Integer.class));
        g.setTenGame(rs.getString("TenGame"));
        g.setMoTaGame(rs.getString("MoTaGame"));
        g.setGiaGoc(rs.getBigDecimal("GiaGoc"));
        g.setGiaBan(rs.getBigDecimal("GiaBan"));
        g.setLuotXem(rs.getObject("LuotXem", Integer.class));

        Timestamp tg = rs.getTimestamp("ThoiGianDang");
        if (tg != null) {
            g.setThoiGianDang(tg.toLocalDateTime());
        }

        g.setIdVideoTrailer(rs.getString("IdVideoTrailer"));
        g.setDuongDanAnh(rs.getString("DuongDanAnh"));
        return g;
    }

    private GameSteam mapFast(ResultSet rs) throws SQLException {
        GameSteam g = new GameSteam();
        g.setMaGameSteam(rs.getObject("MaGameSteam", Integer.class));
        g.setTenGame(rs.getString("TenGame"));
        g.setGiaGoc(rs.getBigDecimal("GiaGoc"));
        g.setGiaBan(rs.getBigDecimal("GiaBan"));
        g.setLuotXem(rs.getObject("LuotXem", Integer.class));

        Timestamp tg = rs.getTimestamp("ThoiGianDang");
        if (tg != null) {
            g.setThoiGianDang(tg.toLocalDateTime());
        }

        g.setIdVideoTrailer(rs.getString("IdVideoTrailer"));
        g.setDuongDanAnh(rs.getString("DuongDanAnh"));
        return g;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    @Override
    public List<GameSteam> getAll() throws SQLException {
        List<GameSteam> list = new ArrayList<>();
        String sql = "SELECT * FROM GAME_STEAM";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToGameSteam(rs));
            }
        }
        return list;
    }

    @Override
    public GameSteam getById(Integer id) throws SQLException {
        GameSteam result = null;
        String sql = "SELECT * FROM GAME_STEAM WHERE MaGameSteam = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToGameSteam(rs);
                }
            }
        }
        return result;
    }

    @Override
    public Integer insert(GameSteam entity) throws SQLException {
        String sql = """
            INSERT INTO GAME_STEAM 
            (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, ThoiGianDang, IdVideoTrailer, DuongDanAnh)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getTenGame());
            ps.setString(2, entity.getMoTaGame());
            ps.setBigDecimal(3, entity.getGiaGoc());
            ps.setBigDecimal(4, entity.getGiaBan());

            ps.setObject(5, entity.getLuotXem());

            if (entity.getThoiGianDang() != null) {
                ps.setTimestamp(6, Timestamp.valueOf(entity.getThoiGianDang()));
            } else {
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            }

            ps.setString(7, entity.getIdVideoTrailer());
            ps.setString(8, entity.getDuongDanAnh());

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
    public boolean update(GameSteam entity) throws SQLException {
        String sql = """
            UPDATE GAME_STEAM
            SET TenGame=?, MoTaGame=?, GiaGoc=?, GiaBan=?, LuotXem=?, 
                ThoiGianDang=?, IdVideoTrailer=?, DuongDanAnh=?
            WHERE MaGameSteam=?
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenGame());
            ps.setString(2, entity.getMoTaGame());
            ps.setBigDecimal(3, entity.getGiaGoc());
            ps.setBigDecimal(4, entity.getGiaBan());

            ps.setObject(5, entity.getLuotXem());

            ps.setTimestamp(6, entity.getThoiGianDang() != null ? Timestamp.valueOf(entity.getThoiGianDang()) : null);
            ps.setString(7, entity.getIdVideoTrailer());
            ps.setString(8, entity.getDuongDanAnh());

            ps.setObject(9, entity.getMaGameSteam());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM GAME_STEAM WHERE MaGameSteam=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public String getMoTaGame(Integer maGameSteam) throws SQLException {
        String sql = "SELECT MoTaGame FROM GAME_STEAM WHERE MaGameSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, maGameSteam);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("MoTaGame");
                }
            }
        }
        return null;
    }

    public List<GameSteam> fastGetAll() throws SQLException {
        List<GameSteam> list = new ArrayList<>();
        String sql = """
            SELECT MaGameSteam, TenGame, GiaGoc, GiaBan, LuotXem, ThoiGianDang, IdVideoTrailer, DuongDanAnh
            FROM GAME_STEAM
        """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapFast(rs));
            }
        }
        return list;
    }
}
