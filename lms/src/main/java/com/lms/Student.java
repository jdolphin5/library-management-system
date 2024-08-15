package com.lms;

import java.time.LocalDate;

class Student extends User {
    private int limit;
    private int bookCount;

    public Student(String name, String userId, int limit) {
        super(name, userId);
        this.limit = limit;
        this.bookCount = 0;
    }

    @Override
    public void borrowBook(Book book) {
        if (bookCount + 1 > limit) {
            System.out.println("\n" + this.getName() + " has already borrowed their limit of "
                    + this.bookCount + " book(s)");
            return;
        }

        this.bookCount++;
        // book already borrowed, reserve it
        if (book.isBorrowed() != null && book.getReservedUser() == null) {
            System.out.println(this.getName() + " reserved the book: " + book.getTitle());
            book.setReserved(this);

            // book is not borrowed
        } else if (book.isBorrowed() == null) {
            System.out.println(this.getName() + " borrowed the book: " + book.getTitle());
            book.setBorrowed(LocalDate.now());
        }
    }

    @Override
    public void returnBook(Book book, LocalDate returnDate) {
        System.out.println("\n" + this.getName() + " returned the book: " + book.getTitle());

        if (book.isBorrowed() != null && returnDate.isAfter(book.isBorrowed())) {
            System.out.println("\n" + "because the date was returned late, there is a fine of $5");
        }

        book.setBorrowed(null);
        this.bookCount--;

        if (book.getReservedUser() != null) {
            book.getReservedUser().borrowBook(book);
            book.setReserved(null);
        }
    }

    public int getLimit() {
        return this.limit;
    }

    public int getBookCount() {
        return this.bookCount;
    }
}
