package com.clj.csdn.bean;

/**
 * 新闻详情
 * Created by clj on 2015/10/29.
 */
public class NewsDetail {
    private String title;
    private String infor;
    private String texts;
    private String commentsLink;

    public String getCommentsLink() {
        return commentsLink;
    }

    public void setCommentsLink(String commentsLink) {
        this.commentsLink = commentsLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }


}
