package com.recyclerush.group5.recyclerush;

public abstract class User {
    protected String userName;
    protected int score;

    protected User() {
        this.userName = "unknown";
        this.score = 0;
    }

    public User(String userName) {
        this.userName = userName;
        this.score = 0;
    }

    public String getUserName() {
        return this.userName;
    }

    public abstract void setUserName(String userName);

    public int getScore() {
        return this.score;
    }

    public abstract void addScore(int points);
}
