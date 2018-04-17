package com.recyclerush.group5.recyclerush;

/**
 * Created by Jeppe on 2018-04-10.
 */

public class ItemObject {
    private String name;
    private String scanId;
    private Boolean recycleable;
    private String materials;

    public ItemObject(String name, String scanId, Boolean recycleAble, String materials){
        this.name = name;
        this.scanId = scanId;
        this.recycleable = recycleAble;
        this.materials = materials;
    }

    public String getName (){
        return this.name;
    }
    public String getScanId(){
        return scanId;
    }
    public Boolean isRecycleable(){
        return recycleable;
    }
    public String getMaterials(){
        return materials;
    }
}