package com.rahmanarifofficial.pertaland.presenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rahmanarifofficial.pertaland.api.ApiBuilder;
import com.rahmanarifofficial.pertaland.api.ApiServices;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.model.Rekomendasi;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;
import com.rahmanarifofficial.pertaland.view.activity.AnalisisView;
import com.rahmanarifofficial.pertaland.view.activity.RekomendasiView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.ASET_TREE;

public class AnalisisPresenter {
    private static DatabaseReference asetReference = FirebaseDatabase.getInstance().getReference(ASET_TREE);

    public static void getListAset(final AnalisisView view) {
        view.showLoading();
        asetReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                view.showAsets(dataSnapshot.getValue(Aset.class));
                view.hideLoading();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.failedShowingAsets(databaseError.getMessage());
                view.hideLoading();
            }
        });
    }

    public static void getAset(String idAset, final AnalisisView view) {
        view.showLoading();
        asetReference.child(idAset).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                view.showAsets(dataSnapshot.getValue(Aset.class));
                view.hideLoading();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.failedShowingAsets(databaseError.getMessage());
                view.hideLoading();
            }
        });
    }

    public static void setRekomendasiAset(String idAset, String rekomendasi,final RekomendasiView view) {
        view.showLoading();
        asetReference.child(idAset).child(Globe_Variable.REKOMENDASI).setValue(rekomendasi);
        view.hideLoading();
    }

    public static void getRekomendasi(final RekomendasiView view) {
        view.showLoading();
        ApiServices apiServices = ApiBuilder.getRekomendasiClient().create(ApiServices.class);
        Call<Rekomendasi> call = apiServices.getRekomendasi();
        call.enqueue(new Callback<Rekomendasi>() {
            @Override
            public void onResponse(Call<Rekomendasi> call, Response<Rekomendasi> response) {
                if (response.body() != null) {
                    view.showRekomendasi(response.body());
                    view.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<Rekomendasi> call, Throwable t) {
                view.failedShowingRekomendasi(t.getMessage());
                view.hideLoading();
            }
        });
    }
}
