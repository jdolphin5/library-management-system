package com.lms;

import java.time.LocalDate;

class Librarian extends User {
    public Librarian(String name, String userId) {
        // call the constructor from the User class
        super(name, userId);
    }

    @Override
    public void borrowBook(Book book) {
        System.out.println(getName() + " borrowed the book: " + book.getTitle());
        book.setBorrowed(true, LocalDate.now());
    }

    @Override
    public void returnBook(Book book, LocalDate returnDate) {
        System.out.println("\n" + this.getName() + " returned the book: " + book.getTitle());

        if (book.isBorrowed() != null && returnDate.isAfter(book.isBorrowed())) {
            System.out.println("\n" + "because the date was returned late, there is a fine of $5");
        }

        book.setBorrowed(false, null);
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBook(Library library, String isbn) {
        library.removeBook(isbn);
    }
}
