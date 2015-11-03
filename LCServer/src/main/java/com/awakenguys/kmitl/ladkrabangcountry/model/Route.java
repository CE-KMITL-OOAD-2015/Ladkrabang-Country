package com.awakenguys.kmitl.ladkrabangcountry.model;

import org.springframework.data.annotation.Id;

import java.io.UnsupportedEncodingException;


public class Route {
    @Id
    private String id;
    private String src;
    private String des;

    public Route(String src, String des) throws UnsupportedEncodingException {
        this.src = src;
        this.des = des;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSrc() {
        return src;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        return "Van_Route{" +
                "id='" + id + '\'' +
                ", src='" + src + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}