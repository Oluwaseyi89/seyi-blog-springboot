package com.deextralucidtechworld.seyi_blog.Models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Component
@Table(name = "news")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String category;
    String news_title;
    String news_poster;
    String news_video;
    String news_content;
    String date_posted;
    String date_updated;
    String author_name;
    boolean comment_status;
    boolean is_liked;
    boolean is_disliked;

    public News() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNewsTitle() {
        return news_title;
    }

    public void setNewsTitle(String news_title) {
        this.news_title = news_title;
    }

    public String getNewsPoster() {
        return news_poster;
    }

    public void setNewsPoster(MultipartFile news_poster) {
        this.news_poster = news_poster.getOriginalFilename();
    }

    public String getNewsVideo() {
        return news_video;
    }

    public void setNewsVideo(MultipartFile news_video) {
        this.news_video = news_video.getOriginalFilename();
    }

    public String getNewsContent() {
        return news_content;
    }

    public void setNewsContent(String news_content) {
        this.news_content = news_content;
    }

    public String getDatePosted() {
        return date_posted;
    }

    public void setDatePosted(String date_posted) {
        this.date_posted = date_posted;
    }

    public String getDateUpdated() {
        return date_updated;
    }

    public void setDateUpdated(String date_updated) {
        this.date_updated = date_updated;
    }

    public String getAuthorName() {
        return author_name;
    }

    public void setAuthorName(String author_name) {
        this.author_name = author_name;
    }

    public boolean getCommentStatus() {
        return comment_status;
    }

    public void setCommentStatus(boolean comment_status) {
        this.comment_status = comment_status;
    }

    public boolean getIsLiked() {
        return is_liked;
    }

    public void setIsLiked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public boolean getIsDisliked() {
        return is_disliked;
    }

    public void setIsDisliked(boolean is_disliked) {
        this.is_disliked = is_disliked;
    }

}
