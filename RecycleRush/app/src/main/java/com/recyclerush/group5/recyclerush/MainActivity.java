package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    boolean first = true;
    //map for the uesrs
    HashMap<String, User> userMap = new HashMap<String, User>();
    // The current user of the app, unknown as login-state
    User currentUser = new User("unknown");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("mainactivity", "test");
        super.onCreate(savedInstanceState);
        //start loginscreen, and wait for a loginresult
        ItemObject.initDummyObjects();
        openScanner();
        finish();

    }

<<<<<<< HEAD

    private void initDummyObjects(){
        ItemObject redbull = new ItemObject("Redbull","7340131610000", true, "metal" );
        map.put(redbull.getScanId(), redbull);
        ItemObject snus = new ItemObject("Snus", "7311250004360", true, "plastic, paper");
        map.put(snus.getScanId(), snus);
        ItemObject tom = new ItemObject("Tom", "5901234123457", true, "paper");
        map.put(tom.getScanId(), tom);
    }

=======
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
>>>>>>> f64ea5331221f73285ae34ec204d41d51235ef15

    private void openScanner() {
        try{
            String user = getIntent().getExtras().getString("user");
            addMember(user);
            currentUser = getUser(user);
        }catch(Exception e){
        }

        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.setCaptureActivity(CustomScannerActivity.class);
        scanIntegrator.addExtra("name", currentUser.getUserName());
        scanIntegrator.addExtra("score", currentUser.getScore());
        scanIntegrator.initiateScan();
    }

<<<<<<< HEAD
        }

=======
    // Search item by textually intputting the barcode
    public void searchBarcode(View view){
        EditText usersBarcode = (EditText) findViewById(R.id.editText1);
        if (usersBarcode.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter a barcode", Toast.LENGTH_SHORT).show();
        } else {
            display(ItemObject.getScannedItem(usersBarcode.getText().toString()));
        }
    }
>>>>>>> f64ea5331221f73285ae34ec204d41d51235ef15

    private void display(ItemObject obj) {

        Intent displayInfo = new Intent(this, SecondActivity.class);
        displayInfo.putExtra("scanId", obj.getScanId());
        displayInfo.putExtra("name", obj.getName());
        displayInfo.putExtra("materials", obj.getMaterials());

        if (obj.isRecycleable()) {
            currentUser.addScore(obj.getScore());
            displayInfo.putExtra("recyc", "Recycable!");
        } else {
            displayInfo.putExtra("recyc", "Not Recycable!");
        }
        startActivity(displayInfo);
    }
<<<<<<< HEAD



    private void display(String barcode){
        display(getScannedItem(barcode));
    }

    private ItemObject getScannedItem(String id){
        return map.get(id);
    }
=======
>>>>>>> f64ea5331221f73285ae34ec204d41d51235ef15
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        super.onActivityResult(requestCode, resultCode, in);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, in);
        if (scanningResult != null) {
            try {
                Log.i("barcode", in.getStringExtra("SCAN_RESULT"));
                display(ItemObject.getScannedItem(in.getStringExtra("SCAN_RESULT")));
            }
            catch (NullPointerException e){}
        }
    }
    private void addMember(String user){
        if (getUser(user) == null){
            //user not in list
            userMap.put(user, new User(user));
        }
    }
    private User getUser(String id){
        return userMap.get(id);
    }


}