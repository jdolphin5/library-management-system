package com.lms;

import java.time.LocalDate;

class Librarian extends User {
    public Librarian(String name, String userId) {
        // call the constructor from the User class
        super(name, userId);
    }

    @Override
    public void borrowBook(Book book, User user) {
        // book already borrowed, reserve it
        if (book.isBorrowed() != null && book.getReservedStatus() == null) {
            book.setReserved(user);

            // book is not borrowed
        } else if (book.isBorrowed() == null) {
            System.out.println(user.getName() + " borrowed the book: " + book.getTitle());
            book.setBorrowed(true, LocalDate.now());
        }
    }

    @Override
    public void returnBook(Book book, LocalDate returnDate) {
        System.out.println("\n" + this.getName() + " returned the book: " + book.getTitle());

        if (book.isBorrowed() != null && returnDate.isAfter(book.isBorrowed())) {
            System.out.println("\n" + "because the date was returned late, there is a fine of $5");
        }

        book.setBorrowed(false, null);

        if (book.getReservedStatus() != null) {
            borrowBook(book, book.getReservedStatus());
            book.setReserved(null);
        }
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBook(Library library, String isbn) {
        library.removeBook(isbn);
    }
}
