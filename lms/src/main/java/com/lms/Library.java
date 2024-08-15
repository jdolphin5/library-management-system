package com.lms;

import com.lms.auth.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private SessionManager sessionManager;

    public Library(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        books = new ArrayList<>();
    }

    public void addBookWithoutAuth(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
    }

    public void addBook(Book book) {
        if (sessionManager.isAuthenticated()) {
            books.add(book);
            System.out.println("Added book: " + book.getTitle());
        } else {
            throw new SecurityException("Librarian unauthorised to add book");
        }

    }

    public void removeBook(String isbn) {
        if (sessionManager.isAuthenticated()) {
            books.removeIf(book -> book.getIsbn().equals(isbn));
            System.out.println("Removed book with ISBN: " + isbn);
        } else {
            throw new SecurityException("Unauthorized access. Librarian authentication required.");
        }
    }

    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)
                    || book.getIsbn().equals(keyword)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> getBooksIfNotBorrowed() {
        List<Book> ret = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).isBorrowed() == null) {
                ret.add(books.get(i));
            }
        }

        return ret;
    }

    public List<Book> getBooksWithCategory(Book.Category category) {
        List<Book> ret = new ArrayList<>();

        for (Book book : books) {
            if (book.getCategory() == category) {
                ret.add(book);
            }
        }

        return ret;
    }
}
