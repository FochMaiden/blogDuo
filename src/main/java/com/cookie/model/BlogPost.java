package com.cookie.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by FochMaiden
 */
@Entity
@Table(name = "blogPost")
public class BlogPost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blogPost_id")
    private int id;
    @Column(name = "title")
    @NotEmpty(message = "Proszę podać tytuł")
    private String title;
    @Column(name = "text")
    @NotEmpty(message = "Proszę podać text")
    private String text;
    @Column(name = "time")
    private Date date= new Date();

    @ManyToOne
    @JoinColumn(name = "blogPost_author")
    private User author;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
