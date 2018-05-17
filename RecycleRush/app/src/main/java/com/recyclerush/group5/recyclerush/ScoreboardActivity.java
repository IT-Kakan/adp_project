package com.recyclerush.group5.recyclerush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {
    private static final String TAG = "ScoreboardActivity";
    ArrayList<User> userList;
    private static ScoreboardAdapter adapter;
    ListView scoreboard;
    CurrentUser currentUser = CurrentUser.getInstance();

    //TODO: find all users; sort by score; populate scoreboard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        scoreboard = this.findViewById(R.id.ListViewScoreboard);
        initScoreboard();
    }

    private void initScoreboard() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("users");
        Query query = database.getReference("users").orderByChild("score");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                handleDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void handleDataChange(DataSnapshot dataSnapshot) {
        List<User> userList = new LinkedList<>();
        for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
            // the try/catch should filter out users who are registered in the database
            // but haven't initialized a score (extremely rare, but it could happen)
            try {
                if (userSnapshot.getKey().equals(CurrentUser.getInstance().getuId())) {
                    userList.add(CurrentUser.getInstance());
                } else {
                    User user = new OtherUser(userSnapshot.child("username").getValue(String.class));
                    Log.i(TAG, user.getUserName());
                    user.addScore(userSnapshot.child("score").getValue(Integer.class));
                    userList.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Query...orderByChildren(score) sorts users with lower scores first
        Collections.reverse(userList);
        User first = userList.get(0);

        TextView winnerName = findViewById(R.id.winner);
        TextView winnerScore = findViewById(R.id.winner_points);

        TextView currUserName = findViewById(R.id.nameuser);
        TextView currUserPoints = findViewById(R.id.pointsuser);
        TextView currUserPlace = findViewById(R.id.place);

        currUserName.setText(currentUser.getUserName());
        currUserPoints.setText(currentUser.getScore() + "");

        winnerName.setText(first.getUserName());
        winnerScore.setText(first.getScore() + "");

        adapter = new ScoreboardAdapter(userList, getApplicationContext());
        scoreboard.setAdapter(adapter);
    }
}
