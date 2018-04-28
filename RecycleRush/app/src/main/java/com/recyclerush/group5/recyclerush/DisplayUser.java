package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayUser extends AppCompatActivity {


    TextView pointsText;
    TextView usernameText;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        View userView = this.findViewById(android.R.id.content);


        String username = getIntent().getStringExtra("username");
        Integer points = getIntent().getIntExtra("points",-1);

        pointsText= findViewById(R.id.points_profile);
        usernameText= findViewById(R.id.username_profile);

        usernameText.setText(username);

        image = findViewById(R.id.imageView2);

        if (points < 100) {
            pointsText.setText(points.toString() + "/100");
            image.setImageResource(R.drawable.first_stage);
        } else if (points < 200) {
            pointsText.setText(points.toString() + "/200");
            image.setImageResource(R.drawable.second_stage);
        } else if (points < 300) {
            pointsText.setText(points.toString() + "/300");
            image.setImageResource(R.drawable.third_stage);
        } else if (points < 400) {
            pointsText.setText(points.toString() + "/400");
            image.setImageResource(R.drawable.fourth_stage);
        } else {
            pointsText.setText(points.toString());
            image.setImageResource(R.drawable.full_oak);
        }

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
