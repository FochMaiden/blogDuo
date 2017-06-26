package com.cookie.control;

import com.cookie.dto.BlogPostDTO;
import com.cookie.dto.BlogPostMapper;
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

        modelAndView.setViewName("redirect:/admin/blogPosts");
        return modelAndView;
    }

    @RequestMapping(value = "admin/editBlogPost", method = RequestMethod.GET)
    public ModelAndView editPost(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        BlogPost blogPost = blogPostService.findBlogPost(id);
        BlogPostDTO blogPostDTO = BlogPostMapper.getBlogPostDTO(blogPost);
        modelAndView.addObject("blogPostDTO", blogPostDTO);
        modelAndView.setViewName("admin/editBlogPost");
        return modelAndView;
    }

    @RequestMapping(value = "admin/editBlogPost", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute BlogPostDTO blogPostDTO,
                                 @RequestParam(value = "action") String action) {
        ModelAndView modelAndView = new ModelAndView("redirect:blogPosts");
        String message = null;

        if(action.equals("save")){
            BlogPost blogPost = BlogPostMapper.getBlogPost(blogPostDTO);
            blogPostService.edit(blogPost);
            message = "Blog post" + blogPost.getId() + "został pomyślnie zedytowany";
            modelAndView.addObject("blogPost", blogPost);
            modelAndView.addObject("message", message);
        }
        if(action.equals("cancel")){
            message = "Blog post" + blogPostDTO.getId() + "nie został zedytowny";
        }


        return modelAndView;
    }

}

