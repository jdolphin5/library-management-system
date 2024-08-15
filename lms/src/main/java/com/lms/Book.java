package com.lms;

import java.time.LocalDate;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;
    private LocalDate dueDate;
    private User reserved;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
        this.dueDate = null;
        this.reserved = null;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public User getReservedStatus() {
        return this.reserved;
    }

    public LocalDate isBorrowed() {
        return this.dueDate;
    }

    public void setBorrowed(boolean borrowed, LocalDate dueDate) {
        this.isBorrowed = borrowed;
        this.dueDate = dueDate;
    }

    public void setReserved(User user) {
        this.reserved = user;
    }
}
