package com.antriansehat.application.model;

import android.media.Image;

public class Article {
    private String id;
    private String title;
    private String content;
    private Image banner;

    public Article(String id, String title, String content, Image banner) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.banner = banner;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Image getBanner() {
        return banner;
    }
}
