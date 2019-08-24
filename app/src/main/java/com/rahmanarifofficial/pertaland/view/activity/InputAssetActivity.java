package com.rahmanarifofficial.pertaland.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.rahmanarifofficial.pertaland.MainActivity;
import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.presenter.InputPresenter;
import com.rahmanarifofficial.pertaland.util.Formatter;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;

import java.util.ArrayList;

public class InputAssetActivity extends AppCompatActivity implements View.OnClickListener, InputView {

    private EditText etNama, etFasilitas, etKeterangan;
    private TextView tvLuasTanah, tvLokasi;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_asset);
        getSupportActionBar().setTitle(getString(R.string.input_asset_menu));

        initViews();
        setViews();
    }

    private void initViews() {
        etNama = findViewById(R.id.et_nama_tanah);
        etFasilitas = findViewById(R.id.et_fasilitas);
        etKeterangan = findViewById(R.id.et_keterangan);
        tvLuasTanah = findViewById(R.id.tv_luas_tanah);
        tvLokasi = findViewById(R.id.tv_lokasi_tanah);
        Button btnSimpan = findViewById(R.id.btn_simpan);
        pbLoading = findViewById(R.id.pb_loading_simpan);
        btnSimpan.setOnClickListener(this);
    }

    private void setViews() {
        tvLuasTanah.setText(Formatter.areaFormatter(getIntent().getDoubleExtra(Globe_Variable.LUAS_AREA, 0)));
        tvLokasi.setText(getIntent().getStringExtra(Globe_Variable.LOKASI));
    }

    @Override
    public void onClick(View v) {
        String idAset = InputPresenter.getIdAset();
        String nama = etNama.getText().toString();
        String fasilitas = etFasilitas.getText().toString();
        String keterangan = etKeterangan.getText().toString();
        double luasTanah = getIntent().getDoubleExtra(Globe_Variable.LUAS_AREA, 0);
        String lokasi = tvLokasi.getText().toString();
        ArrayList<LatLng> latLngs = getIntent().getParcelableArrayListExtra(Globe_Variable.LIST_LAT_LANG);

        Aset aset = new Aset(idAset, nama, luasTanah, lokasi, fasilitas, keterangan);//, latLngs);
        InputPresenter.simpanAset(idAset, aset, this);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pbLoading.setProgress(0);
            }
        }, 500);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
