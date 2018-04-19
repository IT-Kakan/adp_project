package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by emillundgren on 2018-04-12.
 */

public class SecondActivity extends Activity{
    TextView text1;
    TextView text2;
    TextView text3;
    //TODO set text to this button when the name of the closest recycling place is known
    Button mapsButton;

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        text1=(TextView) findViewById(R.id.textView);
        text2=(TextView) findViewById(R.id.textView2);
        text3=(TextView) findViewById(R.id.textView3);
        mapsButton = findViewById(R.id.button_open_maps);

        text1.setText(getIntent().getExtras().getString("name"));
        text2.setText(getIntent().getExtras().getString("materials"));

        layout = (ConstraintLayout) findViewById(R.id.layout);
        Snackbar snack = Snackbar.make(layout, getIntent().getExtras().getString("recyc"), Snackbar.LENGTH_INDEFINITE);
        // View snackView = snack.getView();
        // TextView textView = snackView.findViewById(android.support.design.R.id.snackbar_text);
        // textView.setBackgroundColor(0xFF009446);

        snack.show();

        text3.setText("Material:");

    }

    public void openMaps(View view) {
        // TODO call openMaps using the coordinates and name of nearest recycling place from this method!
    }

    private void openMaps(double latitude, double longitude, String name) {
        String uri;
        if(name == null) {
            uri = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f(Recycling+Station)?z=12", latitude, longitude);
        } else {
            uri = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f(%s)?z=12", latitude, longitude, Uri.encode(name));
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
