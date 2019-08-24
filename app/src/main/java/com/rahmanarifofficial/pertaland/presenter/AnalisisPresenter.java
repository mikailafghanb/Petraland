package com.rahmanarifofficial.pertaland.presenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.view.activity.AnalisisView;

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
}
