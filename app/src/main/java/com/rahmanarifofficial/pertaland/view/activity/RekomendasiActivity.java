package com.rahmanarifofficial.pertaland.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.model.Rekomendasi;
import com.rahmanarifofficial.pertaland.presenter.AnalisisPresenter;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;

import static com.rahmanarifofficial.pertaland.util.Globe_Variable.TAG_APLIKASI;

public class RekomendasiActivity extends AppCompatActivity implements RekomendasiView, View.OnClickListener {

    private TextView tvAset, tvRekomendasi;
    private Button btnOk;
    private ProgressBar pbLoading;
    private String hasil_rekomendasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi);

        getSupportActionBar().setTitle(getString(R.string.text_rekomendasi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        setViews();
        AnalisisPresenter.getRekomendasi(this);
    }

    private void initViews() {
        tvAset = findViewById(R.id.tv_detail_aset);
        tvRekomendasi = findViewById(R.id.tv_rekomendasi);
        pbLoading = findViewById(R.id.pb_loading_rekomendasi);
        btnOk = findViewById(R.id.btn_ok);
    }

    private void setViews() {
        String textAset = getIntent().getStringExtra(Globe_Variable.NAMA_BIDANG) +
                " dengan luas " + getIntent().getStringExtra(Globe_Variable.LUAS_BIDANG) +
                " yang berlokasi di " + getIntent().getStringExtra(Globe_Variable.LOKASI);
        tvAset.setText(textAset);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showRekomendasi(Rekomendasi rekomendasi) {
        String rekomHotel = "";
        String rekomMinimarket = "";
        String rekomSPBU = "";
        if (rekomendasi.getHotel()) {
            rekomHotel = "dapat";
        } else {
            rekomHotel = "tidak dapat";
        }

        if (rekomendasi.getMinimarket()) {
            rekomMinimarket = "dapat";
        } else {
            rekomMinimarket = "tidak dapat";
        }

        if (rekomendasi.getSpbu()) {
            rekomSPBU = "dapat";
        } else {
            rekomSPBU = "tidak dapat";
        }
        hasil_rekomendasi = "Tempat ini " + rekomHotel + " dibangun Hotel, " + rekomMinimarket +
                " dibangun Minimarket dan " + rekomSPBU + " dibangun SPBU";
        tvRekomendasi.setText(hasil_rekomendasi);
    }

    @Override
    public void failedShowingRekomendasi(String error) {
        Log.d(TAG_APLIKASI, error);
    }

    @Override
    public void onClick(View v) {
        String idAset = getIntent().getStringExtra(Globe_Variable.ID_ASET);
        AnalisisPresenter.setRekomendasiAset(idAset, hasil_rekomendasi, this);
        finish();
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
