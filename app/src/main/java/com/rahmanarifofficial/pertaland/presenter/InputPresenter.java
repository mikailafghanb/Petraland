package com.rahmanarifofficial.pertaland.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rahmanarifofficial.pertaland.model.Aset;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.ASET_TREE;

public class InputPresenter {
    private static DatabaseReference asetReference = FirebaseDatabase.getInstance().getReference(ASET_TREE);

    public static String getIdAset() {
        return "Aset" + asetReference.push().getKey();
    }

    public static void simpanAset(String idAset, Aset aset) {
        asetReference.child(idAset).setValue(aset);
    }
}
