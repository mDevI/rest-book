package com.mdevi.restbook.domain;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "title", length = 150, nullable = false)
    private String title;
    @Column(name = "genre", length = 100, nullable = false)
    private String genre;
    @Column(name = "author", nullable = false)
    private String author;

    public Book() {
    }

    public Book(String isbn, String title, String genre, String author) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
