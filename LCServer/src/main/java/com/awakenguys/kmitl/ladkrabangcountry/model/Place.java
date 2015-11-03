package com.awakenguys.kmitl.ladkrabangcountry.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;


import java.io.UnsupportedEncodingException;

public class Place {
    @Id private String id;

    private String name;
    private String category;

    private Point location;


    public Place( String name, String category, Point location) throws UnsupportedEncodingException {
        this.name = name;
        this.category = category;
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
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
                ", location=" + location +
                '}';
    }
}