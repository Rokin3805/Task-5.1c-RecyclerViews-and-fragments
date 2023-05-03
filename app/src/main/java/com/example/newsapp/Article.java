package com.example.newsapp;

public class Article {
    private String articleHeading;
    private String articleBody;
    private int imageResourceId;
    private int id;

    public Article(String articleHeading, String articleBody, int imageResourceId, int id) {
        this.articleHeading = articleHeading;
        this.articleBody = articleBody;
        this.imageResourceId = imageResourceId;
        this.id = id;
    }

    public String getArticleHeading() {
        return articleHeading;
    }

    public void setArticleHeading(String articleHeading) {
        this.articleHeading = articleHeading;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
