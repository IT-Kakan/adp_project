package com.recyclerush.group5.recyclerush;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;


import java.util.HashMap;

/**
 * Created by mahshid on 5/3/18.
 */

public class Categories extends AppCompatActivity{
    ListView CatList;

    HashMap<Integer, LocationObject> MapDatail = new HashMap<>();
    Context CTX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        CatList = findViewById(R.id.listView);
// Defined Array values to show in ListView
        String[] values = new String[] { "Automotive",
                "Batteries",
                "Glass",
                "Paper",
                "Plastic",
                "Electronics",
                "Aluminium"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);

        CatList.setAdapter(adapter);


        CatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Gson gs = new Gson();
                SetRecycleAddress(position);
                String json = gs.toJson(MapDatail.values());
                Intent displayMap = new Intent(Categories.this, LocationActivity.class);
                displayMap.putExtra("JsonValue", json);
                startActivity(displayMap);

            }
        });
    }

    public void SetRecycleAddress(int Value) {
        switch (Value) {
            case 0:

                LocationObject lc1 = new LocationObject(1, 57.762126, 11.996796, "Description1");
                MapDatail.put(1, lc1);
                LocationObject lc2 = new LocationObject(1, 57.754065, 12.005036, "Description2");
                MapDatail.put(2, lc2);
                LocationObject lc3 = new LocationObject(1, 57.753955, 12.009156, "Description3");
                MapDatail.put(3, lc3);
                break;

            case 1:
                LocationObject lc11 = new LocationObject(1, 57.726211, 11.972077, "2");
                MapDatail.put(1, lc11);
                LocationObject lc12 = new LocationObject(1, 57.729144, 11.956971, "2");
                MapDatail.put(2, lc12);
                //LocationObject lc13 = new LocationObject(1, 1.2, 1.3, "2");
                //MapDatail.put(3, lc13);
                break;

            case 2:

                LocationObject lc21 = new LocationObject(1, 57.736475, 11.862901, "2");
                MapDatail.put(1, lc21);
                LocationObject lc22 = new LocationObject(1, 57.725477, 11.836121, "2");
                MapDatail.put(2, lc22);
                LocationObject lc23 = new LocationObject(1, 57.737575, 11.913712, "2");
                MapDatail.put(3, lc23);
                break;

            case 3:

                LocationObject lc31 = new LocationObject(1, 57.729877, 11.956284, "2");
                MapDatail.put(1, lc31);
                LocationObject lc32 = new LocationObject(1, 57.725111, 11.972764, "2");
                MapDatail.put(2, lc32);
                //LocationObject lc33 = new LocationObject(1, 1.2, 1.3, "2");
                //MapDatail.put(3, lc33);
                break;

            case 4:

                LocationObject lc41 = new LocationObject(1, 57.738308, 12.053101, "2");
                MapDatail.put(1, lc41);
                LocationObject lc42 = new LocationObject(1, 57.739041, 12.051728, "2");
                MapDatail.put(2, lc42);
                //LocationObject lc43 = new LocationObject(1, 1.2, 1.3, "2");
                //MapDatail.put(3, lc43);
                break;

            case 5:

                LocationObject lc51 = new LocationObject(1, 57.782630, 11.986497, "2");
                MapDatail.put(1, lc51);
                //LocationObject lc52 = new LocationObject(1, 1.2, 1.3, "2");
                //MapDatail.put(2, lc52);
                //LocationObject lc53 = new LocationObject(1, 1.2, 1.3, "2");
                //MapDatail.put(3, lc53);
                break;

            case 6:

                LocationObject lc61 = new LocationObject(1, 57.730033, 11.958828, "2");
                MapDatail.put(1, lc61);
                LocationObject lc62 = new LocationObject(1, 57.726039, 11.970336, "2");
                MapDatail.put(2, lc62);
                //LocationObject lc63 = new LocationObject(1, 1.2, 1.3, "2");
               // MapDatail.put(3, lc63);
                break;

            case 7:

                LocationObject lc71 = new LocationObject(1, 57.743175, 12.002132, "2");
                MapDatail.put(1, lc71);
                LocationObject lc72 = new LocationObject(1, 57.725486, 11.970121, "2");
                MapDatail.put(2, lc72);
                LocationObject lc73 = new LocationObject(1, 57.728185, 11.954395, "2");
                MapDatail.put(3, lc73);
                break;
        }
    }

}
