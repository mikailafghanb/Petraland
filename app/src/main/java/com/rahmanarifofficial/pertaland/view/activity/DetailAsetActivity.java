package com.rahmanarifofficial.pertaland.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.api.ApiBuilder;
import com.rahmanarifofficial.pertaland.api.ApiServices;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.model.Rekomendasi;
import com.rahmanarifofficial.pertaland.presenter.AnalisisPresenter;
import com.rahmanarifofficial.pertaland.util.Formatter;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.TAG_APLIKASI;

public class DetailAsetActivity extends AppCompatActivity implements AnalisisView, View.OnClickListener {

    private TextView tvNamaBidang, tvLuasBidang, tvLokasi, tvKeterangan, tvFasilitas;
    private Button btnAnalisis;
    private ProgressBar pbLoadingAset;
    private String idAset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aset);

        getSupportActionBar().setTitle(getString(R.string.text_detail_aset));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        idAset = getIntent().getStringExtra(Globe_Variable.ID_ASET);
        AnalisisPresenter.getAset(idAset, this);
    }

    private void initViews() {
        tvNamaBidang = findViewById(R.id.tv_nama_bidang);
        tvLuasBidang = findViewById(R.id.tv_luas_bidang);
        tvLokasi = findViewById(R.id.tv_lokasi_bidang);
        tvKeterangan = findViewById(R.id.tv_keterangan_bidang);
        tvFasilitas = findViewById(R.id.tv_fasilitas_bidang);
        pbLoadingAset = findViewById(R.id.pb_loading_detail_aset);
        btnAnalisis = findViewById(R.id.btn_analisis);
        btnAnalisis.setOnClickListener(this);
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
        tvNamaBidang.setText(aset.getNamaBidang());
        tvFasilitas.setText(aset.getFasilitas());
        tvKeterangan.setText(aset.getKeterangan());
        tvLokasi.setText(aset.getLokasi());
        tvLuasBidang.setText(Formatter.areaFormatter(aset.getLuasBidang()));
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

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, RekomendasiActivity.class)
                .putExtra(Globe_Variable.ID_ASET, idAset)
                .putExtra(Globe_Variable.NAMA_BIDANG, tvNamaBidang.getText().toString())
                .putExtra(Globe_Variable.LUAS_BIDANG, tvLuasBidang.getText().toString())
                .putExtra(Globe_Variable.LOKASI, tvLokasi.getText().toString())
        );
        finish();
    }
}
