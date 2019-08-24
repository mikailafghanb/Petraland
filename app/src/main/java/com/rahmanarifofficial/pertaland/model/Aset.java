package com.rahmanarifofficial.pertaland.model;

public class Aset {
    private String idAset;
    private String namaBidang;
    private double luasBidang;
    private String lokasi;
    private String fasilitas;
    private String keterangan;
    private String rekomendasi;
//    private List<LatLng> latLangs;

    public Aset() {
    }

    public Aset(String idAset, String namaBidang, double luasBidang, String lokasi, String fasilitas, String keterangan,
//            , List<LatLng> latLangs
                String rekomendasi) {
        this.idAset = idAset;
        this.namaBidang = namaBidang;
        this.luasBidang = luasBidang;
        this.lokasi = lokasi;
        this.fasilitas = fasilitas;
        this.keterangan = keterangan;
//        this.latLangs = latLangs;
        this.rekomendasi = rekomendasi;
    }

    public String getIdAset() {
        return idAset;
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

    public String getRekomendasi() {
        return rekomendasi;
    }

    //    public List<LatLng> getLatLangs() {
//        return latLangs;
//    }

}
