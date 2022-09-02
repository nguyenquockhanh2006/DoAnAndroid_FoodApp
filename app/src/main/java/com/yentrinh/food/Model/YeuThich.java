package com.yentrinh.food.Model;

public class YeuThich {
    private String Mamon;
    private String IdChuKho;

    public YeuThich(String mamon, String idChuKho) {
        Mamon = mamon;
        IdChuKho = idChuKho;
    }

    public YeuThich() {
    }

    public String getMamon() {
        return Mamon;
    }

    public void setMamon(String mamon) {
        Mamon = mamon;
    }

    public String getIdChuKho() {
        return IdChuKho;
    }

    public void setIdChuKho(String idChuKho) {
        IdChuKho = idChuKho;
    }
}
