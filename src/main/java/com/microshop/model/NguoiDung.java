package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NguoiDung {

    // Đã sửa hết tên thuộc tính thành dạng camelCase - Hưng
    // Sửa kiểu dữ liệu maHangThanhVien -> Integer
    private Integer maNguoiDung;         // PK
    private String tenDangNhap;          // VARCHAR
    private String matKhau;              // VARCHAR
    private String email;                // VARCHAR
    private String soDienThoai;          // VARCHAR
    private String vaiTro;               // VARCHAR
    private BigDecimal tongTienDaChi;    // DECIMAL 
    private Integer maHangThanhVien;     // FK
    private LocalDateTime thoiGianTao;   // DATETIME

    // Constructor không tham số
    public NguoiDung() {
    }

    // Constructor đầy đủ tham số
    public NguoiDung(Integer maNguoiDung, String tenDangNhap, String matKhau, String email,
            String soDienThoai, String vaiTro, BigDecimal tongTienDaChi,
            Integer maHangThanhVien, LocalDateTime thoiGianTao) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.vaiTro = vaiTro;
        this.tongTienDaChi = tongTienDaChi;
        this.maHangThanhVien = maHangThanhVien;
        this.thoiGianTao = thoiGianTao;
    }

    // Getter
    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getEmail() {
        return email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public BigDecimal getTongTienDaChi() {
        return tongTienDaChi;
    }

    public Integer getMaHangThanhVien() {
        return maHangThanhVien;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    // Setter
    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public void setTongTienDaChi(BigDecimal tongTienDaChi) {
        this.tongTienDaChi = tongTienDaChi;
    }

    public void setMaHangThanhVien(Integer maHangThanhVien) {
        this.maHangThanhVien = maHangThanhVien;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    // toString 
    @Override
    public String toString() {
        return "NguoiDung{"
                + "maNguoiDung=" + maNguoiDung
                + ", tenDangNhap='" + tenDangNhap + '\''
                + ", matKhau='" + matKhau + '\''
                + ", email='" + email + '\''
                + ", soDienThoai='" + soDienThoai + '\''
                + ", vaiTro='" + vaiTro + '\''
                + ", tongTienDaChi=" + tongTienDaChi
                + ", maHangThanhVien=" + maHangThanhVien
                + ", thoiGianTao=" + thoiGianTao
                + '}';
    }
}
