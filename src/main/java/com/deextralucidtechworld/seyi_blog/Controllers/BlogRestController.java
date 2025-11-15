package com.deextralucidtechworld.seyi_blog.Controllers;

import java.util.List;

import com.deextralucidtechworld.seyi_blog.Models.News;
import com.deextralucidtechworld.seyi_blog.Repos.NewsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class BlogRestController {

    @Autowired(required = true)
    NewsRepo newsRepo;

    @GetMapping("/all_news")
    public List<News> getNews() {
        List<News> allNews = newsRepo.findAll();
        return allNews;
    }

    @GetMapping("/news_category/{category}")
    public List<News> getNewsByCategory(@PathVariable("category") String category) {
        String correctCategory = "";
        if (category.contains("poli")) {
            correctCategory = "Politics";
        } else if (category.contains("spo")) {
            correctCategory = "Sports";
        } else if (category.contains("cele")) {
            correctCategory = "Celebrities";
        } else if (category.contains("fas")) {
            correctCategory = "Fashion";
        } else {
            correctCategory = "";
        }

        List<News> categoricalNews;
        if (!correctCategory.equals("")) {
            categoricalNews = newsRepo.findByCategory(correctCategory);
        } else {
            categoricalNews = newsRepo.findAll();
        }
        return categoricalNews;
    }

    @GetMapping("/news_title/{title}")
    public News getNewsByTitle(@PathVariable("title") String title) {
        News soughtNews = newsRepo.findByNewsTitle(title);
        return soughtNews;
    }
}
