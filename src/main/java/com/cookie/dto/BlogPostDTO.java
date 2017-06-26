package com.cookie.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gosia on 26.06.2017.
 */
@Component
@Scope
public class BlogPostDTO implements Serializable{

    private static final long serialVersionUID = -6518171412015203128L;


    private int id;
    @NotEmpty
    private String title;
    @NotEmpty()
    private String text;

    private Date date= new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
