package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.w3c.dom.Text;

public class DisplayUser extends AppCompatActivity {

    private LoggedInUser user;
    private final String TAG = "DisplayUser";
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    TextView uText;
    TextView pText;
    Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = LoggedInUser.getInstance();
        setContentView(R.layout.activity_display_user);
        uText = findViewById(R.id.username_profile);
        pText = findViewById(R.id.points_profile);

        uText.setText(user.getUser());
        pText.setText("loading...");

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(user.getUser());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pText.setText("" + user.getPoints());
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }

    public void logOut(View v) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(DisplayUser.this, UserActivity.class));
                        finish();
                    }
                });
    }
}
