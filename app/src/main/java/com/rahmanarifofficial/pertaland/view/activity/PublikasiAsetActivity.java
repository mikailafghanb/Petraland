package com.rahmanarifofficial.pertaland.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.adapter.AsetListAdapter;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.presenter.AnalisisPresenter;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;

import java.util.ArrayList;
import java.util.Collections;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.TAG_APLIKASI;

public class PublikasiAsetActivity extends AppCompatActivity implements AnalisisView {

    private AsetListAdapter adapter;
    private ArrayList<Aset> asets;
    private RecyclerView rvAset;
    private ProgressBar pbLoadingAset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publikasi_aset);

        getSupportActionBar().setTitle(getString(R.string.publikasi_asset_menu));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pbLoadingAset = findViewById(R.id.pb_loading_publikasi_aset);
        rvAset = findViewById(R.id.rv_list_publikasi_aset);
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
        adapter = new AsetListAdapter(this, asets, Globe_Variable.PUBLIKASI_CLASS);
        rvAset.setAdapter(adapter);
    }

    @Override
    public void failedShowingAsets(String error) {
        Log.d(TAG_APLIKASI, error);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return false;
        }
    }
}
