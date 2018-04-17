package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by emillundgren on 2018-04-12.
 */

public class SecondActivity extends Activity{
    TextView text1;
    TextView text2;
    TextView text3;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        text1=(TextView) findViewById(R.id.textView);
        text2=(TextView) findViewById(R.id.textView2);
        text3=(TextView) findViewById(R.id.textView3);

        text1.setText(getIntent().getExtras().getString("name"));
        text2.setText(getIntent().getExtras().getString("materials"));

        layout = (ConstraintLayout) findViewById(R.id.layout);
        Snackbar snack = Snackbar.make(layout, getIntent().getExtras().getString("recyc"), Snackbar.LENGTH_INDEFINITE);
        // View snackView = snack.getView();
        // TextView textView = snackView.findViewById(android.support.design.R.id.snackbar_text);
        // textView.setBackgroundColor(0xFF009446);

        snack.show();

        text3.setText("Material:");
    }
}
