package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayUser extends AppCompatActivity {


    TextView uText;
    TextView pText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        View userView = this.findViewById(android.R.id.content);


        userView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeLeft () {
                Intent backToMain = new Intent(DisplayUser.this, MainActivity.class);
                //backToMain.putExtra("name", "something");
                setResult(RESULT_OK, backToMain);
                //startActivity(backToMain);
                finish();
            }
        });

    }
}
