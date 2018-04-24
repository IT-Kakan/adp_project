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
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.recyclerush.group5.recyclerush.itemObject;
import com.recyclerush.group5.recyclerush.SecondActivity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;


public class MainActivity extends AppCompatActivity {

    userClass currentUser;

    HashMap<String, itemObject> map = new HashMap<String, itemObject>();
    // Create two objects, one for snus and one for redbull
    itemObject redbull = new itemObject("Redbull","7340131610000", true, "metal" );
    itemObject snus = new itemObject("Snus", "7311250004360", true, "plastic, paper");

    HashMap<String, userClass> userMap = new HashMap<String, userClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("mainactivity", "test");
        super.onCreate(savedInstanceState);

        Intent userAct = new Intent(this, UserActivity.class);
        startActivityForResult(userAct, 100);

    }


    private void openScanner() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    // use functions in the itemobjectclass to retrieve information about each object.
    //String snusname = snus.getName();
    //String snusID = snus.getScanId();

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

        if(requestCode == 100){

                //if someone logged in, set that user as current
            String user = in.getStringExtra("name");
            addMember(user);
            currentUser = getUser(user);
                openScanner();
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