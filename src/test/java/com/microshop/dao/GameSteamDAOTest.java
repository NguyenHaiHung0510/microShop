package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
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

@ExtendWith(MockitoExtension.class)
class GameSteamDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    @Mock
    private ResultSet generatedKeysRs;

    @InjectMocks
    private GameSteamDAO gameSteamDAO;

    private GameSteam sampleGame;

    @BeforeEach
    void setUp() {
        sampleGame = new GameSteam();
        sampleGame.setMaGameSteam(1);
        sampleGame.setTenGame("Test Game");
        sampleGame.setGiaBan(new BigDecimal("250000"));

        // SỬA: Thêm dòng này để tránh NullPointerException
        sampleGame.setLuotXem(0);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockDatabaseConnectionWithKeys() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
    }

    // ============ Test 5 hàm CRUD ============
    @Test
    void getById_ReturnsGameSteam_WhenFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            // SỬA: Mock rs.getObject
            when(rs.getObject("MaGameSteam", Integer.class)).thenReturn(1);
            when(rs.getString("TenGame")).thenReturn("Test Game");
            when(rs.getObject("LuotXem", Integer.class)).thenReturn(0);

            // Act
            GameSteam result = gameSteamDAO.getById(1);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.getMaGameSteam());
            assertEquals("Test Game", result.getTenGame());

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getAll_ReturnsListOfGameSteam() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // 2 bản ghi

            // Act
            List<GameSteam> result = gameSteamDAO.getAll();

            // Assert
            assertNotNull(result);
            assertEquals(2, result.size());
        }
    }

    @Test
    void insert_ReturnsGeneratedId_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnectionWithKeys();

            when(ps.executeUpdate()).thenReturn(1);
            when(ps.getGeneratedKeys()).thenReturn(generatedKeysRs);
            when(generatedKeysRs.next()).thenReturn(true);
            when(generatedKeysRs.getInt(1)).thenReturn(50); // ID được generate

            // Act
            Integer newId = gameSteamDAO.insert(sampleGame);

            // Assert
            assertNotNull(newId);
            assertEquals(50, newId);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(5, sampleGame.getLuotXem());
        }
    }

    @Test
    void update_ReturnsTrue_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeUpdate()).thenReturn(1); // 1 hàng bị ảnh hưởng

            // Act
            boolean success = gameSteamDAO.update(sampleGame);

            // Assert
            assertTrue(success);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(5, sampleGame.getLuotXem());
            verify(ps).setObject(9, sampleGame.getMaGameSteam());
        }
    }

    @Test
    void delete_ReturnsTrue_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeUpdate()).thenReturn(1); // 1 hàng bị ảnh hưởng

            // Act
            boolean success = gameSteamDAO.delete(1);

            // Assert
            assertTrue(success);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, 1);
        }
    }

    // ============ Test các hàm custom ============
    @Test
    void getMoTaGame_ReturnsString() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getString("MoTaGame")).thenReturn("Đây là mô tả game");

            // Act
            String moTa = gameSteamDAO.getMoTaGame(1);

            // Assert
            assertNotNull(moTa);
            assertEquals("Đây là mô tả game", moTa);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getMoTaGame_ReturnsNull_WhenNotFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            // Act
            String moTa = gameSteamDAO.getMoTaGame(1);

            // Assert
            assertNull(moTa);
        }
    }

    @Test
    void fastGetAll_ReturnsList() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // 2 game
            // Mock các trường của mapFast (trừ MoTaGame)
            when(rs.getString("TenGame")).thenReturn("Game 1").thenReturn("Game 2");

            // Act
            List<GameSteam> result = gameSteamDAO.fastGetAll();

            // Assert
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Game 1", result.get(0).getTenGame());
            assertEquals("Game 2", result.get(1).getTenGame());
        }
    }

    @Test
    void fastGetAll_ThrowsSQLException() throws SQLException {
        // (Không cần thay đổi)
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenThrow(new SQLException("Test DB Error"));

            // Act & Assert
            assertThrows(SQLException.class, () -> {
                gameSteamDAO.fastGetAll();
            });
        }
    }
}
