package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by mahshid on 4/26/18.
 */

public class Categories extends Activity{

    String items[] = new String []{"Glass", "Metal","Paper", "Plastic", "Hazardous","Electronics"};

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toast.makeText(getApplicationContext(), "Swiped Left", Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        ListView listView= findViewById(R.id.main_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Categories.this,items[position], Toast.LENGTH_SHORT ).show();
                Intent i= new Intent(Categories.this, Location.class );
                i.putExtra("ID_EXTRA",String.valueOf(id));
                startActivity(i);
            }
        });


    }
}
