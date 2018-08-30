package com.mdevi.restbook;

import com.mdevi.restbook.domain.Book;
import com.mdevi.restbook.util.BookGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookRestApiUnitTests {

    private static final String ROOT_URL = "http://localhost:8082";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testGetAllBooks() {
        ResponseEntity<BookGrid> responseEntity = restTemplate.getForEntity(ROOT_URL + "/api/book?page=1", BookGrid.class);
        List<Book> booksList = responseEntity.getBody().getBooksData();
        assertNotNull(booksList);
    }

    @Test
    public void testGetBookById() {
        Book theBook = restTemplate.getForObject(ROOT_URL + "/api/book/1", Book.class);
        assertNotNull(theBook);
    }

    @Test
    public void testSaveNewBook() {
        Book newBook = new Book();
        newBook.setAuthor("John Doe");
        newBook.setGenre("Fiction");
        newBook.setTitle("A test book title");
        ResponseEntity<Book> bookResponseEntity = restTemplate.postForEntity(ROOT_URL + "/api/book", newBook, Book.class);
        assertNotNull(bookResponseEntity);
        assertNotNull(bookResponseEntity.getBody().getId());
    }

    @Test
    public void testUpdateBookInfo() {
        long bookId = 1L;
        Book bookIdOne = restTemplate.getForObject(ROOT_URL + "/api/book/" + bookId, Book.class);
        bookIdOne.setGenre("Test Genre");
        restTemplate.put(ROOT_URL + "/api/book/" + bookId, bookIdOne);
        Book updatedBook = restTemplate.getForObject(ROOT_URL + "/api/book/" + bookId, Book.class);
        assertNotNull(updatedBook);
    }

    @Test
    public void testDeleteBook() {
        long bookId = 2L;
        Book bookToDelete = restTemplate.getForObject(ROOT_URL + "/api/book/" + bookId, Book.class);
        assertNotNull(bookToDelete);
        restTemplate.delete(ROOT_URL + "/api/book/" + bookId);

        try {
            bookToDelete = restTemplate.getForObject(ROOT_URL + "/api/book/" + bookId, Book.class);
        } catch (final HttpClientErrorException ex) {
            assertEquals(ex.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
