package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoan {

    private Integer maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    private BigDecimal giaBan;
    private String trangThai;
    private Integer maNguoiBan;
    private LocalDateTime thoiGianDang;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer maTaiKhoan, String tenDangNhap, String matKhau, BigDecimal giaBan,
                    String trangThai, Integer maNguoiBan, LocalDateTime thoiGianDang) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.maNguoiBan = maNguoiBan;
        this.thoiGianDang = thoiGianDang;
    }

    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Integer getMaNguoiBan() {
        return maNguoiBan;
    }

    public LocalDateTime getThoiGianDang() {
        return thoiGianDang;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setMaNguoiBan(Integer maNguoiBan) {
        this.maNguoiBan = maNguoiBan;
    }

    public void setThoiGianDang(LocalDateTime thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }

    @Override
    public String toString() {
        return "TaiKhoan{"
                + "maTaiKhoan=" + maTaiKhoan
                + ", tenDangNhap='" + tenDangNhap + '\''
                + ", matKhau='" + matKhau + '\''
                + ", giaBan=" + giaBan
                + ", trangThai='" + trangThai + '\''
                + ", maNguoiBan=" + maNguoiBan
                + ", thoiGianDang=" + thoiGianDang
                + '}';
    }
}
