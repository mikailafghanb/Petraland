package com.rahmanarifofficial.pertaland.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.adapter.AsetListAdapter;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.presenter.AnalisisPresenter;

import java.util.ArrayList;
import java.util.Collections;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.TAG_APLIKASI;

public class AnalisisActivity extends AppCompatActivity implements AnalisisView {

    private AsetListAdapter adapter;
    private ArrayList<Aset> asets;
    private RecyclerView rvAset;
    private ProgressBar pbLoadingAset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);

        pbLoadingAset = findViewById(R.id.pb_loading_aset);
        rvAset = findViewById(R.id.rv_list_aset);
        rvAset.setHasFixedSize(true);
        rvAset.setLayoutManager(new LinearLayoutManager(this));

        asets = new ArrayList<>();
        AnalisisPresenter.getListAset(this);
    }

    @Override
    public void showLoading() {
        pbLoadingAset.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoadingAset.setVisibility(View.GONE);
    }

    @Override
    public void showAsets(Aset aset) {
        asets.add(aset);
        Collections.reverse(asets);
        adapter = new AsetListAdapter(this, asets);
        rvAset.setAdapter(adapter);
    }

    @Override
    public void failedShowingAsets(String error) {
        Log.d(TAG_APLIKASI, error);
    }
}
