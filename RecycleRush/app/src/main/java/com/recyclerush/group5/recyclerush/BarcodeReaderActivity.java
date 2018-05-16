package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;


public class BarcodeReaderActivity extends AppCompatActivity {
    CurrentUser currentUser = CurrentUser.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);

        final SearchView searchBar = findViewById(R.id.searchView);
        searchBar.setQueryHint("Enter barcode BITCH");
        searchBar.onActionViewExpanded();
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.setIconified(false);
            }
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                display(query);
                searchBar.setQuery("", false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeRight() {
                Intent backToMain = new Intent(BarcodeReaderActivity.this, MainActivity.class);
                backToMain.putExtra("user", currentUser.getUserName());
                startActivity(backToMain);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
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
}