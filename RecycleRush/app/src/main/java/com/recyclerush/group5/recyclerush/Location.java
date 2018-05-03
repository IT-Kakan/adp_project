package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Locale;

/**
 * Created by mahshid on 5/3/18.
 */

public class Location extends Categories {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
