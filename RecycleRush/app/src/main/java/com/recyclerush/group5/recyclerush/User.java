package com.recyclerush.group5.recyclerush;

/**
 * Created by Jeppe on 2018-04-19.
 */

public class User {
    private String userName;
    private int score;

    public User(String name){
        this.userName = name;
        this.score = 0;
    }

    public String getUserName(){
        return this.userName;
    }
    public int getScore(){
        return this.score;
    }
    public void recycle(ItemObject item) {
        if (item != null) {
            addScore(item.getScore());
        }
    }
    public void addScore(int score) {
        this.score += score;
    }
}
