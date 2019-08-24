package com.rahmanarifofficial.pertaland.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.SphericalUtil;
import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;

import java.util.ArrayList;
import java.util.List;

public class MapsMarkingActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener,
        View.OnClickListener {

    private GoogleMap myMap;
    private Boolean markerClicked;
    private PolygonOptions polygonOptions;
    private Polygon polygon;
    private List<LatLng> latLngs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_marking);
        getSupportActionBar().setTitle(getString(R.string.input_asset_menu));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Bind View
        Button btnNext = findViewById(R.id.btn_next);
        ImageButton btnDelete = findViewById(R.id.btn_delete_marker);
        btnNext.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        myMap.setMyLocationEnabled(true);
        myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        myMap.setOnMapClickListener(this);
        myMap.setOnMapLongClickListener(this);
        myMap.setOnMarkerClickListener(this);

        markerClicked = false;
        myMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-6, 106)));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        myMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        markerClicked = false;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        myMap.addMarker(new MarkerOptions().position(latLng).title(latLng.toString()));
        latLngs.add(latLng);
        markerClicked = false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (markerClicked) {
            if (polygon != null) {
                polygon.remove();
                polygon = null;
            }
            polygonOptions.add(marker.getPosition());
            polygonOptions.strokeColor(Color.WHITE);
            polygonOptions.fillColor(Color.RED);
            polygon = myMap.addPolygon(polygonOptions);
        } else {
            if (polygon != null) {
                polygon.remove();
                polygon = null;
            }
            polygonOptions = new PolygonOptions().add(marker.getPosition());
            markerClicked = true;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (latLngs.size() > 2) {
                    double luasArea = SphericalUtil.computeArea(latLngs);
                    startActivity(new Intent(this, InputAssetActivity.class)
                            .putExtra(Globe_Variable.LUAS_AREA, luasArea));
                } else {
                    Toast.makeText(this, "Tentukan Area Terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_delete_marker:
                myMap.clear();
                latLngs.clear();
                break;
        }
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
