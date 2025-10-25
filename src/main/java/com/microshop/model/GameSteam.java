package com.microshop.model;

public class GameSteam {

    private Integer maGameSteam;
    private String tenGame;
    private String moTaGame;
    private String idVideoTrailer;
    private String duongDanAnh;

    public GameSteam() {
    }

    public GameSteam(Integer maGameSteam, String tenGame, String moTaGame,
                     String idVideoTrailer, String duongDanAnh) {
        this.maGameSteam = maGameSteam;
        this.tenGame = tenGame;
        this.moTaGame = moTaGame;
        this.idVideoTrailer = idVideoTrailer;
        this.duongDanAnh = duongDanAnh;
    }

    public Integer getMaGameSteam() {
        return maGameSteam;
    }

    public String getTenGame() {
        return tenGame;
    }

    public String getMoTaGame() {
        return moTaGame;
    }

    public String getIdVideoTrailer() {
        return idVideoTrailer;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setMaGameSteam(Integer maGameSteam) {
        this.maGameSteam = maGameSteam;
    }

    public void setTenGame(String tenGame) {
        this.tenGame = tenGame;
    }

    public void setMoTaGame(String moTaGame) {
        this.moTaGame = moTaGame;
    }

    public void setIdVideoTrailer(String idVideoTrailer) {
        this.idVideoTrailer = idVideoTrailer;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    @Override
    public String toString() {
        return "GameSteam{" +
                "maGameSteam=" + maGameSteam +
                ", tenGame='" + tenGame + '\'' +
                ", moTaGame='" + moTaGame + '\'' +
                ", idVideoTrailer='" + idVideoTrailer + '\'' +
                ", duongDanAnh='" + duongDanAnh + '\'' +
                '}';
    }
}
