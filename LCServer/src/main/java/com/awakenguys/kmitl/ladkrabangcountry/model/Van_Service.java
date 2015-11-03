package com.awakenguys.kmitl.ladkrabangcountry.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Van_Service {
    @Id private  String id;
    private ArrayList<Route> routes;
    private ArrayList<Station> stations;

    public Van_Service() {
        this.routes = new ArrayList<Route>();
        this.stations = new ArrayList<Station>();
    }

    public void add_Route(String src, String des) throws UnsupportedEncodingException{
        routes.add(new Route(src, des));
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

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Van_Service{" +
                "id='" + id + '\'' +
                ", routes=" + routes +
                ", stations=" + stations +
                '}';
    }
}
