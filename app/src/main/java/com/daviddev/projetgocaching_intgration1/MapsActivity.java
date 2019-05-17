package com.daviddev.projetgocaching_intgration1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.Image;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
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
    Button find_map_button, maps_image_button;
    public static Activity maps;
    GoogleMap google;
    int o;

    double lat;

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

    /*    mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, mLocationListener);*/

    }

   /* private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {

            Log.d(">>>>>>>>>>>>>>>>", "latitude" +  location.getLatitude());

            Polyline line = gmap.addPolyline(new PolylineOptions()
                    .add(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(lat, longi))
                    .width(5)
                    .color(Color.RED));

        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };*/

    //La méthode onMapReady est appelée lorsque la carte est prête à être utilisée
    @Override
    public void onMapReady(GoogleMap googleMap) {
        google = googleMap;

        o = displayMap(Geocache.getNextLatitude(), Geocache.getNextLongitude(), googleMap, Geocache.getNextTitle());

    }

    //null
    //-91 91    -181 181
    //Pas d'instance de la database
    //format, type

    @SuppressLint("MissingPermission")
    public int displayMap(double latitude, double longitude, GoogleMap gmap, String title){

        gmap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //Activé la localisation du smartphone
        gmap.setMyLocationEnabled(true);

        gmap.getUiSettings().setMapToolbarEnabled(false);

       //Création d'un point GPS à partir de la latitude et de la longitude
        /*La valeur de latitude est comprise entre -90 et 90
        La valeur de latitude est comprise entre -180 et 180*/
        if(!(90 > latitude && latitude > -90 && 180 > longitude && longitude > -180)){
            return -1;
        }

        //La valeur de latitude est comprise entre -180 et 180
       LatLng geocachePoint = new LatLng(latitude ,longitude);
       //Ajout du point GPS sur la carte
       gmap.addMarker(new MarkerOptions().position(geocachePoint).title(title));

        //Création d'un niveau de zoom
        float zoomLevel = (float) 18.0;
        gmap.setMinZoomPreference((float) 16.0);
        //Centrer la carte sur le point GPS crée et régler le zoom
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(geocachePoint, zoomLevel));

        return 1;
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
