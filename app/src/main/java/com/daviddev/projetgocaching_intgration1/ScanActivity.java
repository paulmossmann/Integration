package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ScanActivity extends Activity implements View.OnClickListener {

























    RelativeLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        lay = findViewById(R.id.lay);
        lay.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.9));

        GeocacheManager.enableCatchingNfcIntents(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {

            String geocacheId = GeocacheManager.readGeocacheId(intent);

            Toast.makeText(this, "id: " + geocacheId, Toast.LENGTH_SHORT).show();

            intent = new Intent(this, MapsActivity.class);
            intent.putExtra("geocacheId", geocacheId);
            startActivity(intent);

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
