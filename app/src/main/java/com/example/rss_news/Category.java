package com.example.rss_news;

public class Category {
    private String title;
    private int imgCategory;
    private String url;

    public Category(String title, int imgCategory, String url) {
        this.title = title;
        this.imgCategory = imgCategory;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgCategory() {
        return imgCategory;
    }

    public void setImgCategory(int imgCategory) {
        this.imgCategory = imgCategory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
