package com.rahmanarifofficial.pertaland.presenter;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rahmanarifofficial.pertaland.api.ApiBuilder;
import com.rahmanarifofficial.pertaland.api.ApiServices;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.model.address.Alamat;
import com.rahmanarifofficial.pertaland.view.activity.InputView;
import com.rahmanarifofficial.pertaland.view.activity.MapsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.ASET_TREE;
import static com.rahmanarifofficial.pertaland.util.Globe_Variable.TAG_APLIKASI;

public class InputPresenter {
    private static DatabaseReference asetReference = FirebaseDatabase.getInstance().getReference(ASET_TREE);

    public static String getIdAset() {
        return "Aset" + asetReference.push().getKey();
    }

    public static void simpanAset(String idAset, Aset aset, InputView view) {
        view.showLoading();
        asetReference.child(idAset).setValue(aset);
        view.hideLoading();
    }

    public static void getAlamat(String latLng, String key, final MapsView view) {
        ApiServices apiServices = ApiBuilder.getClient().create(ApiServices.class);
        Call<Alamat> call = apiServices.getAlamat(latLng, key);
        call.enqueue(new Callback<Alamat>() {
            @Override
            public void onResponse(Call<Alamat> call, Response<Alamat> response) {
                if (response.body() != null) {
                    view.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<Alamat> call, Throwable t) {
                view.onFailure(t.getMessage());
            }
        });
    }
}
