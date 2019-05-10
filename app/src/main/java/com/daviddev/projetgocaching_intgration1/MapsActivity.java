package com.daviddev.projetgocaching_intgration1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{

    Intent intent;
    Button find_map_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        find_map_button = findViewById(R.id.find_map_button);
        find_map_button.setOnClickListener(this);

        //Demande d'accés à la permission de localisation GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);
        }
    }

    @Override

    public void onMapReady(GoogleMap googleMap) {

        //Création d'un point GPS à partir de la latitude et de la longitude
        LatLng geocachePoint = new LatLng( Geocache.getNextLatitude(), Geocache.getNextLongitude());
        //Création d'un niveau de zoom
        float zoomLevel = (float) 18.0;
        //Selection du type de carte
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //Ajout du point GPS sur la carte
        googleMap.addMarker(new MarkerOptions().position(geocachePoint).title(Geocache.getNextTitle()));
        //Activé la localisation du smartphone
        googleMap.setMyLocationEnabled(true);
        //Centrer la carte sur le point GPS crée et régler le zoom
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geocachePoint, zoomLevel));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_map_button:

                if (GeocacheManager.isNfcEnable(this)){
                    intent = new Intent(this, ScanActivity.class);
                    startActivity(intent);
                    break;
                }
                else
                    Toast.makeText(this, "Vous devez activer NFC sur votre smartphone !", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        GeocacheManager.disableCatchingNfcIntents(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GeocacheManager.enableCatchingNfcIntents(this);
    }
}
