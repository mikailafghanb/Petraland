package com.rahmanarifofficial.pertaland.api;

import com.google.android.gms.maps.model.LatLng;
import com.rahmanarifofficial.pertaland.model.address.Alamat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("maps/api/geocode/json")
    Call<Alamat> getAlamat(@Query("latlng") String latlang, @Query("key") String key);
}
