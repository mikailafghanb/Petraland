package com.rahmanarifofficial.pertaland.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Aset {
    private String namaBidang;
    private double luasBidang;
    private String lokasi;
    private String fasilitas;
    private String keterangan;
    private List<LatLng> latLangs;

    public Aset(String namaBidang, double luasBidang, String lokasi, String fasilitas, String keterangan, List<LatLng> latLangs) {
        this.namaBidang = namaBidang;
        this.luasBidang = luasBidang;
        this.lokasi = lokasi;
        this.fasilitas = fasilitas;
        this.keterangan = keterangan;
        this.latLangs = latLangs;
    }

    public String getNamaBidang() {
        return namaBidang;
    }

    public double getLuasBidang() {
        return luasBidang;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public List<LatLng> getLatLangs() {
        return latLangs;
    }

}
