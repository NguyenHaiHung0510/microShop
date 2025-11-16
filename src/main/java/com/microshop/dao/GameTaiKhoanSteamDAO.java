package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.GameSteam;
import com.microshop.model.TaiKhoanSteam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GameTaiKhoanSteamDAO {

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    public boolean addGameToTaiKhoan(Integer maGameSteam, Integer maTaiKhoanSteam) throws SQLException {
        String sql = "INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maGameSteam);
            ps.setObject(2, maTaiKhoanSteam);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean removeGameFromTaiKhoan(Integer maGameSteam, Integer maTaiKhoanSteam) throws SQLException {
        String sql = "DELETE FROM GAME_TAIKHOAN_STEAM WHERE MaGameSteam = ? AND MaTaiKhoanSteam = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maGameSteam);
            ps.setObject(2, maTaiKhoanSteam);
            return ps.executeUpdate() > 0;
        }
    }

    public List<GameSteam> getAllGameByMaTaiKhoan(Integer maTaiKhoanSteam) throws SQLException {
        List<GameSteam> list = new ArrayList<>();
        String sql = """
            SELECT g.* FROM GAME_STEAM g
            JOIN GAME_TAIKHOAN_STEAM gts ON g.MaGameSteam = gts.MaGameSteam
            WHERE gts.MaTaiKhoanSteam = ?
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maTaiKhoanSteam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToGameSteam(rs));
                }
            }
        }
        return list;
    }

    public List<TaiKhoanSteam> getAllTaiKhoanByMaGameSteam(Integer maGameSteam) throws SQLException {
        List<TaiKhoanSteam> list = new ArrayList<>();
        String sql = """
            SELECT ts.* FROM TAIKHOAN_STEAM ts 
            JOIN GAME_TAIKHOAN_STEAM gts ON ts.MaTaiKhoanSteam = gts.MaTaiKhoanSteam 
            WHERE gts.MaGameSteam = ?
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maGameSteam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanSteam(rs));
                }
            }
        }
        return list;
    }

    /**
     * SỬA LỖI: Cập nhật câu SQL để sắp xếp theo (TongSoSlot - SoSlotDaBan) Đây
     * là logic cân bằng tải.
     */
    public List<TaiKhoanSteam> getAllTaiKhoanByMaGameSteamSorted(Integer maGameSteam) throws SQLException {
        List<TaiKhoanSteam> list = new ArrayList<>();
        String sql = """
            SELECT ts.* FROM TAIKHOAN_STEAM ts 
            JOIN GAME_TAIKHOAN_STEAM gts ON ts.MaTaiKhoanSteam = gts.MaTaiKhoanSteam 
            WHERE gts.MaGameSteam = ? 
            ORDER BY (ts.TongSoSlot - ts.SoSlotDaBan) DESC
            """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, maGameSteam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoanSteam(rs));
                }
            }
        }
        return list;
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
}
