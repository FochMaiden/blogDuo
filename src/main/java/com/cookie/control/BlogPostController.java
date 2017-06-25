package com.cookie.control;

import com.cookie.model.BlogPost;
import com.cookie.service.BlogPostService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by FochMaiden
 */
@Controller
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;


    @GetMapping(value = "admin/blogPosts")
    public ModelAndView posts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogPosts", blogPostService.findAll());
        modelAndView.setViewName("admin/blogPosts");
        return modelAndView;
    }

    @GetMapping(value = "admin/addBlogPost")
    public ModelAndView addPost() {
        ModelAndView modelAndView = new ModelAndView();
        BlogPost blogPost = new BlogPost();
        modelAndView.addObject("blogPost", blogPost);
        modelAndView.setViewName("admin/addBlogPost");
        return modelAndView;
    }

    @RequestMapping(value = "admin/addBlogPost", method = RequestMethod.POST)
    public ModelAndView createPost(@Valid BlogPost blogPost, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        BlogPost blogPostExists = blogPostService.findBlogPostByTitle(blogPost.getTitle());
        if (blogPostExists != null) {
            bindingResult.rejectValue("title", "error.title", "Post o danym tytule już istnieje");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/addBlogPost");
        } else {
            blogPostService.create(blogPost);
            modelAndView.addObject("successMessage", "Pomyślnie stworzono post");
            modelAndView.addObject("blogPost", new BlogPost());
            modelAndView.setViewName("admin/addBlogPost");
        }
        return modelAndView;
    }

    @RequestMapping(value = "admin/blogPost/{id}")
    public ModelAndView showPost(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        BlogPost blogPost = blogPostService.findBlogPost(id);
        modelAndView.addObject("blogPost", blogPost);
        modelAndView.setViewName("admin/blogPost");
        return modelAndView;
    }

    @RequestMapping(value = "admin/blogPost/delete", method = RequestMethod.GET)
    public ModelAndView deletePost(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        blogPostService.deleteById(id);

        modelAndView.setViewName("admin/blogPosts");
        return modelAndView;
    }

    @RequestMapping(value = "admin/blogPost/edit", method = RequestMethod.GET)
    public ModelAndView editPost(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        BlogPost blogPost = blogPostService.findBlogPost(id);

        modelAndView.addObject("blogPost", blogPost);
        modelAndView.setViewName("admin/blogPost/edit");
        return modelAndView;
    }

    @RequestMapping(value = "admin/blogPost/edit", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute BlogPost blogPost) {
        ModelAndView modelAndView = new ModelAndView();
        blogPostService.edit(blogPost);

        modelAndView.addObject("blogPost", blogPost);
        modelAndView.setViewName("admin/blogPost/edit");
        return modelAndView;
    }

}

