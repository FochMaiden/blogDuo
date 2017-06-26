package com.cookie.service;

import com.cookie.model.BlogPost;
import com.cookie.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FochMaiden
 */
@Service("blogPostService")
public class BlogPostServiceImpl implements BlogPostService {


    @Autowired
    private BlogPostRepository blogPostRepository;


    @Override
    public BlogPost findBlogPostByTitle(String title) {
        return blogPostRepository.findBlogPostByTitle(title);
    }


    @Override
    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost findBlogPost(int blogPostId) {
        return blogPostRepository.getOne(blogPostId);
    }

    @Override
    public BlogPost create(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost edit(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public void deleteById(Integer id) {
        blogPostRepository.delete(id);
    }

}
