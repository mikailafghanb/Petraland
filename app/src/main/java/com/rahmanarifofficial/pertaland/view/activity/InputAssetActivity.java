package com.rahmanarifofficial.pertaland.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.presenter.InputPresenter;
import com.rahmanarifofficial.pertaland.util.Formatter;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;

public class InputAssetActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNama, etFasilitas, etKeterangan;
    private TextView tvLuasTanah, tvLokasi;
    private Button btnSimpan;

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
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(this);
    }

    private void setViews() {
        tvLuasTanah.setText(Formatter.areaFormatter(getIntent().getDoubleExtra(Globe_Variable.LUAS_AREA, 0)));
    }

    @Override
    public void onClick(View v) {
        String nama = etNama.getText().toString();
        String fasilitas = etFasilitas.getText().toString();
        String keterangan = etKeterangan.getText().toString();
        double luasTanah = getIntent().getDoubleExtra(Globe_Variable.LUAS_AREA, 0);
        String lokasi = tvLokasi.getText().toString();

        Aset aset = new Aset(nama, luasTanah, lokasi, fasilitas, keterangan);
        InputPresenter.simpanAset(InputPresenter.getIdAset(), aset);
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
    }
}
