package com.mdevi.restbook.controler;

import com.mdevi.restbook.domain.Book;
import com.mdevi.restbook.exception.ResourceNotFoundException;
import com.mdevi.restbook.repository.BookRepository;
import com.mdevi.restbook.util.BookGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final Logger LOG = LoggerFactory.getLogger(BookRestController.class);

    @Autowired
    private BookRepository bookRepository;


    @GetMapping(value = "/book", produces = "application/json")
    public BookGrid getAllBooksByPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                      @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
                                      @RequestParam(value = "sidx", required = false) String sortBy,
                                      @RequestParam(value = "sord", required = false) String order) {

        LOG.info("GET. Page {} fetched with {} rows, sorted by {} in order by {}.", page, rows, sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else
                sort = new Sort(Sort.Direction.ASC, orderBy);
        }


        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0,
        // while jqGrid starts with 1
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = PageRequest.of(page - 1, rows, sort);
        } else {
            pageRequest = PageRequest.of(page - 1, rows);
        }
        Page<Book> bookPage = bookRepository.findAll(pageRequest);

        // Construct the grid data that will return as JSON data
        BookGrid bookGrid = new BookGrid();
        bookGrid.setBooksData(bookPage.stream().collect(Collectors.toList()));
        bookGrid.setCurrentPage(bookPage.getNumber() + 1);
        bookGrid.setTotalPages(bookPage.getTotalPages());
        bookGrid.setTotalRecords(bookPage.getTotalElements());
        return bookGrid;
    }

    @GetMapping(value = "/book/{id}", produces = "application/json")
    public Book readBookByID(@PathVariable("id") long id) {
        LOG.info("GET. A book with id {} has been inquired.", id);
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("No book found with id=" + id));
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        LOG.info("POST. A new book with id {} was saved.", savedBook.getId());
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping(value = "/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Optional<Book> theBook = bookRepository.findById(id);
        if (!theBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        bookRepository.save(book);
        LOG.info("PUT. The book {} has been updated. ", book.getTitle());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") long id) {
        LOG.info("DELETE. The book with id {} has been deleted.", id);
        bookRepository.deleteById(id);
    }

}