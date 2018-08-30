package com.mdevi.restbook;

import com.mdevi.restbook.domain.Book;
import com.mdevi.restbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestBookApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestBookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.save(new Book("9781593275846", "Eloquent JavaScript, Second Edition", "IT", "Marijn Haverbeke"));
        bookRepository.save(new Book("9781449331818", "Learning JavaScript Design Patterns", "IT", "Addy Osmani"));
        bookRepository.save(new Book("9781449365035", "Speaking JavaScript", "IT", "Axel Rauschmayer"));
        bookRepository.save(new Book("9781491950296", "Programming JavaScript Applications", "IT", "Eric Elliott"));
        bookRepository.save(new Book("9780526698316", "Enim ullamco exercitation mollit cillum", "History", "Leona Sears"));
        bookRepository.save(new Book("9799330906092", "Aliqua aliqua minim Lorem consectetur", "Fiction", "Gale Bush"));
        bookRepository.save(new Book("9781209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9791209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9791209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9781209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9781209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9791209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9781209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9791209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9781209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9791209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9781209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9791209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9781209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
        bookRepository.save(new Book("9791209504915", "Lorem duis incididunt dolor est", "Art", "Horton Walker"));
    }
}
