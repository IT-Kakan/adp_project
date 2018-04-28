package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayUser extends AppCompatActivity {


    TextView pointsText;
    TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        View userView = this.findViewById(android.R.id.content);


        String username = getIntent().getStringExtra("username");
        Integer points = getIntent().getIntExtra("points",-1);

        pointsText= findViewById(R.id.points_profile);
        usernameText= findViewById(R.id.username_profile);

        pointsText.setText(points.toString());
        usernameText.setText(username);


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
