package com.lms;

import java.util.ArrayList;
import java.util.List;

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Removed book with ISBN: " + isbn);
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
