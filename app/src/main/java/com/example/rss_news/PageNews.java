package com.example.rss_news;

import android.util.Log;

import java.io.Serializable;

public class PageNews implements Serializable {
    private int id;
    private String pageName;
    private int pageLogo;

    public PageNews(int id, String pageName, int pageLogo) {
        this.id = id;
        this.pageName = pageName;
        this.pageLogo = pageLogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getPageLogo() {
        return pageLogo;
    }

    public void setPageLogo(int pageLogo) {
        this.pageLogo = pageLogo;
    }
}
