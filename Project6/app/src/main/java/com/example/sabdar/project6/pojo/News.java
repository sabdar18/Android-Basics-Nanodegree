package com.example.sabdar.project6.pojo;

public class News {
    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mPublishDate;
    private String mWebUrl;

    public News(String title, String section, String author, String publishDate, String webUrl) {
        this.mTitle = title;
        this.mSection = section;
        this.mAuthor = author;
        this.mPublishDate = publishDate;
        this.mWebUrl = webUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public String getWebUrl() {
        return mWebUrl;
    }
}
