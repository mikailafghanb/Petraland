package com.rahmanarifofficial.pertaland.model;

public class Aset {
    private String namaBidang;
    private double luasBidang;
    private String lokasi;
    private String fasilitas;
    private String keterangan;

    public Aset(String namaBidang, double luasBidang, String lokasi, String fasilitas, String keterangan) {
        this.namaBidang = namaBidang;
        this.luasBidang = luasBidang;
        this.lokasi = lokasi;
        this.fasilitas = fasilitas;
        this.keterangan = keterangan;
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
}
