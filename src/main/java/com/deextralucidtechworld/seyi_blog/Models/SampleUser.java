package com.deextralucidtechworld.seyi_blog.Models;

/**
 * SampleUser was an experiment and is no longer a JPA entity.
 * This plain POJO remains to avoid breaking references during the transition.
 * It is intentionally not annotated with JPA annotations so it will not
 * be mapped to a database table.
 */
public class SampleUser {
    private int id;
    private String first_name;
    private String last_name;
    private String photo;
    private String video;
    private String phone;
    private String email;
    private String password;

    public SampleUser() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return first_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }
    public String getLastName() { return last_name; }
    public void setLastName(String last_name) { this.last_name = last_name; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
    public String getVideo() { return video; }
    public void setVideo(String video) { this.video = video; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
