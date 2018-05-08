package com.recyclerush.group5.recyclerush;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by emillundgren on 2018-04-12.
 */

public class DisplayItemInfoActivity extends Activity{
    TextView text1;
    TextView text2;
    TextView text3;
    //TODO set text to this button when the name of the closest recycling place is known
    Button mapsButton;
    LocationManager locationManager;
    double lat, lng;
    Location location;
    String recyclingName;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    ConstraintLayout layout;
    CurrentUser currentUser = CurrentUser.getInstance();

    @Override
    //@TargetApi(23)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item_info);

        text1=(TextView) findViewById(R.id.textView);
        text2=(TextView) findViewById(R.id.textView2);
        text3=(TextView) findViewById(R.id.textView3);
        mapsButton = findViewById(R.id.button_open_maps);

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMaps(v);

            }
        });

        ImageButton cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(DisplayItemInfoActivity.this, MainActivity.class);
                backToMain.putExtra("user", currentUser.getUserName());
                startActivity(backToMain);
            }
        });

        //find object
        ItemObject item = ItemObject.getScannedItem(getIntent().getExtras().getString("scanId"));
        if (item==null) {
            Log.d("DEBUG", "item == null");
        }else {
            text1.setText(item.getName());
            text2.setText(item.getMaterials());

            layout = (ConstraintLayout) findViewById(R.id.layout);
            String isRecyclableText;
            if (item.isRecyclable())
                isRecyclableText = "Recycable!";
            else
                isRecyclableText = "Not Recycable!";

            Snackbar snack = Snackbar.make(layout, isRecyclableText, Snackbar.LENGTH_INDEFINITE);
            snack.show();

            text3.setText("Material:");

            setupLocation();
        }
    }

    private void setupLocation(){
        LocationManager locationManager= (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //Log.d("Network", "Network");
        //if (locationManager!= null){
        // location= locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location!= null){
            lat= location.getAltitude();
            lng=location.getLongitude();
        }
        // }
        LocationListener locationListener= new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat= location.getAltitude();
                lng= location.getLongitude();

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
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED) {
            Log.i("Location", "no permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

            return;

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }
    public void onRequestPermissionResult(int requestCode, String permission[], int[]grantResult){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_LOCATION:{
                if (grantResult.length>0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                        LocationListener locationListener= new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                lat = location.getAltitude();
                                lng= location.getLongitude();
                                Log.i("LocationUpdate", location.getLatitude()+","+ location.getLongitude());

                            }

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {

                            }

                            @Override
                            public void onProviderEnabled(String provider) {

                            }

                            @Override
                            public void onProviderDisabled(String provider)
                            {

                            }
                        };

                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    }
                }else
                {

                }
                return;

            }
        }

    }



    public void openMaps(View view) {
        Location UserCurrentLoc = new Location("");
        UserCurrentLoc.setLatitude(lat);
        UserCurrentLoc.setLongitude(lng);

        Location FirstDestination  = new Location("");
        FirstDestination.setLatitude(57.718636);
        FirstDestination.setLongitude(12.039979);

        Location SecondDestination  = new Location("");
        SecondDestination.setLatitude(57.734782);
        SecondDestination.setLongitude(12.062007);

        float distanceInMeters =UserCurrentLoc.distanceTo(FirstDestination);
        float distanceInMeters2 =UserCurrentLoc.distanceTo(SecondDestination);
        if(distanceInMeters<distanceInMeters2)
        {
            openMaps(FirstDestination.getLatitude(),FirstDestination.getLongitude(),recyclingName);

        }
        else
        {
            openMaps(SecondDestination.getLatitude(),SecondDestination.getLongitude(),recyclingName);

        }

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
