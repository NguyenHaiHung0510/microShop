package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoanRiot extends TaiKhoan {

    private String game;
    private String rank;
    private Integer soTuong;
    private Integer soSkin;
    private String moTa;

    public TaiKhoanRiot() {
    }

    public TaiKhoanRiot(Integer maTaiKhoan, String tenDangNhap, String matKhau,
                        BigDecimal giaBan, String trangThai, Integer maNguoiBan,
                        LocalDateTime thoiGianDang, String game, String rank,
                        Integer soTuong, Integer soSkin, String moTa) {
        super(maTaiKhoan, tenDangNhap, matKhau, giaBan, trangThai, maNguoiBan, thoiGianDang);
        this.game = game;
        this.rank = rank;
        this.soTuong = soTuong;
        this.soSkin = soSkin;
        this.moTa = moTa;
    }

    public String getGame() {
        return game;
    }

    public String getRank() {
        return rank;
    }

    public Integer getSoTuong() {
        return soTuong;
    }

    public Integer getSoSkin() {
        return soSkin;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSoTuong(Integer soTuong) {
        this.soTuong = soTuong;
    }

    public void setSoSkin(Integer soSkin) {
        this.soSkin = soSkin;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "TaiKhoanRiot{" +
                "game='" + game + '\'' +
                ", rank='" + rank + '\'' +
                ", soTuong=" + soTuong +
                ", soSkin=" + soSkin +
                ", moTa='" + moTa + '\'' +
                "} " + super.toString();
    }
}
