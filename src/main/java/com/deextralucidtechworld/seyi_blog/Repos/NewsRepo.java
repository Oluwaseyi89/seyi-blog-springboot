package com.deextralucidtechworld.seyi_blog.Repos;

import java.util.List;

import com.deextralucidtechworld.seyi_blog.Models.News;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepo extends JpaRepository<News, Integer> {
    @Query("Select news from News news where news.news_title = :news_title")
    News findByNewsTitle(@Param("news_title") String news_title);

    @Query("Select news from News news where news.category = :category")
    List<News> findByCategory(@Param("category") String category);
}
