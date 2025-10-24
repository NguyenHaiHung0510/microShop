package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DonHangSlotSteam {

    private Integer maDonHangSlot; 
    private Integer maNguoiDung; 
    private Integer maGameSteam;
    private Integer maTaiKhoanSteam;
    private BigDecimal giaMua;
    private LocalDateTime thoiGianMua;
    private String trangThai;
    private LocalDateTime thoiGianTao;

    public DonHangSlotSteam() {
    }

    public DonHangSlotSteam(Integer maDonHangSlot, Integer maNguoiDung, Integer maGameSteam,
            Integer maTaiKhoanSteam, BigDecimal giaMua, LocalDateTime thoiGianMua,
            String trangThai, LocalDateTime thoiGianTao) {
        this.maDonHangSlot = maDonHangSlot;
        this.maNguoiDung = maNguoiDung;
        this.maGameSteam = maGameSteam;
        this.maTaiKhoanSteam = maTaiKhoanSteam;
        this.giaMua = giaMua;
        this.thoiGianMua = thoiGianMua;
        this.trangThai = trangThai;
        this.thoiGianTao = thoiGianTao;
    }

    public Integer getMaDonHangSlot() {
        return maDonHangSlot;
    }

    public void setMaDonHangSlot(Integer maDonHangSlot) {
        this.maDonHangSlot = maDonHangSlot;
    }

    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public Integer getMaGameSteam() {
        return maGameSteam;
    }

    public void setMaGameSteam(Integer maGameSteam) {
        this.maGameSteam = maGameSteam;
    }

    public Integer getMaTaiKhoanSteam() {
        return maTaiKhoanSteam;
    }

    public void setMaTaiKhoanSteam(Integer maTaiKhoanSteam) {
        this.maTaiKhoanSteam = maTaiKhoanSteam;
    }

    public BigDecimal getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(BigDecimal giaMua) {
        this.giaMua = giaMua;
    }

    public LocalDateTime getThoiGianMua() {
        return thoiGianMua;
    }

    public void setThoiGianMua(LocalDateTime thoiGianMua) {
        this.thoiGianMua = thoiGianMua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String toString() {
        return "DonHangSlotSteam{"
                + "maDonHangSlot=" + maDonHangSlot
                + ", maNguoiDung=" + maNguoiDung
                + ", maGameSteam=" + maGameSteam
                + ", maTaiKhoanSteam=" + maTaiKhoanSteam
                + ", giaMua=" + (giaMua != null ? giaMua.toPlainString() : "null")
                + ", thoiGianMua=" + (thoiGianMua != null ? thoiGianMua.format(FORMATTER) : "null")
                + ", trangThai='" + trangThai + '\''
                + ", thoiGianTao=" + (thoiGianTao != null ? thoiGianTao.format(FORMATTER) : "null")
                + '}';
    }
}
