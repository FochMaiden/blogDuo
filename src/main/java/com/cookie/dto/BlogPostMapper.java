package com.cookie.dto;

import com.cookie.integration.BlogPost;

/**
 * Created by Gosia on 26.06.2017.
 */
public class BlogPostMapper {

    public static BlogPost getBlogPost(BlogPostDTO blogPostDTO){

        BlogPost blogPost = new BlogPost();
        blogPost.setId(blogPostDTO.getId());
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setText(blogPostDTO.getText());

        return blogPost;
    }

    public static BlogPostDTO getBlogPostDTO (BlogPost blogPost){
        BlogPostDTO blogPostDTO = new BlogPostDTO();
        blogPostDTO.setId(blogPost.getId());
        blogPostDTO.setTitle(blogPost.getTitle());
        blogPostDTO.setText(blogPost.getText());

        return blogPostDTO;
    }

}
