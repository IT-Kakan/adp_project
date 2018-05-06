package com.recyclerush.group5.recyclerush;

/**
 * Created by mahshid on 5/3/18.
 */

public class LocationObject {
    int ID;
    Double Lat;
    Double Lng;
    String Description;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLng() {
        return Lng;
    }

    public void setLng(Double lng) {
        Lng = lng;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocationObject(int ID,Double Lat,Double Lng,String Description){
        this.ID = ID;
        this.Lat = Lat;
        this.Lng = Lng;
        this.Description = Description;
    }
}
