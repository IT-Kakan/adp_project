package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;


/**
 * This activity has a margin.
 */
public class CustomScannerActivity extends CaptureActivity {
    TextView text2;

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_custom_barcode_scanner);

        View thisView = this.findViewById(android.R.id.content);
        thisView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop (){
                Intent backToMain = new Intent(CustomScannerActivity.this, CategoriesActivity.class);
                startActivity(backToMain);
            }

        });

        DecoratedBarcodeView barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeRight() {
                String username = CurrentUser.getInstance().getUserName();

                Integer points = CurrentUser.getInstance().getScore();

                // Here it should change view to the profile

                if (!CurrentUser.getInstance().isLoggedIn()) {
                    Intent userAct = new Intent(CustomScannerActivity.this, UserActivity.class);
                    startActivityForResult(userAct, 100);
                } else {
                    Intent startDisplayUser  = new Intent (CustomScannerActivity.this, DisplayUserActivity.class);
                    startActivity(startDisplayUser);
                }
            }

            public void onSwipeLeft() {
                Intent intent = new Intent(CustomScannerActivity.this, BarcodeReaderActivity.class);
                startActivity(intent);
            }

            public void onSwipeTop (){
                Intent backToMain = new Intent(CustomScannerActivity.this, CategoriesActivity.class);
                startActivity(backToMain);
            }
        });

        return barcodeScannerView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        Log.i("CustomScannerActivity", "onActivityResult");
        super.onActivityResult(requestCode, resultCode, in);

        if(requestCode == 100){
            //if someone logged in, set that user as current
            Intent sendToUserInfo = new Intent(this, MainActivity.class);
            startActivity(sendToUserInfo);
        }
    }

}

