package com.deextralucidtechworld.seyi_blog.Repos;

import com.deextralucidtechworld.seyi_blog.Models.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface AuthorRepo extends JpaRepository<Author, Integer> {

    @Query("Select author from Author author where author.author_email = :email")
    Author findByAuthorEmail(@Param("email") String email);

    @Query("Select author from Author author where author.user_name = :username")
    Author findByUserName(@Param("username") String username);

}
