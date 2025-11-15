package com.deextralucidtechworld.seyi_blog.Repos;

import com.deextralucidtechworld.seyi_blog.Models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
