package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {

            int geocacheId = Integer.parseInt(GeocacheManager.readGeocacheId(intent));

            if (geocacheId != Course.getNextGeocacheId())
                Toast.makeText(this, "mauvais géocache >" + Integer.toString(Course.getNextGeocacheId()), Toast.LENGTH_LONG).show();
            else{
                Course.geocacheHasBeenScanned();
                Geocache.setupGeocache();
                intent = new Intent(this, QuestionActivity.class);
                startActivity(intent);
                this.finish();
            }

        }
    }

    @Override
    public void onClick(View v) {
        ScanActivity.this.finish();
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
