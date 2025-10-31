package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.microshop.model.TaiKhoanSteam;

@ExtendWith(MockitoExtension.class)
class TaiKhoanSteamDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    @Mock
    private ResultSet generatedKeysRs;

    @InjectMocks
    private TaiKhoanSteamDAO taiKhoanSteamDAO;

    private TaiKhoanSteam sampleTaiKhoanSteam;

    @BeforeEach
    void setUp() {
        sampleTaiKhoanSteam = new TaiKhoanSteam();
        sampleTaiKhoanSteam.setMaTaiKhoanSteam(1);
        sampleTaiKhoanSteam.setTenDangNhapSteam("steam_user");
        sampleTaiKhoanSteam.setMatKhauSteam("123456");
        sampleTaiKhoanSteam.setTongSoSlot(100);
        sampleTaiKhoanSteam.setSoSlotDaBan(5);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockDatabaseConnectionWithKeys() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
    }

    private void mockFullTaiKhoanSteamResultSet() throws SQLException {
        when(rs.getObject("MaTaiKhoanSteam", Integer.class)).thenReturn(sampleTaiKhoanSteam.getMaTaiKhoanSteam());
        when(rs.getString("TenDangNhapSteam")).thenReturn(sampleTaiKhoanSteam.getTenDangNhapSteam());
        when(rs.getString("MatKhauSteam")).thenReturn(sampleTaiKhoanSteam.getMatKhauSteam());
        when(rs.getObject("TongSoSlot", Integer.class)).thenReturn(sampleTaiKhoanSteam.getTongSoSlot());
        when(rs.getObject("SoSlotDaBan", Integer.class)).thenReturn(sampleTaiKhoanSteam.getSoSlotDaBan());
    }

    // ============ Test CRUD ============
    @Test
    void getById_ReturnsTaiKhoanSteam_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullTaiKhoanSteamResultSet();

            TaiKhoanSteam result = taiKhoanSteamDAO.getById(1);

            assertNotNull(result);
            assertEquals(1, result.getMaTaiKhoanSteam());
            assertEquals("steam_user", result.getTenDangNhapSteam());
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getById_ReturnsNull_WhenNotFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            TaiKhoanSteam result = taiKhoanSteamDAO.getById(99);

            assertNull(result);
        }
    }

    @Test
    void getAll_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);
            mockFullTaiKhoanSteamResultSet();

            List<TaiKhoanSteam> result = taiKhoanSteamDAO.getAll();

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

            // Test khi insert SoSlotDaBan là null, nó sẽ tự set là 0
            sampleTaiKhoanSteam.setSoSlotDaBan(null);
            Integer newId = taiKhoanSteamDAO.insert(sampleTaiKhoanSteam);

            assertNotNull(newId);
            assertEquals(99, newId);
            verify(ps).setString(1, "steam_user");
            verify(ps).setObject(3, 100); // TongSoSlot
            verify(ps).setObject(4, 0); // SoSlotDaBan (default về 0)
        }
    }

    @Test
    void update_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = taiKhoanSteamDAO.update(sampleTaiKhoanSteam);

            assertTrue(success);
            verify(ps).setString(1, "steam_user");
            verify(ps).setObject(4, 5); // SoSlotDaBan
            verify(ps).setObject(5, 1); // MaTaiKhoanSteam (WHERE)
        }
    }

    @Test
    void delete_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = taiKhoanSteamDAO.delete(1);

            assertTrue(success);
            verify(ps).setObject(1, 1);
        }
    }

    // ============ Test Custom ============
    @Test
    void updateSoSlotDaBan_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            // Test tăng 1 slot
            boolean success = taiKhoanSteamDAO.updateSoSlotDaBan(1, 1);

            assertTrue(success);
            verify(ps).setInt(1, 1); // soLuongThayDoi
            verify(ps).setObject(2, 1); // maTaiKhoanSteam
        }
    }

    @Test
    void getByTenDangNhap_ReturnsTaiKhoanSteam_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullTaiKhoanSteamResultSet();

            TaiKhoanSteam result = taiKhoanSteamDAO.getByTenDangNhap("steam_user");

            assertNotNull(result);
            assertEquals(1, result.getMaTaiKhoanSteam());
            verify(ps).setString(1, "steam_user");
        }
    }

    @Test
    void getByTenDangNhap_ReturnsNull_WhenNotFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            TaiKhoanSteam result = taiKhoanSteamDAO.getByTenDangNhap("not_found");

            assertNull(result);
        }
    }
}
