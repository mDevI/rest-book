package com.mdevi.restbook.util;

import com.mdevi.restbook.domain.Book;

import java.util.List;

public class BookGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Book> booksData;

    public BookGrid() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<Book> getBooksData() {
        return booksData;
    }

    public void setBooksData(List<Book> booksData) {
        this.booksData = booksData;
    }
}
