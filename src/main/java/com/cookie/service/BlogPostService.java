package com.cookie.service;

import com.cookie.model.BlogPost;

import java.util.List;

/**
 * Created by FochMaiden
 */

public interface BlogPostService {
    BlogPost findBlogPostByTitle(String title);
    List<BlogPost> findAll();
    BlogPost findBlogPost(int blogPostId);
    BlogPost create(BlogPost blogPost);
    BlogPost edit(BlogPost blogPost);
    void deleteById(Integer id);


}
