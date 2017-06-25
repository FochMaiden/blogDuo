package com.cookie.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

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
    @NotEmpty(message = "Proszę podać Tytuł")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "time")
    private Date date= new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
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