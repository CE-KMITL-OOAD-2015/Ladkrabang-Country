package com.awakenguys.kmitl.ladkrabangcountry.model;

import java.io.UnsupportedEncodingException;


public class Van_Route {
    private String src;
    private String des;

    public Van_Route() {
    }

    public Van_Route(String src, String des) throws UnsupportedEncodingException {
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
                ", src='" + src + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}