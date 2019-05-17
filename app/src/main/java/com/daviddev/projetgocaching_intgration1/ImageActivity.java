package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ImageActivity extends Activity implements View.OnClickListener {

    Button map_button, find_image_button;
    ListView image_list;
    Intent intent;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

       // this.overridePendingTransition(R.anim.left_slide_in,R.anim.left_slide_out);

        map_button = findViewById(R.id.map_button);
        map_button.setOnClickListener(this);

        find_image_button = findViewById(R.id.find_image_button);
        find_image_button.setOnClickListener(this);

        image_list = findViewById(R.id.image_list);

      //  int id = Course.getCurrentGeocacheId();
        int id2 = Course.getNextGeocacheId();

        String path = Geocache.getNextURL();

        if (path == null) {
            Toast.makeText(this, "probleme d'affichage de l'image", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            imageView3 = findViewById(R.id.imageView3);
            imageView3.setImageURI(Uri.parse(path));
        }

    /*    int flags[] = {R.drawable.img1};

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),  flags);
        image_list.setAdapter(customAdapter);*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.map_button:
                intent = new Intent(this, MapsActivity.class);
              //  this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(intent);
                break;
            case R.id.find_image_button:
                intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
        }

    }
}
