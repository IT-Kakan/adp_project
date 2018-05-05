package com.recyclerush.group5.recyclerush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreboardActivity extends AppCompatActivity {
    ArrayList<User> userList;
    private static ScoreboardAdapter adapter;
    ListView scoreboard;
    //TODO: find all users; sort by score; populate scoreboard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);


        scoreboard = this.findViewById(R.id.ListViewScoreboard);
        userList = getDummyUserList();
        //sort userList by score (descending)
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2){
                return  Integer.compare(user2.getScore(),user1.getScore());
            }
        });

        adapter = new ScoreboardAdapter(userList, getApplicationContext());
        scoreboard.setAdapter(adapter);
    }

    // Caution: This does not mean that the users are dummies!
    ArrayList<User> getDummyUserList(){
        ArrayList<User> tempUL = new ArrayList<User>();
        User magnus = new User("Magnus");
        magnus.addScore(5);
        tempUL.add(magnus);
        User tom = new User("Tom");
        tom.addScore(1);
        tempUL.add(tom);
        User terese = new User("Terese");
        terese.addScore(3);
        tempUL.add(terese);
        return tempUL;
    }

}
