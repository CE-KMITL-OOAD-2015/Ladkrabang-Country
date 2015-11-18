package com.awakenguys.kmitl.ladkrabangcountry.model;



import java.util.Date;
import java.util.HashMap;

public class Review {

    private String id;
    private String topic;
    private String content;
    private String img_path;
    private String author;
    private String authorId;
    private float rating = 0;
    private long create_date = new Date().getTime();
    private HashMap<String, Integer> ratings = new HashMap<>();

    public Review() {
    }

    public Review(String topic, String content, String authorId, String author) {
        this.topic = topic;
        this.content = content;
        this.authorId = authorId;
        this.author = author;
    }

    public Review(String topic, String content, String img_path, String authorId, String author) {
        this.topic = topic;
        this.content = content;
        this.img_path = img_path;
        this.authorId = authorId;
        this.author = author;
    }


    public Review(String id, String topic, String content, String img_path, String author, String authorId, float rating, long create_date, HashMap<String, Integer> ratings) {
        this.id = id;
        this.topic = topic;
        this.content = content;
        this.img_path = img_path;
        this.author = author;
        this.authorId = authorId;
        this.rating = rating;
        this.create_date = create_date;
        this.ratings = ratings;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public HashMap<String, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(HashMap<String, Integer> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", img_path='" + img_path + '\'' +
                ", author='" + author + '\'' +
                ", authorId='" + authorId + '\'' +
                ", rating=" + rating +
                ", create_date=" + create_date +
                ", ratings=" + ratings +
                '}';
    }
}
