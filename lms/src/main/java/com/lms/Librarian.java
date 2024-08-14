package com.lms;

class Librarian extends User {
    public Librarian(String name, String userId) {
        // call the constructor from the User class
        super(name, userId);
    }

    @Override
    public void borrowBook(Book book) {
        System.out.println(getName() + " borrowed the book: " + book.getTitle());
        book.setBorrowed(true);
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBook(Library library, String isbn) {
        library.removeBook(isbn);
    }
}
