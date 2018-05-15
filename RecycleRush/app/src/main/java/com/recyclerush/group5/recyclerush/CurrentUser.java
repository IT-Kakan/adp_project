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
    private final String TAG = "CurrentUser";
    private boolean isLoggedIn;
    private String uId;
    private boolean dbScoreFetched;
    private DatabaseReference userRef;
    private DatabaseReference scoreRef;

    private CurrentUser() {
        super();
        this.isLoggedIn = false;
        this.dbScoreFetched = false;
    }

    public void logIn() {
        try {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            try{
                userName = user.getDisplayName();
                uId = user.getUid();
                userRef = database.getReference("users").child(uId);
                userRef.child("username").setValue(userName);
                scoreRef = userRef.child("score");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            addListener();
            isLoggedIn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logOut() {
        isLoggedIn = false;
        dbScoreFetched = false;
        userName = "unknown";
        this.score = 0;
    }

    private void addListener() {
        scoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                handleDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void handleDataChange(DataSnapshot dataSnapshot) {
        Long value =  dataSnapshot.getValue(Long.class);
        try{
            if(!dbScoreFetched) {
                dbScoreFetched = true;
                score += value;
                scoreRef.setValue(score);
            }
        } catch (Exception e) {
            scoreRef.setValue(score);
        }
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
            Log.i(TAG, "Writing " + score + "to database for user with key " + userRef.getKey());
            scoreRef.setValue(this.score);
        } else if(points >= 0) {
            this.score += points;
        } else {
            throw new IllegalArgumentException("Only positive integers");
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getuId() {
        return uId;
    }
}
