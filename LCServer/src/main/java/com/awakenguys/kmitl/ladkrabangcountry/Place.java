package com.awakenguys.kmitl.ladkrabangcountry;

import org.springframework.data.annotation.Id;

import java.io.UnsupportedEncodingException;

public class Place {
    @Id private String id;

    private String name;
    private String category;
    private float lat;
    private float lng;

    public Place( String name, String category, float lat, float lng) throws UnsupportedEncodingException {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.category = category;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCategory(String cat){
        category=cat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }
}