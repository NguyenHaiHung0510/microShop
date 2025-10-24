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

    public Integer getMaGameSteam() {
        return maGameSteam;
    }

    public String getTieuDeBaiViet() {
        return tieuDeBaiViet;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    public LocalDateTime getThoiGianCapNhatCuoi() {
        return thoiGianCapNhatCuoi;
    }

    public void setMaBaiViet(Integer maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public void setMaGameSteam(Integer maGameSteam) {
        this.maGameSteam = maGameSteam;
    }

    public void setTieuDeBaiViet(String tieuDeBaiViet) {
        this.tieuDeBaiViet = tieuDeBaiViet;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
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
