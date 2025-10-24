package com.microshop.model;

public class GameTaiKhoanSteam {

    private Integer maGameSteam;
    private Integer maTaiKhoanSteam;

    public GameTaiKhoanSteam() {
    }

    public GameTaiKhoanSteam(Integer maGameSteam, Integer maTaiKhoanSteam) {
        this.maGameSteam = maGameSteam;
        this.maTaiKhoanSteam = maTaiKhoanSteam;
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

    @Override
    public String toString() {
        return "GameTaiKhoanSteam{"
                + "maGameSteam=" + maGameSteam
                + ", maTaiKhoanSteam=" + maTaiKhoanSteam
                + '}';
    }
}
