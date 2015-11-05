package com.awakenguys.kmitl.ladkrabangcountry.model;

import org.springframework.data.annotation.Id;


public class Place {
    @Id private String id;


    private String name;
    private String category;
    private float lat;
    private float lng;

    public Place() {
    }

    public Place(String name, String category, float lat, float lng) {
        this.name = name;
        this.category = category;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCategory(String cat){
        category=cat;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}