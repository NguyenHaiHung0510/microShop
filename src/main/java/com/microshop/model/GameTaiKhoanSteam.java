package com.microshop.model;

public class GameTaiKhoanSteam {

    private int maGameSteam;
    private int maTaiKhoanSteam;

    public GameTaiKhoanSteam() {

    }

    public GameTaiKhoanSteam(int maGameSteam, int maTaiKhoanSteam) {
        this.maGameSteam = maGameSteam;
        this.maTaiKhoanSteam = maTaiKhoanSteam;
    }

    public int getMaGameSteam() {
        return maGameSteam;
    }

    public int getMaTaiKhoanSteam() {
        return maTaiKhoanSteam;
    }

    public void setMaGameSteam(int maGameSteam) {
        this.maGameSteam = maGameSteam;
    }

    public void setMaTaiKhoanSteam(int maTaiKhoanSteam) {
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
