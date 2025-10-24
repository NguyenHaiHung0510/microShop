package com.microshop.model;

public class TaiKhoanRiot extends TaiKhoan {

    private Integer maTaiKhoanRiot;
    private Integer capDoRiot;
    private Integer soTuongLmht;
    private Integer soTrangPhucLmht;
    private Integer soDaSacLmht;
    private Integer soBieuCamLmht;
    private Integer soBieuTuongLmht;
    private String hangRankLmht;
    private String khungRankLmht;
    private Integer soThuCungTft;
    private Integer soSanDauTft;
    private Integer soChuongLucTft;

    public TaiKhoanRiot() {
    }

    public TaiKhoanRiot(Integer maTaiKhoan, Integer maTaiKhoanRiot, Integer capDoRiot, Integer soTuongLmht,
                        Integer soTrangPhucLmht, Integer soDaSacLmht, Integer soBieuCamLmht,
                        Integer soBieuTuongLmht, String hangRankLmht, String khungRankLmht,
                        Integer soThuCungTft, Integer soSanDauTft, Integer soChuongLucTft) {
        super();
        this.maTaiKhoanRiot = maTaiKhoanRiot;
        this.capDoRiot = capDoRiot;
        this.soTuongLmht = soTuongLmht;
        this.soTrangPhucLmht = soTrangPhucLmht;
        this.soDaSacLmht = soDaSacLmht;
        this.soBieuCamLmht = soBieuCamLmht;
        this.soBieuTuongLmht = soBieuTuongLmht;
        this.hangRankLmht = hangRankLmht;
        this.khungRankLmht = khungRankLmht;
        this.soThuCungTft = soThuCungTft;
        this.soSanDauTft = soSanDauTft;
        this.soChuongLucTft = soChuongLucTft;
        this.setMaTaiKhoan(maTaiKhoan);
    }

    public Integer getMaTaiKhoanRiot() {
        return maTaiKhoanRiot;
    }

    public void setMaTaiKhoanRiot(Integer maTaiKhoanRiot) {
        this.maTaiKhoanRiot = maTaiKhoanRiot;
    }

    public Integer getCapDoRiot() {
        return capDoRiot;
    }

    public void setCapDoRiot(Integer capDoRiot) {
        this.capDoRiot = capDoRiot;
    }

    public Integer getSoTuongLmht() {
        return soTuongLmht;
    }

    public void setSoTuongLmht(Integer soTuongLmht) {
        this.soTuongLmht = soTuongLmht;
    }

    public Integer getSoTrangPhucLmht() {
        return soTrangPhucLmht;
    }

    public void setSoTrangPhucLmht(Integer soTrangPhucLmht) {
        this.soTrangPhucLmht = soTrangPhucLmht;
    }

    public Integer getSoDaSacLmht() {
        return soDaSacLmht;
    }

    public void setSoDaSacLmht(Integer soDaSacLmht) {
        this.soDaSacLmht = soDaSacLmht;
    }

    public Integer getSoBieuCamLmht() {
        return soBieuCamLmht;
    }

    public void setSoBieuCamLmht(Integer soBieuCamLmht) {
        this.soBieuCamLmht = soBieuCamLmht;
    }

    public Integer getSoBieuTuongLmht() {
        return soBieuTuongLmht;
    }

    public void setSoBieuTuongLmht(Integer soBieuTuongLmht) {
        this.soBieuTuongLmht = soBieuTuongLmht;
    }

    public String getHangRankLmht() {
        return hangRankLmht;
    }

    public void setHangRankLmht(String hangRankLmht) {
        this.hangRankLmht = hangRankLmht;
    }

    public String getKhungRankLmht() {
        return khungRankLmht;
    }

    public void setKhungRankLmht(String khungRankLmht) {
        this.khungRankLmht = khungRankLmht;
    }

    public Integer getSoThuCungTft() {
        return soThuCungTft;
    }

    public void setSoThuCungTft(Integer soThuCungTft) {
        this.soThuCungTft = soThuCungTft;
    }

    public Integer getSoSanDauTft() {
        return soSanDauTft;
    }

    public void setSoSanDauTft(Integer soSanDauTft) {
        this.soSanDauTft = soSanDauTft;
    }

    public Integer getSoChuongLucTft() {
        return soChuongLucTft;
    }

    public void setSoChuongLucTft(Integer soChuongLucTft) {
        this.soChuongLucTft = soChuongLucTft;
    }

    @Override
    public String toString() {
        return "TaiKhoanRiot{" +
                "maTaiKhoanRiot=" + maTaiKhoanRiot +
                ", capDoRiot=" + capDoRiot +
                ", soTuongLmht=" + soTuongLmht +
                ", soTrangPhucLmht=" + soTrangPhucLmht +
                ", soDaSacLmht=" + soDaSacLmht +
                ", soBieuCamLmht=" + soBieuCamLmht +
                ", soBieuTuongLmht=" + soBieuTuongLmht +
                ", hangRankLmht='" + hangRankLmht + '\'' +
                ", khungRankLmht='" + khungRankLmht + '\'' +
                ", soThuCungTft=" + soThuCungTft +
                ", soSanDauTft=" + soSanDauTft +
                ", soChuongLucTft=" + soChuongLucTft +
                '}';
    }
}
