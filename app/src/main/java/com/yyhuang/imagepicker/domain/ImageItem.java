package com.yyhuang.imagepicker.domain;

public class ImageItem {
    private long date;
    private String title;
    private String path;

    @Override
    public String toString() {
        return "ImageItem{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public ImageItem() {
    }

    public ImageItem(long date, String title, String path) {
        this.date = date;
        this.title = title;
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

