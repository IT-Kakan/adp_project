package com.recyclerush.group5.recyclerush;

/**
 * Created by Jeppe on 2018-04-19.
 */

public class userClass {
    String userName;
    Integer points;

    public userClass(String name){
        this.userName = name;
        this.points = 0;
    }

    public String getUser(){
        return this.userName;
    }
    public Integer getPoints(){
        return this.points;
    }
    public void addScore(int score) {
        this.points += score;
    }

}
