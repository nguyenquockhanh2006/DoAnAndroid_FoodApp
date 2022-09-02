package com.yentrinh.food.Model;

public class Follow {
    private String IdFled;
    private String idfl;

    public Follow(String idFled, String idfl) {
        IdFled = idFled;
        this.idfl = idfl;
    }

    public Follow() {
    }

    public String getIdFled() {
        return IdFled;
    }

    public void setIdFled(String idFled) {
        IdFled = idFled;
    }

    public String getIdfl() {
        return idfl;
    }

    public void setIdfl(String idfl) {
        this.idfl = idfl;
    }
}
