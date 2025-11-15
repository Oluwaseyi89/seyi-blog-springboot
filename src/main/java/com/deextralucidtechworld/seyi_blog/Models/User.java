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
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String user_display_name;
    String user_complete_name;
    String user_name;
    String password;
    String user_pix;
    boolean user_status;

    public User() {
    }

    public String getUserDisplayName() {
        return user_display_name;
    }

    public void setUserDisplayName(String user_display_name) {
        this.user_display_name = user_display_name;
    }

    public String getUserCompleteName() {
        return user_complete_name;
    }

    public void setUserCompleteName(String user_complete_name) {
        this.user_complete_name = user_complete_name;
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

    public String getUserPix() {
        return user_pix;
    }

    public void setUserPix(MultipartFile user_pix) {
        this.user_pix = user_pix.getOriginalFilename();
    }

    public boolean getUserStatus() {
        return user_status;
    }

    public void setUserStatus(boolean user_status) {
        this.user_status = user_status;
    }

}
