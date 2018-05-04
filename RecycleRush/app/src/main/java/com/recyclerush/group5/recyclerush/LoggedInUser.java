package com.recyclerush.group5.recyclerush;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoggedInUser {
    private static LoggedInUser instance;
    private String userName;
    private int points;
    private final String TAG = "UserClass";

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private LoggedInUser() {
        points = 0;
        try {
            logIn();
        } catch (Exception e) {
            e.printStackTrace();
            userName = "not logged in";
        }
    }

    public void logIn() {
        try {
            user = FirebaseAuth.getInstance().getCurrentUser();
            userName = user.getDisplayName();
            database = FirebaseDatabase.getInstance();
            userRef = database.getReference(user.getDisplayName());
            Log.i(TAG, "KEY: " + userRef.getKey());
            fetchUserData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchUserData() {
        Log.d(TAG, "Trying to add event listener");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                points = Integer.parseInt(value);
                Log.d(TAG, "Value is: " + points);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public static LoggedInUser getInstance() {
        if(instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    public String getUser(){
        return this.userName;
    }

    public int getPoints(){
        return this.points;
    }

    public int addPoints(int points) throws IllegalArgumentException {
        Log.d(TAG, "Add points: " + points);
        if(points > 0) {
            this.points += points;
            userRef.setValue("" + this.points);
            return getPoints();
        } else {
            throw new IllegalArgumentException("Only positive integers");
        }
    }
}
