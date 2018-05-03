package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

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

                if(username.equals("unknown")){
                    Intent userAct = new Intent(CustomScannerActivity.this, UserActivity.class);
                    startActivityForResult(userAct, 100);
                } else {
                    Intent startDisplayUser  = new Intent (CustomScannerActivity.this, DisplayUser.class);
                    startDisplayUser.putExtra("score", points);
                    startDisplayUser.putExtra("username", username);
                    startActivity(startDisplayUser);
                }
            }

        });

        Button enterBarcode = findViewById(R.id.button3);

        enterBarcode.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();
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