package com.rahmanarifofficial.pertaland.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.presenter.AnalisisPresenter;
import com.rahmanarifofficial.pertaland.util.Formatter;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.TAG_APLIKASI;

public class DetailAsetActivity extends AppCompatActivity implements AnalisisView {

    private TextView tvNamaBidang, tvLuasBidang, tvLokasi, tvKeterangan, tvFasilitas;
    private Button btnAnalisis;
    private ProgressBar pbLoadingAset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aset);

        initViews();

        String idAset = getIntent().getStringExtra(Globe_Variable.ID_ASET);
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
}
