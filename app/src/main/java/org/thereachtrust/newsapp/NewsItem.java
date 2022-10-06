package org.thereachtrust.newsapp;

public class NewsItem {
    private String titles;
    private String descr;
    private String links;


    public NewsItem(String titles, String descr, String links) {
        this.titles = titles;
        this.descr = descr;
        this.links = links;

    }

    public NewsItem() {
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }


    @Override
    public String toString() {
        return "NewsItem{" +
                "titles='" + titles + '\'' +
                ", descr='" + descr + '\'' +
                ", links='" + links + '\'' +
                '}';
    }
}
