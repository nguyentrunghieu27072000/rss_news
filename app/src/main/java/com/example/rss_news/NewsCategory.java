package com.example.rss_news;

public class NewsCategory {
    public String categoryId;
    public String categoryName;

    public String categoryLink;

    public String getCategoryLink() {
        return categoryLink;
    }

    public void setCategoryLink(String categoryLink) {
        this.categoryLink = categoryLink;
    }

    public NewsCategory(String categoryId, String categoryName, String categoryLink) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryLink = categoryLink;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
