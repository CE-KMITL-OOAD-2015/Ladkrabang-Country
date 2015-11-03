package com.awakenguys.kmitl.ladkrabangcountry.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Train_Service {
    @Id private  String id;
    private ArrayList<Station> stations;

    public Train_Service() {
        this.stations = new ArrayList<Station>();
    }

    public void add_Station(String name, Point location) throws UnsupportedEncodingException{
        stations.add(new Station(name, location));
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Train_Service{" +
                "id='" + id + '\'' +
                ", stations=" + stations +
                '}';
    }
}
