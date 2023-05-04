package com.example.rss_news;

public class NewsFavorite extends News{
    public String id;
    public NewsFavorite(String id, String title, String link, String img) {
        super(title, link, img);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
