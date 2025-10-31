package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
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
import com.microshop.model.DonHangSlotSteam;

@ExtendWith(MockitoExtension.class)
class DonHangSlotSteamDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    @Mock
    private ResultSet generatedKeysRs;

    @InjectMocks
    private DonHangSlotSteamDAO donHangSlotSteamDAO;

    private DonHangSlotSteam sampleDonHang;
    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        sampleDonHang = new DonHangSlotSteam();
        sampleDonHang.setMaDonHangSlot(1);
        sampleDonHang.setMaNguoiDung(1);
        sampleDonHang.setMaGameSteam(1);
        sampleDonHang.setMaTaiKhoanSteam(1);
        sampleDonHang.setGiaMua(new BigDecimal("50000"));
        sampleDonHang.setTrangThai("CHO_THANH_TOAN");
        sampleDonHang.setThoiGianTao(timeNow);
        sampleDonHang.setThoiGianMua(null);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockDatabaseConnectionWithKeys() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
    }

    private void mockFullDonHangSlotResultSet() throws SQLException {
        when(rs.getObject("MaDonHangSlot", Integer.class)).thenReturn(sampleDonHang.getMaDonHangSlot());
        when(rs.getObject("MaNguoiDung", Integer.class)).thenReturn(sampleDonHang.getMaNguoiDung());
        when(rs.getObject("MaGameSteam", Integer.class)).thenReturn(sampleDonHang.getMaGameSteam());
        when(rs.getObject("MaTaiKhoanSteam", Integer.class)).thenReturn(sampleDonHang.getMaTaiKhoanSteam());
        when(rs.getBigDecimal("GiaMua")).thenReturn(sampleDonHang.getGiaMua());
        when(rs.getString("TrangThai")).thenReturn(sampleDonHang.getTrangThai());
        when(rs.getTimestamp("ThoiGianTao")).thenReturn(Timestamp.valueOf(timeNow));
        when(rs.getTimestamp("ThoiGianMua")).thenReturn(null);
    }

    // ============ Test CRUD ============
    @Test
    void getById_ReturnsDonHang_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullDonHangSlotResultSet();

            DonHangSlotSteam result = donHangSlotSteamDAO.getById(1);

            assertNotNull(result);
            assertEquals(1, result.getMaDonHangSlot());
            assertEquals("CHO_THANH_TOAN", result.getTrangThai());
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

            DonHangSlotSteam result = donHangSlotSteamDAO.getById(99);

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
            mockFullDonHangSlotResultSet();

            List<DonHangSlotSteam> result = donHangSlotSteamDAO.getAll();

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

            Integer newId = donHangSlotSteamDAO.insert(sampleDonHang);

            assertNotNull(newId);
            assertEquals(99, newId);
            verify(ps).setObject(1, 1); // MaNguoiDung
            verify(ps).setObject(2, 1); // MaGameSteam
            verify(ps).setBigDecimal(4, new BigDecimal("50000")); // GiaMua
        }
    }

    @Test
    void update_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = donHangSlotSteamDAO.update(sampleDonHang);

            assertTrue(success);
            verify(ps).setObject(1, 1); // MaNguoiDung
            verify(ps).setObject(8, 1); // MaDonHangSlot (WHERE)
            verify(ps).setTimestamp(5, null); // ThoiGianMua
        }
    }

    @Test
    void delete_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = donHangSlotSteamDAO.delete(1);

            assertTrue(success);
            verify(ps).setObject(1, 1);
        }
    }

    // ============ Test Custom ============
    @Test
    void getByMaNguoiDung_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);
            mockFullDonHangSlotResultSet();

            List<DonHangSlotSteam> result = donHangSlotSteamDAO.getByMaNguoiDung(1);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getByMaTaiKhoanSteam_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);
            mockFullDonHangSlotResultSet();

            List<DonHangSlotSteam> result = donHangSlotSteamDAO.getByMaTaiKhoanSteam(1);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void updateTrangThai_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            LocalDateTime thoiGianMuaMoi = LocalDateTime.now();
            boolean success = donHangSlotSteamDAO.updateTrangThai(1, "DA_HOAN_THANH", thoiGianMuaMoi);

            assertTrue(success);
            verify(ps).setString(1, "DA_HOAN_THANH");
            verify(ps).setTimestamp(2, Timestamp.valueOf(thoiGianMuaMoi));
            verify(ps).setObject(3, 1);
        }
    }

    @Test
    void getByTrangThai_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);
            mockFullDonHangSlotResultSet();

            List<DonHangSlotSteam> result = donHangSlotSteamDAO.getByTrangThai("CHO_THANH_TOAN");

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(ps).setString(1, "CHO_THANH_TOAN");
        }
    }

    @Test
    void getByMaGameSteam_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);
            mockFullDonHangSlotResultSet();

            List<DonHangSlotSteam> result = donHangSlotSteamDAO.getByMaGameSteam(1);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(ps).setObject(1, 1);
        }
    }
}
