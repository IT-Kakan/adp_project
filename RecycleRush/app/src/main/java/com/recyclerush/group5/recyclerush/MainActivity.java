package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.recyclerush.group5.recyclerush.itemObject;
import com.recyclerush.group5.recyclerush.SecondActivity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    HashMap<String, itemObject> map = new HashMap<String, itemObject>();
    // Create two objects, one for snus and one for redbull
    itemObject redbull = new itemObject("Redbull","7340131610000", true, "metal" );
    itemObject snus = new itemObject("Snus", "7311250004360", true, "plastic, paper");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("mainactivity", "test");
        super.onCreate(savedInstanceState);
        openScanner();
        setContentView(R.layout.activity_main);
    }

    private void openScanner() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    // use functions in the itemobjectclass to retrieve information about each object.
    //String snusname = snus.getName();
    //String snusID = snus.getScanId();
    String message = "";
    public void searchBarcode(View view){
        EditText usersBarcode = (EditText) findViewById(R.id.editText1);
            // Toast.makeText(MainActivity.this, usersBarcode.getText().toString(), Toast.LENGTH_SHORT).show();
        if (usersBarcode.getText().toString().isEmpty()) {
           // message = "Please enter a barcode";
            Toast.makeText(MainActivity.this, "Please enter a barcode", Toast.LENGTH_SHORT).show();
        } else {
            UserBarcode myNumber = new UserBarcode();
            myNumber.number = usersBarcode.getText().toString();

            if (myNumber.isRecylable()) {

                if (myNumber.number.equals("7340131610000")){
                    display(redbull);
                    // message = " It is redbull and It is recyclable!";
                } else if (myNumber.number.equals("7311250004360")){
                    display(snus);
                    //message = "It is snus and It is recyclable!";
                }
            } else {
                message = "Sorry, I can't identify this material.";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
            // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }

    }

  private void displayHelper(String scanId) {
        if (scanId.equals("7340131610000")) {
            display(redbull);
        } else  if (scanId.equals("7311250004360")){
            display(snus);
        }
    }

    private void display(itemObject obj) {
        Intent displayInfo = new Intent(this, SecondActivity.class);
        displayInfo.putExtra("scanId", obj.getScanId());
        displayInfo.putExtra("name", obj.getName());
        displayInfo.putExtra("materials", obj.getMaterials());

        if (obj.isRecycleable()) {
            displayInfo.putExtra("recyc", "Recycable!");
        } else {
            displayInfo.putExtra("recyc", "Not Recycable!");
        }
        startActivity(displayInfo);
    }


    private itemObject getScannedItem(String id){
            return map.get(id);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        super.onActivityResult(requestCode, resultCode, in);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, in);
        if (scanningResult != null) {
            try {
                Log.i("barcode", in.getStringExtra("SCAN_RESULT"));
                displayHelper(in.getStringExtra("SCAN_RESULT"));
            }
            catch (NullPointerException e){}
        }
    }
}