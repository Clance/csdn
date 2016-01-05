package com.clj.csdn.bean;

import java.io.Serializable;

/**
 * 新闻实体类
 * Created by Administrator on 2015/10/23.
 */
public class News implements Serializable {
    private int id;
    /**
     * 图片的链接
     */
    private String imgLink;
    /**
     * 内容
     */
    private String content;
    /**
     * 标题
     */
    private String title;
    /**
     * 链接
     */
    private String link;
    /**
     * 发布日期
     */
    private String ago;
    /**
     * 阅读
     */
    private String view_time;
    /**
     * 评论
     */
    private String num_recom;
    private String author_time;

    public String getAuthor_time() {
        return author_time;
    }

    public void setAuthor_time(String author_time) {
        this.author_time = author_time;
    }

    public String getAgo() {
        return ago;
    }

    public void setAgo(String ago) {
        this.ago = ago;
    }

    public String getView_time() {
        return view_time;
    }

    public void setView_time(String view_time) {
        this.view_time = view_time;
    }

    public String getNum_recom() {
        return num_recom;
    }

    public void setNum_recom(String num_recom) {
        this.num_recom = num_recom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return ago;
    }

    public void setDate(String date) {
        this.ago = date;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
