package com.recyclerush.group5.recyclerush;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void recycle() {
        //Set up fresh user
        User user = new User("Tester");
        int unmodifiedScore = user.getScore();

        //Non-existing items should not increase score
        user.recycle(null);
        assertEquals(unmodifiedScore, user.getScore());

        //Use the standard test item
        String testItemId = "5901234123457";
        ItemObject testItem = ItemObject.getScannedItem(testItemId);
        user.recycle(testItem);

        assertEquals(unmodifiedScore + testItem.getScore(), user.getScore());
    }
}