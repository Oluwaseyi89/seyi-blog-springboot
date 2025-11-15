package com.deextralucidtechworld.seyi_blog.Models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String comment_content;
    int news_id;
    String comment_date;
    int user_id;
    boolean comment_status;

    public Comment() {
    }

    public String getCommentContent() {
        return comment_content;
    }

    public void setCommentContent(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getNewsId() {
        return news_id;
    }

    public void setNewsId(int news_id) {
        this.news_id = news_id;
    }

    public String getCommentDate() {
        return comment_date;
    }

    public void setCommentDate(String comment_date) {
        this.comment_date = comment_date;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public boolean getCommentStatus() {
        return comment_status;
    }

    public void setCommentStatus(boolean comment_status) {
        this.comment_status = comment_status;
    }

}
