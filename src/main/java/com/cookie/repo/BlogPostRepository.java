package com.cookie.repo;

import com.cookie.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FochMaiden
 */
@Repository("blogRepo")
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    BlogPost findBlogPostByTitle(String blogPost);
}
