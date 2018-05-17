package com.recyclerush.group5.recyclerush;

/**
 * Created by Jeppe on 2018-04-10.
 */

public class ItemObject {
    private String name;
    private String scanId;
    private boolean recyclable;
    private String materials;
    private int score;

    public ItemObject(String scanId, String name, boolean recycleable, String materials){
        this.name = name;
        this.scanId = scanId;
        this.recyclable = recycleable;
        this.materials = materials;
        this.score = 20; //For now, score is independent of item.
    }

    public String getName (){
        return this.name;
    }
    public String getScanId(){
        return scanId;
    }
    public boolean isRecyclable(){
        return recyclable;
    }
    public String getMaterials(){
        return materials;
    }
    public int getScore(){
        return score;
    }

    //checks if scanned item exists
    public static boolean doesExist(String id){
        return (ItemObject.getScannedItem(id) != null);
    }

    public static ItemObject getScannedItem(String id){
        return readFromDB(id);
    }

    private static ItemObject readFromDB(String scanId) {
        // This is a mock database. It simulates data for a few chosen items.
        ItemObject result = null;
        switch (scanId) {
            case "7340131610000": //Redbull barcode
                result = new ItemObject("7340131610000", "Redbull", true, "metal" );
                break;
            case "7311250004360": //Snus barcode
                result = new ItemObject("7311250004360","Snus" , true, "plastic");
                break;
            case "5901234123457": //Tom barcode
                result = new ItemObject("5901234123457","Tom",  true, "paper");
                break;
            case "123": //Test barcode
                result = new ItemObject("5901234123457","Coca Cola",  true, "plutonium");
                break;
            case "9783319051543": //Book
                result = new ItemObject("9783319051543","Book",  true, "paper");
                break;
            default:
                break;

        }
        return result;
    }
}
