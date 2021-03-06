package com.daviddev.salscaching;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daviddev.projetgocaching_intgration1.R;

public class ImageActivity extends Activity implements View.OnClickListener {

    Button map_button, find_image_button;
    ListView image_list;
    Intent intent;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        map_button = findViewById(R.id.map_button);
        map_button.setOnClickListener(this);

        find_image_button = findViewById(R.id.find_image_button);
        find_image_button.setOnClickListener(this);

        String path = Geocache.getNextURL();

        if (path == null) {
            Toast.makeText(this, "probleme d'affichage de l'image", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            imageView3 = findViewById(R.id.imageView3);
            imageView3.setImageURI(Uri.parse(path));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.map_button:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.find_image_button:
                if (GeocacheManager.isNfcEnable(this)){
                    intent = new Intent(this, ScanActivity.class);
                    startActivity(intent);
                    break;
                }
                else
                    Toast.makeText(this, "Vous devez activer NFC sur votre smartphone !", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
