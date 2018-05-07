package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    HashMap<String, ItemObject> map = new HashMap<String, ItemObject>();
    boolean first = true;
    //map for the uesrs
    //HashMap<String, CurrentUser> userMap = new HashMap<String, CurrentUser>();
    // The current user of the app, unknown as login-state
    CurrentUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("mainactivity", "test");
        super.onCreate(savedInstanceState);
        //start loginscreen, and wait for a loginresult
        currentUser = CurrentUser.getInstance();
        currentUser.setUserName("unknown");
        openScanner();
        //finish();
    }



    private void initDummyObjects(){
        ItemObject redbull = new ItemObject("7340131610000","Redbull", true, "metal" );
        map.put(redbull.getScanId(), redbull);
        ItemObject snus = new ItemObject("7311250004360", "Snus", true, "plastic, paper");
        map.put(snus.getScanId(), snus);
        ItemObject tom = new ItemObject("5901234123457", "Tom", true, "paper");
        map.put(tom.getScanId(), tom);
    }



    private void openScanner() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.setCaptureActivity(CustomScannerActivity.class);
        scanIntegrator.addExtra("name", currentUser.getUserName());
        scanIntegrator.addExtra("score", currentUser.getScore());
        scanIntegrator.initiateScan();
    }

    private void display(ItemObject obj) {

        Intent displayInfo = new Intent(this, SecondActivity.class);
        displayInfo.putExtra("scanId", obj.getScanId());
        displayInfo.putExtra("name", obj.getName());
        displayInfo.putExtra("materials", obj.getMaterials());

        currentUser.recycle(obj); // For now, we assume that scanning items means recycling them
        if (obj.isRecyclable()) {
            displayInfo.putExtra("recyc", "Recycable!");
        } else {
            displayInfo.putExtra("recyc", "Not Recycable!");
        }
        startActivity(displayInfo);
    }




    private void display(String barcode){
        display(getScannedItem(barcode));
    }

    private ItemObject getScannedItem(String id){
        return map.get(id);
    }

    
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

    /*
    private void addMember(String user){
        if (getUser(user) == null){
            //user not in list
            userMap.put(user, new User(user));
        }
    }
    private User getUser(String id){
        return userMap.get(id);
    }
    */

}