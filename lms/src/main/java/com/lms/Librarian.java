package com.lms;

import java.time.LocalDate;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Librarian extends User {
    private String username;
    private String hashedPassword;

    public Librarian(String name, String userId, String username) {
        // call the constructor from the User class
        super(name, userId);
        this.username = username;
    }

    public boolean authenticate(String inputPassword) {
        return BCrypt.checkpw(inputPassword, this.hashedPassword);
    }

    public String getUsername() {
        return this.username;
    }

    public void setHashedPassword(String hashedPw) {
        this.hashedPassword = hashedPw;
    }

    @Override
    public void borrowBook(Book book) {
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

        if (book.getReservedUser() != null) {
            book.getReservedUser().borrowBook(book);
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
