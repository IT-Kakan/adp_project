package com.recyclerush.group5.recyclerush;

import java.util.HashMap;

/**
 * Created by Jeppe on 2018-04-10.
 */

class ItemObject {
    String name;
    String scanId;
    boolean recycleable;
    String materials;
    int score;
    private static HashMap<String, ItemObject> map = new HashMap<String, ItemObject>();

    public ItemObject(String name, String scanId, boolean recycleable, String materials){
        this.name = name;
        this.scanId = scanId;
        this.recycleable = recycleable;
        this.materials = materials;
        this.score = 1;
    }

    public String getName (){
        return this.name;
    }
    public String getScanId(){
        return scanId;
    }
    public boolean isRecycleable(){
        return recycleable;
    }
    public String getMaterials(){
        return materials;
    }
    public int getScore(){
        return score;
    }

    public static void initDummyObjects(){
        ItemObject redbull = new ItemObject("Redbull","7340131610000", true, "metal" );
        map.put(redbull.getScanId(), redbull);
        ItemObject snus = new ItemObject("Snus", "7311250004360", true, "plastic, paper");
        map.put(snus.getScanId(), snus);
        ItemObject tom = new ItemObject("Tom", "5901234123457", true, "paper");
        map.put(tom.getScanId(), tom);
    }

    public static ItemObject getScannedItem(String id){
        return map.get(id);
    }
}
