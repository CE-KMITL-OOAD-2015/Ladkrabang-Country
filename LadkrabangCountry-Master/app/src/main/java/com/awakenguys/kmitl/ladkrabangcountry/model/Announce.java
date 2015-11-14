package com.awakenguys.kmitl.ladkrabangcountry.model;



public class Announce {
    private String id;
    private String topic;
    private String content;
    private String img_path;
    private String author;

    public Announce() {
    }

    public Announce(String topic, String content, String author) {
        this.topic = topic;
        this.content = content;
        img_path = null;
        this.author = author;
    }

    public Announce(String author, String topic, String content, String img_path) {
        this.author = author;
        this.topic = topic;
        this.content = content;
        this.img_path = img_path;
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

    @Override
    public String toString() {
        return "Announce{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", img_path='" + img_path + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
