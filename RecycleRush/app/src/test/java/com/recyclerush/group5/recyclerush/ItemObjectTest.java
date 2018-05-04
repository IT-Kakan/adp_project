package com.recyclerush.group5.recyclerush;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemObjectTest {

    @Test
    public void getScannedItem() {
        // The customer has so far provided test cases for these products.
        verifyItem("7340131610000", "Redbull", true, "metal");
        verifyItem("7311250004360","Snus", true, "plastic, paper");
    }

    public void verifyItem(String scanId, String name, boolean recycleable, String materials) {
        ItemObject item = ItemObject.getScannedItem(scanId);
        assertEquals(name, item.getName());
        assertEquals(recycleable, item.isRecyclable());
        assertEquals(materials, item.getMaterials());
    }

}