package com.deextralucidtechworld.seyi_blog.Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;

import com.deextralucidtechworld.seyi_blog.Models.Author;
import com.deextralucidtechworld.seyi_blog.Models.News;
import com.deextralucidtechworld.seyi_blog.Repos.AuthorRepo;
import com.deextralucidtechworld.seyi_blog.Repos.NewsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {

    @Autowired(required = true)
    private StorageService storageService;
    @Autowired(required = true)
    private AuthorRepo authorRepo;

    @Autowired(required = true)
    private NewsRepo newsRepo;

    @GetMapping(path = "/")
    @ResponseBody
    public ModelAndView getHome() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("author");
        return mv;
    }

    @PostMapping(value = "/save_news", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Map<String, ?> saveNews(News news, @RequestParam("newsVideo") MultipartFile newsVideo,
            @RequestParam("newsPoster") MultipartFile newsPoster) {

        News soughtNews = newsRepo.findByNewsTitle(news.getNewsTitle());
        if (soughtNews != null) {
            Map<String, String> newsExists = new HashMap<String, String>();
            String resMessage = "News with title: " + "\"" + news.getNewsTitle() + "\"" + " already exists";
            newsExists.put("message", resMessage);
            newsExists.put("status", "error");
            return newsExists;
        } else {
            try {
                storageService.save(newsPoster);
                storageService.saveVideo(newsVideo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            newsRepo.save(news);
            Map<String, String> newsSaved = new HashMap<String, String>();
            newsSaved.put("message", "News successfully posted");
            newsSaved.put("status", "success");
            return newsSaved;
        }
    }

    @PostMapping(value = "/save_author", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public Map<String, String> saveAuthor(Author author, @RequestParam("authorPix") MultipartFile authorPix)
            throws IOException {

        Map<String, String> susRes = new HashMap<>();
        Map<String, String> failRes = new HashMap<>();
            Map<String, String> newsExists = new HashMap<>();
                    Map<String, String> newsSaved = new HashMap<>();
                        // TODO: Replace with proper logging
            Map<String, String> nonUser = new HashMap<>();
            Map<String, String> wrongPassword = new HashMap<>();
            Map<String, Object> loginSuccess = new HashMap<>();
            Map<String, String> authorObj = new HashMap<>();
                        // TODO: Replace with proper logging
        susRes.put("message", "Author successfully saved");
        susRes.put("status", "success");
        failRes.put("message", "Author already exists");
        failRes.put("status", "error");
        Author checkedAuthor = authorRepo.findByAuthorEmail(author.getAuthorEmail());
        if (checkedAuthor != null) {
            return failRes;
        } else {
            try {
                storageService.save(authorPix);
            } catch (Exception e) {
                e.printStackTrace();
            }
            authorRepo.save(author);
            System.out.println(susRes.toString());
            return susRes;
        }
    }

    @PostMapping(value = "login_author", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Map<String, ?> loginAuthor(@RequestBody Author author) {

        Author soughtUser = authorRepo.findByUserName(author.getUserName());

        Map<String, String> nonUser = new HashMap<String, String>();
        nonUser.put("message", "User does not exist");
        nonUser.put("status", "error");

        Map<String, String> wrongPassword = new HashMap<String, String>();
        wrongPassword.put("message", "Password is wrong");
        wrongPassword.put("status", "error");

        Map<String, Object> loginSuccess = new HashMap<String, Object>();
        loginSuccess.put("message", "Login Successful");
        loginSuccess.put("status", "success");

        Map<String, String> authorObj = new HashMap<String, String>();

        if (soughtUser == null) {
            return nonUser;
        }

        if (!soughtUser.getPassword().equals(author.getPassword())) {
            return wrongPassword;

        } else {
            authorObj.put("author_name", soughtUser.getAuthorName());
            authorObj.put("author_pix", soughtUser.getAuthorPix());
            authorObj.put("user_name", soughtUser.getUserName());
            loginSuccess.put("author", authorObj);
            return loginSuccess;
        }
    }

    @GetMapping(value = "/logged_author/{author}/{password}")
    public ModelAndView getLoggedInAuthor(@PathVariable("author") String author,
            @PathVariable("password") String password) {

        Author allowedAuthor = authorRepo.findByUserName(author);
        String[] sampString = password.split("\\*");
        String[] reversedPassword = arrayReverse(sampString);
        String usablePassword = buildString(reversedPassword);
        System.out.println(usablePassword);

        if (allowedAuthor != null && allowedAuthor.getPassword().equals(usablePassword)) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("author_dashboard");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("404");
            return mv;
        }

    }

    @GetMapping(value = "/log_author")
    public ModelAndView getLoggedInAuthorPost(Author author) {

        Author allowedAuthor = authorRepo.findByUserName(author.getUserName());

        if (allowedAuthor != null && allowedAuthor.getPassword().equals(author.getPassword())) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("author_dashboard");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("404");
            return mv;
        }

    }

    @GetMapping(value = "/testreverse/{samparr}")
    @ResponseBody
    public String testReverse(@PathVariable("samparr") String samparr) {
        String[] reversedArr = arrayReverse(samparr.split(""));
        StringBuilder joinedString = new StringBuilder();

        for (String chr : reversedArr) {
            joinedString.append(chr);
        }

        String result = joinedString.toString();
        return result;
    }

    String buildString(String[] arrStr) {
        StringBuilder joinedString = new StringBuilder();

        for (String chr : arrStr) {
            joinedString.append(chr);
        }

        String result = joinedString.toString();
        return result;
    }

    @GetMapping(value = "/author_reg_page")
    public ModelAndView getAuthorRegPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("registerAuthor");
        return mv;
    }

    @GetMapping(value = "/uploads/{file}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getPhoto(HttpServletResponse response, @PathVariable("file") String file) throws IOException {
        ClassPathResource photoFile = new ClassPathResource("uploads/" + file + ".jpg");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(photoFile.getInputStream(), response.getOutputStream());
    }

    @GetMapping(value = "/videos/{file}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getVideo(HttpServletResponse response, @PathVariable("file") String file) throws IOException {
        ClassPathResource videoFile = new ClassPathResource("videos/" + file + ".mp4");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        StreamUtils.copy(videoFile.getInputStream(), response.getOutputStream());
    }

    String[] arrayReverse(String[] sampArr) {
        String[] newArr = new String[sampArr.length];
        int n = sampArr.length;

        int j = n;

        for (int i = 0; i < n; i++) {
            newArr[j - 1] = sampArr[i];
            j = j - 1;
        }

        return newArr;
    }
}
