package com.recyclerush.group5.recyclerush;

public class OtherUser extends User {

    public OtherUser(String userName) {
        super(userName);
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void addScore(int points) {
        this.score += points;
    }
}
