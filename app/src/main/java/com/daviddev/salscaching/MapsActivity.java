package com.daviddev.salscaching;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daviddev.projetgocaching_intgration1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{

    Intent intent;
    Button find_map_button, maps_image_button;
    public static Activity maps;
    GoogleMap google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        maps = this;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        find_map_button = findViewById(R.id.find_map_button);
        find_map_button.setOnClickListener(this);

        maps_image_button = findViewById(R.id.maps_image_button);
        maps_image_button.setOnClickListener(this);

        //Demande d'accés à la permission de localisation GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);
        }

    }

    /**
     * Méthode appelée lorsque la carte est prête à être affichée
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //Activé la localisation du smartphone
        googleMap.setMyLocationEnabled(true);

        googleMap.getUiSettings().setMapToolbarEnabled(false);

        //La valeur de latitude est comprise entre -180 et 180
        LatLng geocachePoint = new LatLng(Geocache.getNextLatitude() ,Geocache.getNextLongitude());
        //Ajout du point GPS sur la carte
        googleMap.addMarker(new MarkerOptions().position(geocachePoint).title(Geocache.getNextTitle()));

        //Création d'un niveau de zoom
        float zoomLevel = (float) 18.0;
        googleMap.setMinZoomPreference((float) 16.0);
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
                break;

            case R.id.maps_image_button:
                intent = new Intent(this, ImageActivity.class);
                startActivity(intent);
                this.finish();
                break;

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Toast.makeText(this, "Avant de scanner vous devez appuyer sur \"J'ai trouver le Géocache!\"", Toast.LENGTH_LONG).show();
        }
    }

    // Disable Catching Nfc Intents
    @Override
    protected void onPause() {
        super.onPause();
        GeocacheManager.disableCatchingNfcIntents(this);
    }

    //Enable Catching Nfc intents
    @Override
    protected void onResume() {
        super.onResume();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // enableCatchingNfcIntents function must be called in onResume methode.
        //L'option FLAG_RECEIVER_REPLACE_PENDING permet a WriteActivity de rester au premier plan
        Intent intent = new Intent(this, MapsActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        //Argument 1 pour enableForegroundDIspatch
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //Argument 2 pour enableForegroundDIspatch
        IntentFilter[] intentFilter = new IntentFilter[]{};
        //Permet de gérer les évenements NFC sur cette activité
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);

    }

}
