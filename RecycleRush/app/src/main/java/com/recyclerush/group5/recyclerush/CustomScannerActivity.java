package com.recyclerush.group5.recyclerush;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This activity has a margin.
 */
public class CustomScannerActivity extends CaptureActivity {
    TextView text1;
    TextView text2;

    @Override
    protected DecoratedBarcodeView initializeContent() {

        setContentView(R.layout.custom_barcode_scanner);

        DecoratedBarcodeView barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);




        barcodeScannerView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {

            public void onSwipeRight() {
                String username = getIntent().getStringExtra("name");

                Integer points = getIntent().getIntExtra("score",-1);

                // Here it should change view to the profile

                if (username.equals("unknown")) {
                    Intent userAct = new Intent(CustomScannerActivity.this, UserActivity.class);
                    startActivityForResult(userAct, 100);
                } else {

                    Intent startDisplayUser  = new Intent (CustomScannerActivity.this, DisplayUser.class);
                    startDisplayUser.putExtra("score", points);
                    startDisplayUser.putExtra("username", username);
                    startActivity(startDisplayUser);

                }
            }

            public void onSwipeLeft()
            {

                Intent intent = new Intent(CustomScannerActivity.this, BarcodeReaderActivity.class);
                startActivity(intent);
                //Intent barcodee = new Intent(CustomScannerActivity.this, ThirdActivtiy.class);
                //startActivity(barcodee);
            }


        });


        Button enterBarcode = (Button) findViewById(R.id.button3);
        enterBarcode.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

              // Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();
                    // Launch the third activity

                Intent intent = new Intent(CustomScannerActivity.this, BarcodeReaderActivity.class);
                startActivity(intent);
            }
        });



        return barcodeScannerView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        super.onActivityResult(requestCode, resultCode, in);

        if(requestCode == 100){
            //if someone logged in, set that user as current
            String user = in.getStringExtra("name");
            Intent sendToUserInfo = new Intent(this, MainActivity.class);
            sendToUserInfo.putExtra("user", user);
            startActivity(sendToUserInfo);
        }
    }

}

