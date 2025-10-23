package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DonHangSlotSteam {

    private int maDonHangSlot;
    private int maNguoiDung;
    private int maGameSteam;
    private int maTaiKhoanSteam;
    private BigDecimal giaMua;
    private LocalDateTime thoiGianMua;
    private String trangThai;
    private LocalDateTime thoiGianTao;

    public DonHangSlotSteam() {

    }

    public DonHangSlotSteam(int maDonHangSlot, int maNguoiDung, int maGameSteam,
            int maTaiKhoanSteam, BigDecimal giaMua, LocalDateTime thoiGianMua,
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

    public int getMaDonHangSlot() {
        return maDonHangSlot;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public int getMaGameSteam() {
        return maGameSteam;
    }

    public int getMaTaiKhoanSteam() {
        return maTaiKhoanSteam;
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

    public void setMaDonHangSlot(int maDonHangSlot) {
        this.maDonHangSlot = maDonHangSlot;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public void setMaGameSteam(int maGameSteam) {
        this.maGameSteam = maGameSteam;
    }

    public void setMaTaiKhoanSteam(int maTaiKhoanSteam) {
        this.maTaiKhoanSteam = maTaiKhoanSteam;
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
