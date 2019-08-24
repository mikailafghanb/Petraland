package com.rahmanarifofficial.pertaland.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rekomendasi {
    @SerializedName("hotel")
    @Expose
    private Boolean hotel;
    @SerializedName("minimarket")
    @Expose
    private Boolean minimarket;
    @SerializedName("spbu")
    @Expose
    private Boolean spbu;

    public Boolean getHotel() {
        return hotel;
    }

    public void setHotel(Boolean hotel) {
        this.hotel = hotel;
    }

    public Boolean getMinimarket() {
        return minimarket;
    }

    public void setMinimarket(Boolean minimarket) {
        this.minimarket = minimarket;
    }

    public Boolean getSpbu() {
        return spbu;
    }

    public void setSpbu(Boolean spbu) {
        this.spbu = spbu;
    }
}
