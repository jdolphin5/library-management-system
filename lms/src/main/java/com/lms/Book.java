package com.lms;

import java.time.LocalDate;

class Book {
    public enum Category {
        UNDEFINED, EDUCATION, ROMANCE, THRILLER, FANTASY, SCIFI
    }

    private String title;
    private String author;
    private String isbn;
    // private boolean isBorrowed;
    private LocalDate dueDate;
    private User reserved;
    private Category category;

    public Book(String title, String author, String isbn, Category category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.dueDate = null;
        this.reserved = null;
        this.category = category;
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

    public Category getCategory() {
        return this.category;
    }

    public LocalDate isBorrowed() {
        return this.dueDate;
    }

    public void setBorrowed(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReserved(User user) {
        this.reserved = user;
    }
}
