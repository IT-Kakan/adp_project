package com.recyclerush.group5.recyclerush;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Jeppe on 2018-04-19.
 */

public class CurrentUser extends User {
    private static CurrentUser instance;
    private final String TAG = "User";
    private boolean isLoggedIn;

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private CurrentUser() {
        super();
        this.isLoggedIn = false;
    }

    public void logIn() {
        try {
            user = FirebaseAuth.getInstance().getCurrentUser();
            userName = user.getDisplayName();
            database = FirebaseDatabase.getInstance();
            userRef = database.getReference(user.getDisplayName());
            Log.i(TAG, "KEY: " + userRef.getKey());
            fetchUserData();
            isLoggedIn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logOut() {
        isLoggedIn = false;
        userName = "unknown";
        this.score = 0;
    }

    private void fetchUserData() {
        Log.d(TAG, "Trying to add event listener");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                try{
                    score += Integer.parseInt(value);
                } catch (Exception e) {
                    userRef.setValue("" + score);
                }
                Log.d(TAG, "Value is: " + score);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public static CurrentUser getInstance() {
        if(instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void recycle(ItemObject item) {
        if (item != null) {
            addScore(item.getScore());
        }
    }

    public void addScore(int points) throws IllegalArgumentException {
        Log.d(TAG, "Add points: " + points);
        if(points >= 0 && isLoggedIn) {
            this.score += points;
            userRef.setValue("" + this.score);
        } else if(points >= 0) {
            this.score += points;
        } else {
            throw new IllegalArgumentException("Only positive integers");
        }
    }
}
