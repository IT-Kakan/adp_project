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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.recyclerush.group5.recyclerush.itemObject;
import com.recyclerush.group5.recyclerush.SecondActivity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import android.view.Menu;
import android.view.MenuItem;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;


public class MainActivity extends AppCompatActivity {
    HashMap<String, itemObject> map = new HashMap<String, itemObject>();
    // Create two objects, one for snus and one for redbull
    itemObject redbull = new itemObject("Redbull","7340131610000", true, "metal" );
    itemObject snus = new itemObject("Snus", "7311250004360", true, "plastic, paper");
    boolean first = true;
    //map for the uesrs
    HashMap<String, userClass> userMap = new HashMap<String, userClass>();
    // The current user of the app, unknown as login-state
    userClass currentUser = new userClass("unknown");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("mainactivity", "test");
        super.onCreate(savedInstanceState);
        //start loginscreen, and wait for a loginresult
        openScanner();
        setContentView(R.layout.activity_main);


        Button backButton = findViewById(R.id.backbuttonmanual);

        backButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                openScanner();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_camera:
                    openScanner();
                   // IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                    // scanIntegrator.initiateScan();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }



    private void openScanner() {

        try{
            String user = getIntent().getExtras().getString("user");
            addMember(user);
            currentUser = getUser(user);
        }catch(Exception e){
        }

        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.setCaptureActivity(CustomScannerActivity.class);
        scanIntegrator.addExtra("name", currentUser.getUser());
        scanIntegrator.addExtra("points", currentUser.getPoints());
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
    private void addMember(String user){
        if (getUser(user) == null){
            //user not in list
            userMap.put(user, new userClass(user));
        }
    }
    private userClass getUser(String id){
        return userMap.get(id);
    }
}