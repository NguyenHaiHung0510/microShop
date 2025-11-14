package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement; // Không cần thiết
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
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanRiot;

@ExtendWith(MockitoExtension.class)
class TaiKhoanRiotDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;

    @Mock
    private TaiKhoanDAO taiKhoanDAO;

    @InjectMocks
    private TaiKhoanRiotDAO taiKhoanRiotDAO;

    private TaiKhoanRiot sampleTKRiot;
    private final LocalDateTime timeNow = LocalDateTime.now();
    private final String sampleAnh = "path/to/riot.jpg";

    @BeforeEach
    void setUp() {
        sampleTKRiot = new TaiKhoanRiot();
        // Thuộc tính cha
        sampleTKRiot.setMaTaiKhoan(1);
        sampleTKRiot.setMaDanhMuc(3); // Danh mục Riot
        sampleTKRiot.setGiaBan(new BigDecimal("300000"));
        sampleTKRiot.setTrangThai("DANG_BAN");
        sampleTKRiot.setThoiGianDang(timeNow);
        sampleTKRiot.setDuongDanAnh(sampleAnh);
        // Thuộc tính con
        sampleTKRiot.setTenDangNhap("tkriot_user");
        sampleTKRiot.setMatKhau("456");
        sampleTKRiot.setCapDoRiot(500);
        sampleTKRiot.setSoTuongLMHT(160);
        sampleTKRiot.setSoTrangPhucLMHT(300);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockFullTKRiotResultSet() throws SQLException {
        // Mock phần cha (TAIKHOAN)
        when(rs.getObject("MaTaiKhoan", Integer.class)).thenReturn(sampleTKRiot.getMaTaiKhoan());
        when(rs.getObject("MaDanhMuc", Integer.class)).thenReturn(sampleTKRiot.getMaDanhMuc());
        when(rs.getBigDecimal("GiaBan")).thenReturn(sampleTKRiot.getGiaBan());
        when(rs.getString("TrangThai")).thenReturn(sampleTKRiot.getTrangThai());
        when(rs.getTimestamp("ThoiGianDang")).thenReturn(Timestamp.valueOf(timeNow));
        when(rs.getBigDecimal("GiaGoc")).thenReturn(sampleTKRiot.getGiaGoc());
        when(rs.getString("DiemNoiBat")).thenReturn(sampleTKRiot.getDiemNoiBat());
        when(rs.getObject("LuotXem", Integer.class)).thenReturn(sampleTKRiot.getLuotXem());
        when(rs.getString("DuongDanAnh")).thenReturn(sampleTKRiot.getDuongDanAnh());

        // Mock phần con (TAIKHOAN_RIOT)
        when(rs.getString("TenDangNhap")).thenReturn(sampleTKRiot.getTenDangNhap());
        when(rs.getString("MatKhau")).thenReturn(sampleTKRiot.getMatKhau());
        when(rs.getObject("CapDoRiot", Integer.class)).thenReturn(sampleTKRiot.getCapDoRiot());
        when(rs.getObject("SoTuongLMHT", Integer.class)).thenReturn(sampleTKRiot.getSoTuongLMHT());
        when(rs.getObject("SoTrangPhucLMHT", Integer.class)).thenReturn(sampleTKRiot.getSoTrangPhucLMHT());
        // Mock các trường còn lại là null
        when(rs.getObject("SoDaSacLMHT", Integer.class)).thenReturn(null);
        when(rs.getObject("SoBieuCamLMHT", Integer.class)).thenReturn(null);
        when(rs.getObject("SoBieuTuongLMHT", Integer.class)).thenReturn(null);
        when(rs.getString("HangRankLMHT")).thenReturn(null);
        when(rs.getString("KhungRankLMHT")).thenReturn(null);
        when(rs.getObject("SoThuCungTFT", Integer.class)).thenReturn(null);
        when(rs.getObject("SoSanDauTFT", Integer.class)).thenReturn(null);
        when(rs.getObject("SoChuongLucTFT", Integer.class)).thenReturn(null);
    }

    // ============ Test CRUD ============
    @Test
    void getById_ReturnsTKRiot_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullTKRiotResultSet();

            TaiKhoanRiot result = taiKhoanRiotDAO.getById(1);

            assertNotNull(result);
            assertEquals(1, result.getMaTaiKhoan());
            assertEquals(500, result.getCapDoRiot());
            assertEquals(160, result.getSoTuongLMHT());
            assertEquals(sampleAnh, result.getDuongDanAnh());
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getAll_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi
            mockFullTKRiotResultSet();

            List<TaiKhoanRiot> result = taiKhoanRiotDAO.getAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(500, result.get(0).getCapDoRiot());
            assertEquals(sampleAnh, result.get(0).getDuongDanAnh());
        }
    }

    @Test
    void insert_ReturnsGeneratedId() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {

            // --- SỬA LỖI TEST ---
            when(taiKhoanDAO.insert(any(TaiKhoan.class), any(Connection.class))).thenReturn(99);

            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            when(connection.prepareStatement(anyString())).thenReturn(ps); // Mock cho DAO con
            when(ps.executeUpdate()).thenReturn(1);

            Integer newId = taiKhoanRiotDAO.insert(sampleTKRiot);

            assertNotNull(newId);
            assertEquals(99, newId);
            verify(taiKhoanDAO).insert(any(TaiKhoan.class), any(Connection.class));
            verify(ps).setObject(1, 99); // MaTaiKhoan
            verify(ps).setObject(4, 500); // CapDoRiot
            verify(ps).setObject(5, 160); // SoTuongLMHT
        }
    }

    @Test
    void update_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {

            // --- SỬA LỖI TEST ---
            when(taiKhoanDAO.update(any(TaiKhoan.class), any(Connection.class))).thenReturn(true);

            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = taiKhoanRiotDAO.update(sampleTKRiot);

            assertTrue(success);
            verify(taiKhoanDAO).update(any(TaiKhoan.class), any(Connection.class));
            verify(ps).setObject(14, 1); // MaTaiKhoan (WHERE)
            verify(ps).setObject(3, 500); // CapDoRiot
        }
    }

    @Test
    void delete_ReturnsTrue() throws SQLException {
        when(taiKhoanDAO.delete(1)).thenReturn(true);
        boolean success = taiKhoanRiotDAO.delete(1);
        assertTrue(success);
        verify(taiKhoanDAO, times(1)).delete(1);
    }

    // ============ Test Custom ============
    @Test
    void getByTrangThai_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi
            mockFullTKRiotResultSet();

            List<TaiKhoanRiot> result = taiKhoanRiotDAO.getByTrangThai("DANG_BAN");

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(sampleAnh, result.get(0).getDuongDanAnh());
            verify(ps).setString(1, "DANG_BAN");
        }
    }

    @Test
    void updateTrangThai_CallsParentDAO() throws SQLException {
        taiKhoanRiotDAO.updateTrangThai(1, "DA_BAN");
        verify(taiKhoanDAO, times(1)).updateTrangThai(1, "DA_BAN");
    }
}
