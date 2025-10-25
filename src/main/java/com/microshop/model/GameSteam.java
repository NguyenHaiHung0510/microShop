package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameSteam {

    private Integer maGameSteam;
    private String tenGame;
    private String moTaGame;
    private BigDecimal giaGoc;
    private BigDecimal giaBan;
    private Integer luotXem;
    private LocalDateTime thoiGianDang;
    private String idVideoTrailer;
    private String duongDanAnh;

    public GameSteam() {
    }

    public GameSteam(Integer maGameSteam, String tenGame, String moTaGame, BigDecimal giaGoc,
            BigDecimal giaBan, Integer luotXem, LocalDateTime thoiGianDang,
            String idVideoTrailer, String duongDanAnh) {
        this.maGameSteam = maGameSteam;
        this.tenGame = tenGame;
        this.moTaGame = moTaGame;
        this.giaGoc = giaGoc;
        this.giaBan = giaBan;
        this.luotXem = luotXem;
        this.thoiGianDang = thoiGianDang;
        this.idVideoTrailer = idVideoTrailer;
        this.duongDanAnh = duongDanAnh;
    }

    // Getters
    public Integer getMaGameSteam() {
        return maGameSteam;
    }

    public String getTenGame() {
        return tenGame;
    }

    public String getMoTaGame() {
        return moTaGame;
    }

    public BigDecimal getGiaGoc() {
        return giaGoc;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public Integer getLuotXem() {
        return luotXem;
    }

    public LocalDateTime getThoiGianDang() {
        return thoiGianDang;
    }

    public String getIdVideoTrailer() {
        return idVideoTrailer;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    // Setters
    public void setMaGameSteam(Integer maGameSteam) {
        this.maGameSteam = maGameSteam;
    }

    public void setTenGame(String tenGame) {
        this.tenGame = tenGame;
    }

    public void setMoTaGame(String moTaGame) {
        this.moTaGame = moTaGame;
    }

    public void setGiaGoc(BigDecimal giaGoc) {
        this.giaGoc = giaGoc;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }

    public void setThoiGianDang(LocalDateTime thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }

    public void setIdVideoTrailer(String idVideoTrailer) {
        this.idVideoTrailer = idVideoTrailer;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String toString() {
        return "GameSteam{"
                + "maGameSteam=" + maGameSteam
                + ", tenGame='" + tenGame + '\''
                + ", moTaGame='" + (moTaGame != null && moTaGame.length() > 50 ? moTaGame.substring(0, 50) + "..." : moTaGame) + '\''
                + ", giaGoc=" + (giaGoc != null ? giaGoc.toPlainString() : "null")
                + ", giaBan=" + (giaBan != null ? giaBan.toPlainString() : "null")
                + ", luotXem=" + luotXem
                + ", thoiGianDang=" + (thoiGianDang != null ? thoiGianDang.format(FORMATTER) : "null")
                + ", idVideoTrailer='" + idVideoTrailer + '\''
                + ", duongDanAnh='" + duongDanAnh + '\''
                + '}';
    }
}
