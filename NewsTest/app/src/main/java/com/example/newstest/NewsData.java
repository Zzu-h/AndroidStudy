package com.example.newstest;

import java.io.Serializable;

public class NewsData implements Serializable {
    private String title, urlToImage, content, link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String fiexed_title = title.replaceAll("<b>","");
        fiexed_title = fiexed_title.replaceAll("</b>","");
        this.title = fiexed_title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        String fiexed_content = content.replaceAll("<b>","");
        fiexed_content = fiexed_content.replaceAll("</b>","");
        this.content = fiexed_content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
