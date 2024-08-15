package com.lms;

import java.time.LocalDate;

abstract class User {
    private String name;
    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public String getUserId() {
        return this.userId;
    }

    public abstract void borrowBook(Book book, User user);

    public abstract void returnBook(Book book, LocalDate returnDate);
}
