package com.awakenguys.kmitl.ladkrabangcountry.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

public class Station {
    @Id
    private String id;
    private String name;
    private Point location;

    public Station(String name, Point location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
