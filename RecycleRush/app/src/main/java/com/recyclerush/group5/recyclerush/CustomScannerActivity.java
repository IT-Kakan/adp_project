package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

/**
 * This activity has a margin.
 */
public class CustomScannerActivity extends CaptureActivity {

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.custom_barcode_scanner);

        DecoratedBarcodeView barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);

        barcodeScannerView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeRight() {
                // Here it should change view to the profile
                Intent userAct = new Intent(CustomScannerActivity.this, UserActivity.class);
                startActivityForResult(userAct, 100);
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
}