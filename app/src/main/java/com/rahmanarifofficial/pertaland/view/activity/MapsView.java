package com.rahmanarifofficial.pertaland.view.activity;

import com.rahmanarifofficial.pertaland.model.address.Alamat;

public interface MapsView {
    void onResponse(Alamat alamat);
    void onFailure(String error);
}
