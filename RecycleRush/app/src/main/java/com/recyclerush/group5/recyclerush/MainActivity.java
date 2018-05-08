package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    HashMap<String, ItemObject> map = new HashMap<String, ItemObject>();
    boolean first = true;
    // The current user of the app, unknown as login-state
    CurrentUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        //start loginscreen, and wait for a loginresult
        currentUser = CurrentUser.getInstance();
        if (!currentUser.isLoggedIn()) {
            currentUser.setUserName("unknown");
        }
        openScanner();
    }

    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
        currentUser = CurrentUser.getInstance();
        if (!currentUser.isLoggedIn()) {
            currentUser.setUserName("unknown");
        }
        openScanner();
    }

    private void openScanner() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.setCaptureActivity(CustomScannerActivity.class);
        scanIntegrator.addExtra("name", currentUser.getUserName());
        scanIntegrator.addExtra("score", currentUser.getScore());
        scanIntegrator.initiateScan();
    }


    private void display(String id) {
        if(ItemObject.doesExist(id)){
            //open display activity
            Intent displayInfo = new Intent(this, DisplayItemInfoActivity.class);
            displayInfo.putExtra("scanId", id);
            startActivity(displayInfo);
        }else {
            Toast.makeText(getApplicationContext(), "Barcode not found.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        Log.i("MainActivity", "onActivityResults");
        super.onActivityResult(requestCode, resultCode, in);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, in);
        if (scanningResult != null) {
            try {
                Log.i("barcode", in.getStringExtra("SCAN_RESULT"));
                display(in.getStringExtra("SCAN_RESULT"));
            }
            catch (NullPointerException e){}
        }
    }
}