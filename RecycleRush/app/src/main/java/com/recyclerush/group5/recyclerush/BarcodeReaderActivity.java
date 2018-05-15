package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import android.view.Menu;

public class BarcodeReaderActivity extends AppCompatActivity {
    CurrentUser currentUser = CurrentUser.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeRight() {
                Intent backToMain = new Intent(BarcodeReaderActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

        ImageButton cameraButton = findViewById(R.id.cameraButton1);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cam = new Intent(BarcodeReaderActivity.this, MainActivity.class);
                startActivity(cam);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }

    public void barcodeRead(View view){
        EditText usersBarcode = findViewById(R.id.editText_1);
        if (usersBarcode.getText().toString().isEmpty()) {
            Toast.makeText(BarcodeReaderActivity.this, "Please enter a barcode", Toast.LENGTH_SHORT).show();
        } else {
            display(usersBarcode.getText().toString());
        }
    }

    private void display(String id) {
        if(ItemObject.doesExist(id)){
            currentUser.recycle(ItemObject.getScannedItem(id));
            Intent displayInfo = new Intent(this, DisplayItemInfoActivity.class);
            displayInfo.putExtra("scanId", id);
            startActivity(displayInfo);
        }else {
            Toast.makeText(getApplicationContext(), "Barcode not found.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
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