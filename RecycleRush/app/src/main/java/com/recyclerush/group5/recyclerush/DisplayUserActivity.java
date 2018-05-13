package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayUserActivity extends AppCompatActivity {

    TextView pointsText;
    TextView usernameText;
    ImageView image;
    private CurrentUser user;
    Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = CurrentUser.getInstance();
        setContentView(R.layout.activity_display_user);
        View userView = this.findViewById(android.R.id.content);
        pointsText = findViewById(R.id.points_profile);
        pointsText.setText("0/100");
        usernameText = findViewById(R.id.username_profile);
        usernameText.setText(user.getUserName());
        image = findViewById(R.id.imageView2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users").child(user.getuId());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int points = user.getScore();
                if (points < 100) {
                    pointsText.setText(points + "/100");
                    image.setImageResource(R.drawable.first_stage);
                } else if (points < 200) {
                    pointsText.setText(points + "/200");
                    image.setImageResource(R.drawable.second_stage);
                } else if (points < 300) {
                    pointsText.setText(points + "/300");
                    image.setImageResource(R.drawable.third_stage);
                } else if (points < 400) {
                    pointsText.setText(points + "/400");
                    image.setImageResource(R.drawable.fourth_stage);
                } else {
                    pointsText.setText(points);
                    image.setImageResource(R.drawable.full_oak);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });

        userView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeLeft () {
                Intent backToMain = new Intent(DisplayUserActivity.this, MainActivity.class);
                backToMain.putExtra("user", user.getUserName());
                startActivity(backToMain);
            }
        });

        pointsText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showScoreboard();
            }
        });
    }

    void showScoreboard(){
        Intent displayInfo = new Intent(this, ScoreboardActivity.class);
        startActivity(displayInfo);
    }

    public void logOut(View v) {
        CurrentUser.getInstance().logOut();
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(DisplayUserActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}
