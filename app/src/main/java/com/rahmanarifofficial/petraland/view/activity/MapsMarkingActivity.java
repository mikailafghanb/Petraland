package com.rahmanarifofficial.petraland.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.rahmanarifofficial.petraland.R;

import java.util.ArrayList;
import java.util.List;

public class MapsMarkingActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener {

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
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
}
