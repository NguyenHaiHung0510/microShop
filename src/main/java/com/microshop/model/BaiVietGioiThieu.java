package com.microshop.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaiVietGioiThieu {

    private Integer maBaiViet;
    private Integer maGameSteam;
    private String tieuDeBaiViet;
    private String noiDung;
    private LocalDateTime thoiGianTao;
    private LocalDateTime thoiGianCapNhatCuoi;

    public BaiVietGioiThieu() {
    }

    public BaiVietGioiThieu(Integer maBaiViet, Integer maGameSteam, String tieuDeBaiViet,
            String noiDung, LocalDateTime thoiGianTao, LocalDateTime thoiGianCapNhatCuoi) {
        this.maBaiViet = maBaiViet;
        this.maGameSteam = maGameSteam;
        this.tieuDeBaiViet = tieuDeBaiViet;
        this.noiDung = noiDung;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhatCuoi = thoiGianCapNhatCuoi;
    }

    public Integer getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(Integer maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public Integer getMaGameSteam() {
        return maGameSteam;
    }

    public void setMaGameSteam(Integer maGameSteam) {
        this.maGameSteam = maGameSteam;
    }

    public String getTieuDeBaiViet() {
        return tieuDeBaiViet;
    }

    public void setTieuDeBaiViet(String tieuDeBaiViet) {
        this.tieuDeBaiViet = tieuDeBaiViet;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public LocalDateTime getThoiGianCapNhatCuoi() { 
        return thoiGianCapNhatCuoi;
    }

    public void setThoiGianCapNhatCuoi(LocalDateTime thoiGianCapNhatCuoi) { 
        this.thoiGianCapNhatCuoi = thoiGianCapNhatCuoi;
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String toString() {
        return "BaiVietGioiThieu{"
                + "maBaiViet=" + maBaiViet
                + ", maGameSteam=" + maGameSteam
                + ", tieuDeBaiViet='" + tieuDeBaiViet + '\''
                + ", thoiGianTao=" + (thoiGianTao != null ? thoiGianTao.format(FORMATTER) : "null")
                + ", thoiGianCapNhatCuoi=" + (thoiGianCapNhatCuoi != null ? thoiGianCapNhatCuoi.format(FORMATTER) : "null")
                + '}';
    }
}
