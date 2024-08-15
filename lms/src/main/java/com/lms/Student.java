package com.lms;

import java.time.LocalDate;

class Student extends User {
    public Student(String name, String userId) {
        super(name, userId);
    }

    @Override
    public void borrowBook(Book book, User user) {
        System.out.println(user.getName() + " borrowed the book: " + book.getTitle());
        book.setBorrowed(LocalDate.now());
    }

    @Override
    public void returnBook(Book book, LocalDate returnDate) {
        System.out.println("\n" + this.getName() + " returned the book: " + book.getTitle());

        if (book.isBorrowed() != null && returnDate.isAfter(book.isBorrowed())) {
            System.out.println("\n" + "because the date was returned late, there is a fine of $5");
        }

        book.setBorrowed(null);

        if (book.getReservedStatus() != null) {
            borrowBook(book, book.getReservedStatus());
        }
    }
}
