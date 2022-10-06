package org.thereachtrust.newsapp;

public class NewsItem {
    private String title;
    private String desc;
    private String link;
    private String date;

    public NewsItem(String title, String desc, String link, String date) {
        this.title = title;
        this.desc = desc;
        this.link = link;
        this.date = date;

    }

    public NewsItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", link='" + link + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
