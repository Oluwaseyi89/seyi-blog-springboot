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
@Table(name = "authors")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String author_name;
    String author_display_name;
    String author_email;
    boolean author_account_status;
    String author_pix;
    String user_name;
    String password;

    public Author() {
    }

    public String getAuthorName() {
        return author_name;
    }

    public void setAuthorName(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthorDisplayName() {
        return author_display_name;
    }

    public void setAuthorDisplayName(String author_display_name) {
        this.author_display_name = author_display_name;
    }

    public String getAuthorEmail() {
        return author_email;
    }

    public void setAuthorEmail(String author_email) {
        this.author_email = author_email;
    }

    public boolean getAuthorAccountStatus() {
        return author_account_status;
    }

    public void setAuthorAccountStatus(boolean author_account_status) {
        this.author_account_status = author_account_status;
    }

    public String getAuthorPix() {
        return author_pix;
    }

    public void setAuthorPix(MultipartFile author_pix) {
        this.author_pix = author_pix.getOriginalFilename();
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
