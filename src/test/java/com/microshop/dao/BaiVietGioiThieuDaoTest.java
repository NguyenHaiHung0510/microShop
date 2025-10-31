package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.microshop.model.BaiVietGioiThieu;

@ExtendWith(MockitoExtension.class)
class BaiVietGioiThieuDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    @Mock
    private ResultSet generatedKeysRs;

    @InjectMocks
    private BaiVietGioiThieuDAO baiVietGioiThieuDAO;

    private BaiVietGioiThieu sampleBaiViet;
    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        sampleBaiViet = new BaiVietGioiThieu();
        sampleBaiViet.setMaBaiViet(1);
        sampleBaiViet.setMaGameSteam(1);
        sampleBaiViet.setTieuDeBaiViet("Bài viết về Game Mới");
        sampleBaiViet.setNoiDung("Nội dung bài viết...");
        sampleBaiViet.setThoiGianTao(timeNow);
        sampleBaiViet.setThoiGianCapNhatCuoi(timeNow);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockDatabaseConnectionWithKeys() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
    }

    private void mockFullBaiVietResultSet() throws SQLException {
        when(rs.getObject("MaBaiViet", Integer.class)).thenReturn(sampleBaiViet.getMaBaiViet());
        when(rs.getObject("MaGameSteam", Integer.class)).thenReturn(sampleBaiViet.getMaGameSteam());
        when(rs.getString("TieuDeBaiViet")).thenReturn(sampleBaiViet.getTieuDeBaiViet());
        when(rs.getString("NoiDung")).thenReturn(sampleBaiViet.getNoiDung());
        when(rs.getTimestamp("ThoiGianTao")).thenReturn(Timestamp.valueOf(timeNow));
        when(rs.getTimestamp("ThoiGianCapNhatCuoi")).thenReturn(Timestamp.valueOf(timeNow));
    }

    // ============ Test CRUD ============
    @Test
    void getById_ReturnsBaiViet_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullBaiVietResultSet();

            BaiVietGioiThieu result = baiVietGioiThieuDAO.getById(1);

            assertNotNull(result);
            assertEquals(1, result.getMaBaiViet());
            assertEquals("Bài viết về Game Mới", result.getTieuDeBaiViet());
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getAll_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);
            mockFullBaiVietResultSet();

            List<BaiVietGioiThieu> result = baiVietGioiThieuDAO.getAll();

            assertNotNull(result);
            assertEquals(1, result.size());
        }
    }

    @Test
    void insert_ReturnsGeneratedId() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnectionWithKeys();
            when(ps.executeUpdate()).thenReturn(1);
            when(ps.getGeneratedKeys()).thenReturn(generatedKeysRs);
            when(generatedKeysRs.next()).thenReturn(true);
            when(generatedKeysRs.getInt(1)).thenReturn(99);

            Integer newId = baiVietGioiThieuDAO.insert(sampleBaiViet);

            assertNotNull(newId);
            assertEquals(99, newId);
            verify(ps).setObject(1, 1); // MaGameSteam
            verify(ps).setString(2, "Bài viết về Game Mới");
        }
    }

    @Test
    void update_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = baiVietGioiThieuDAO.update(sampleBaiViet);

            assertTrue(success);
            verify(ps).setObject(1, 1); // MaGameSteam
            verify(ps).setString(2, "Bài viết về Game Mới");
            verify(ps).setObject(4, 1); // MaBaiViet (WHERE)
        }
    }

    @Test
    void delete_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = baiVietGioiThieuDAO.delete(1);

            assertTrue(success);
            verify(ps).setObject(1, 1);
        }
    }

    // ============ Test Custom ============
    @Test
    void getByMaGameSteam_ReturnsBaiViet_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullBaiVietResultSet();

            BaiVietGioiThieu result = baiVietGioiThieuDAO.getByMaGameSteam(1);

            assertNotNull(result);
            assertEquals(1, result.getMaBaiViet());
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void deleteByMaGameSteam_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = baiVietGioiThieuDAO.deleteByMaGameSteam(1);

            assertTrue(success);
            verify(ps).setObject(1, 1);
        }
    }
}
