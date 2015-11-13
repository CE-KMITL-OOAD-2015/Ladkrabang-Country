package com.awakenguys.kmitl.ladkrabangcountry.model;


import java.util.Date;

public class Review {

    private String id;
    private String topic;
    private String content;
    private String img_path;
    private String author;
    private float rating = 0;
    private int total = 0;
    private long create_date = new Date().getTime();

    public Review() {
    }

    public Review(String topic, String content, String author) {
        this.topic = topic;
        this.content = content;
        this.author = author;
    }

    public Review(String topic, String content, String img_path, String author) {
        this.topic = topic;
        this.content = content;
        this.img_path = img_path;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", img_path='" + img_path + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", total=" + total +
                ", create_date=" + create_date +
                '}';
    }
}
