package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoanFreeFire extends TaiKhoan {

    private Integer soNhanVat;
    private Integer soSkin;
    private String capDo;
    private String rank;
    private String moTa;

    public TaiKhoanFreeFire() {
    }

    public TaiKhoanFreeFire(Integer maTaiKhoan, String tenDangNhap, String matKhau,
                            BigDecimal giaBan, String trangThai, Integer maNguoiBan,
                            LocalDateTime thoiGianDang, Integer soNhanVat, Integer soSkin,
                            String capDo, String rank, String moTa) {
        super(maTaiKhoan, tenDangNhap, matKhau, giaBan, trangThai, maNguoiBan, thoiGianDang);
        this.soNhanVat = soNhanVat;
        this.soSkin = soSkin;
        this.capDo = capDo;
        this.rank = rank;
        this.moTa = moTa;
    }

    public Integer getSoNhanVat() {
        return soNhanVat;
    }

    public Integer getSoSkin() {
        return soSkin;
    }

    public String getCapDo() {
        return capDo;
    }

    public String getRank() {
        return rank;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setSoNhanVat(Integer soNhanVat) {
        this.soNhanVat = soNhanVat;
    }

    public void setSoSkin(Integer soSkin) {
        this.soSkin = soSkin;
    }

    public void setCapDo(String capDo) {
        this.capDo = capDo;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "TaiKhoanFreeFire{" +
                "soNhanVat=" + soNhanVat +
                ", soSkin=" + soSkin +
                ", capDo='" + capDo + '\'' +
                ", rank='" + rank + '\'' +
                ", moTa='" + moTa + '\'' +
                "} " + super.toString();
    }
}
