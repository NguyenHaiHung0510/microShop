package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microshop.context.DBContext;
import com.microshop.model.GameSteam;
import com.microshop.model.TaiKhoanSteam;

@ExtendWith(MockitoExtension.class)
class GameTaiKhoanSteamDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;

    @InjectMocks
    private GameTaiKhoanSteamDAO gameTaiKhoanSteamDAO;

    private TaiKhoanSteam sampleTaiKhoanSteam;
    private GameSteam sampleGameSteam;
    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        // Cần setup 2 model này để dùng cho việc test hàm map
        sampleTaiKhoanSteam = new TaiKhoanSteam();
        sampleTaiKhoanSteam.setMaTaiKhoanSteam(1);
        sampleTaiKhoanSteam.setTenDangNhapSteam("steam_user");
        sampleTaiKhoanSteam.setTongSoSlot(100);

        sampleGameSteam = new GameSteam();
        sampleGameSteam.setMaGameSteam(1);
        sampleGameSteam.setTenGame("Test Game");
        sampleGameSteam.setGiaBan(new BigDecimal("100000"));
        sampleGameSteam.setThoiGianDang(timeNow);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    // Mock riêng cho hàm mapResultSetToGameSteam
    private void mockFullGameSteamResultSet() throws SQLException {
        when(rs.getObject("MaGameSteam", Integer.class)).thenReturn(sampleGameSteam.getMaGameSteam());
        when(rs.getString("TenGame")).thenReturn(sampleGameSteam.getTenGame());
        when(rs.getBigDecimal("GiaBan")).thenReturn(sampleGameSteam.getGiaBan());
        when(rs.getTimestamp("ThoiGianDang")).thenReturn(Timestamp.valueOf(timeNow));
        when(rs.getBigDecimal("GiaGoc")).thenReturn(null);
        when(rs.getString("MoTaGame")).thenReturn(null);
        when(rs.getObject("LuotXem", Integer.class)).thenReturn(null);
        when(rs.getString("IdVideoTrailer")).thenReturn(null);
        when(rs.getString("DuongDanAnh")).thenReturn(null);
    }

    // Mock riêng cho hàm mapResultSetToTaiKhoanSteam
    private void mockFullTaiKhoanSteamResultSet() throws SQLException {
        when(rs.getObject("MaTaiKhoanSteam", Integer.class)).thenReturn(sampleTaiKhoanSteam.getMaTaiKhoanSteam());
        when(rs.getString("TenDangNhapSteam")).thenReturn(sampleTaiKhoanSteam.getTenDangNhapSteam());
        when(rs.getString("MatKhauSteam")).thenReturn(null);
        when(rs.getObject("TongSoSlot", Integer.class)).thenReturn(sampleTaiKhoanSteam.getTongSoSlot());
        when(rs.getObject("SoSlotDaBan", Integer.class)).thenReturn(null);
    }

    // ============ Test Custom ============
    @Test
    void addGameToTaiKhoan_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = gameTaiKhoanSteamDAO.addGameToTaiKhoan(1, 1);

            assertTrue(success);
            verify(ps).setObject(1, 1); // MaGameSteam
            verify(ps).setObject(2, 1); // MaTaiKhoanSteam
        }
    }

    @Test
    void removeGameFromTaiKhoan_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = gameTaiKhoanSteamDAO.removeGameFromTaiKhoan(1, 1);

            assertTrue(success);
            verify(ps).setObject(1, 1); // MaGameSteam
            verify(ps).setObject(2, 1); // MaTaiKhoanSteam
        }
    }

    @Test
    void getGamesByMaTaiKhoan_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 game
            mockFullGameSteamResultSet(); // Chuẩn bị dữ liệu cho mapResultSetToGameSteam

            // SỬA: Đổi tên hàm
            List<GameSteam> result = gameTaiKhoanSteamDAO.getAllGameByMaTaiKhoan(1);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Test Game", result.get(0).getTenGame());
            verify(ps).setObject(1, 1); // MaTaiKhoanSteam
        }
    }

    @Test
    void getTaiKhoansByMaGame_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 tài khoản
            mockFullTaiKhoanSteamResultSet(); // Chuẩn bị dữ liệu cho mapResultSetToTaiKhoanSteam

            // SỬA: Đổi tên hàm
            List<TaiKhoanSteam> result = gameTaiKhoanSteamDAO.getAllTaiKhoanByMaGameSteam(1);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("steam_user", result.get(0).getTenDangNhapSteam());
            verify(ps).setObject(1, 1); // MaGameSteam
        }
    }
}
