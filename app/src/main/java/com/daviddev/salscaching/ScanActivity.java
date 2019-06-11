package com.daviddev.salscaching;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daviddev.projetgocaching_intgration1.R;

public class ScanActivity extends Activity implements View.OnClickListener {

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        layout = findViewById(R.id.lay);
        layout.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.9));

    }

    //Detection d'une nouvelle intention
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //Si l'intention correspond à un scan d'un tag NFC
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {

            int geocacheId = 0;

            try{

                geocacheId = Integer.parseInt(GeocacheManager.readGeocacheId(intent));

                //Si l'id du tag NFC scanné correspond à l'id attendu.
                if(isExpectedGeocache(geocacheId)){

                    //Mise à jour du parcours
                    Course.geocacheHasBeenScanned();
                    //Mise à jour des informations pour le géocache en cours
                    Geocache.setupGeocache();

                    //Configuration et lancement de l'activité de question.
                    intent = new Intent(this, QuestionActivity.class);
                    startActivity(intent);

                    MapsActivity.maps.finish();
                    this.finish();
                }
                //Si l'id du tag NFC scanné ne correspond pas à l'id attendu.
                else{
                    //Affichage d'un message d'erreur
                    Toast.makeText(this, "Vous scannez le mauvais géocache !", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {

                //Affichage du message d'erreur dans le cas ou une exception est lancée

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean isExpectedGeocache(int id){
        return id == Course.getNextGeocacheId();
    }

    @Override
    public void onClick(View v) {
        this.finish();
        GeocacheManager.disableCatchingNfcIntents(this);
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
        // enableCatchingNfcIntents function must be called in onResume methode.
        GeocacheManager.enableCatchingNfcIntents(this);

    }
}
