package com.cookie;

import com.cookie.model.BlogPost;
import com.cookie.service.BlogPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


/**
 * Created by Gosia
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@WebAppConfiguration
public class SpringTest {

    @Autowired
    BlogPostService blogPostService;

    @Test
    public void testFindByTitle() {
        BlogPost blogPost = blogPostService.findBlogPostByTitle("gfdfdfdgdgfgdfsgfsdsdgf");
    }

    @Test
    public void testFindAllBlogPosts(){
        List<BlogPost> posts = blogPostService.findAll();

    }
}