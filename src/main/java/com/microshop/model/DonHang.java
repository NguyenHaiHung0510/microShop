package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DonHang {

    private Integer maDonHang;
    private Integer maNguoiDung;
    private Integer maTaiKhoan;
    private BigDecimal giaMua;
    private LocalDateTime thoiGianMua;
    private String trangThai;
    private LocalDateTime thoiGianTao;

    public DonHang() {
    }

    public DonHang(Integer maDonHang, Integer maNguoiDung, Integer maTaiKhoan,
            BigDecimal giaMua, LocalDateTime thoiGianMua,
            String trangThai, LocalDateTime thoiGianTao) {
        this.maDonHang = maDonHang;
        this.maNguoiDung = maNguoiDung;
        this.maTaiKhoan = maTaiKhoan;
        this.giaMua = giaMua;
        this.thoiGianMua = thoiGianMua;
        this.trangThai = trangThai;
        this.thoiGianTao = thoiGianTao;
    }

    // Getters
    public Integer getMaDonHang() {
        return maDonHang;
    }

    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }

    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public BigDecimal getGiaMua() {
        return giaMua;
    }

    public LocalDateTime getThoiGianMua() {
        return thoiGianMua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    // Setters
    public void setMaDonHang(Integer maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public void setGiaMua(BigDecimal giaMua) {
        this.giaMua = giaMua;
    }

    public void setThoiGianMua(LocalDateTime thoiGianMua) {
        this.thoiGianMua = thoiGianMua;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String toString() {
        return "DonHang{"
                + "maDonHang=" + maDonHang
                + ", maNguoiDung=" + maNguoiDung
                + ", maTaiKhoan=" + maTaiKhoan
                + ", giaMua=" + (giaMua != null ? giaMua.toPlainString() : "null")
                + ", thoiGianMua=" + (thoiGianMua != null ? thoiGianMua.format(FORMATTER) : "null")
                + ", trangThai='" + trangThai + '\''
                + ", thoiGianTao=" + (thoiGianTao != null ? thoiGianTao.format(FORMATTER) : "null")
                + '}';
    }
}
