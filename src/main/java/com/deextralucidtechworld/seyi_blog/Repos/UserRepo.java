package com.deextralucidtechworld.seyi_blog.Repos;

import com.deextralucidtechworld.seyi_blog.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
