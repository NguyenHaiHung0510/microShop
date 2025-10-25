package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DonHang {

    private Integer maDonHang;
    private Integer maNguoiDung;
    private Integer maTaiKhoan;
    private BigDecimal giaMua;
    private LocalDateTime ngayMua;
    private String trangThai;
    private LocalDateTime thoiGianTao;

    public DonHang() {
    }

    public DonHang(Integer maDonHang, Integer maNguoiDung, Integer maTaiKhoan,
                   BigDecimal giaMua, LocalDateTime ngayMua,
                   String trangThai, LocalDateTime thoiGianTao) {
        this.maDonHang = maDonHang;
        this.maNguoiDung = maNguoiDung;
        this.maTaiKhoan = maTaiKhoan;
        this.giaMua = giaMua;
        this.ngayMua = ngayMua;
        this.trangThai = trangThai;
        this.thoiGianTao = thoiGianTao;
    }

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

    public LocalDateTime getNgayMua() {
        return ngayMua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

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

    public void setNgayMua(LocalDateTime ngayMua) {
        this.ngayMua = ngayMua;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "maDonHang=" + maDonHang +
                ", maNguoiDung=" + maNguoiDung +
                ", maTaiKhoan=" + maTaiKhoan +
                ", giaMua=" + giaMua +
                ", ngayMua=" + ngayMua +
                ", trangThai='" + trangThai + '\'' +
                ", thoiGianTao=" + thoiGianTao +
                '}';
    }
}
