package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NguoiDung {

    private Integer MaNguoiDung;         // PK
    private String TenDangNhap;          // VARCHAR
    private String MatKhau;              // VARCHAR
    private String Email;                 // VARCHAR
    private String SoDienThoai;          // VARCHAR
    private String VaiTro;               // VARCHAR
    private BigDecimal TongTienDaChi;    // DECIMAL
    private String MaHangThanhVien;      // FK
    private LocalDate ThoiGianTao;       // DATETIME

    // Constructor không tham số
    public NguoiDung() {
    }

    // Constructor đầy đủ tham số
    public NguoiDung(Integer MaNguoiDung, String TenDangNhap, String MatKhau, String Email,
                      String SoDienThoai, String VaiTro, BigDecimal TongTienDaChi,
                      String MaHangThanhVien, LocalDate ThoiGianTao) {
        this.MaNguoiDung = MaNguoiDung;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.SoDienThoai = SoDienThoai;
        this.VaiTro = VaiTro;
        this.TongTienDaChi = TongTienDaChi;
        this.MaHangThanhVien = MaHangThanhVien;
        this.ThoiGianTao = ThoiGianTao;
    }

    // Getter
    public Integer getMaNguoiDung() {
        return MaNguoiDung;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public String getEmail() {
        return Email;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public String getVaiTro() {
        return VaiTro;
    }

    public BigDecimal getTongTienDaChi() {
        return TongTienDaChi;
    }

    public String getMaHangThanhVien() {
        return MaHangThanhVien;
    }

    public LocalDate getThoiGianTao() {
        return ThoiGianTao;
    }

    // Setter
    public void setMaNguoiDung(Integer MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public void setVaiTro(String VaiTro) {
        this.VaiTro = VaiTro;
    }

    public void setTongTienDaChi(BigDecimal TongTienDaChi) {
        this.TongTienDaChi = TongTienDaChi;
    }

    public void setMaHangThanhVien(String MaHangThanhVien) {
        this.MaHangThanhVien = MaHangThanhVien;
    }

    public void setThoiGianTao(LocalDate ThoiGianTao) {
        this.ThoiGianTao = ThoiGianTao;
    }

    // toString
    @Override
    public String toString() {
        return "NguoiDung{" +
                "MaNguoiDung=" + MaNguoiDung +
                ", TenDangNhap='" + TenDangNhap + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", Email='" + Email + '\'' +
                ", SoDienThoai='" + SoDienThoai + '\'' +
                ", VaiTro='" + VaiTro + '\'' +
                ", TongTienDaChi=" + TongTienDaChi +
                ", MaHangThanhVien='" + MaHangThanhVien + '\'' +
                ", ThoiGianTao=" + ThoiGianTao +
                '}';
    }
}
