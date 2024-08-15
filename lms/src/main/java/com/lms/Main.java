package com.lms;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Alice", "L001");
        Student student = new Student("Bob", "S001", 1);

        // Librarian adds books
        librarian.addBook(library, new Book("Effective Java", "Joshua Bloch", "9780134686097",
                Book.Category.EDUCATION));
        librarian.addBook(library, new Book("Clean Code", "Robert C. Martin", "9780136083238",
                Book.Category.EDUCATION));
        librarian.addBook(library, new Book("Pride and Prejudice", "Jane Austen", "9780140434262",
                Book.Category.ROMANCE));
        librarian.addBook(library, new Book("Crime and Punishment", "Fyodor Dostoevsky",
                "9780140449136", Book.Category.THRILLER));

        // Student borrows a book
        Book bookToBorrow = library.searchBook("Effective Java");
        if (bookToBorrow != null && bookToBorrow.isBorrowed() == null) {
            student.borrowBook(bookToBorrow);
        } else {
            System.out.println("The book is already borrowed or not found.");
        }

        // Student borrows a second book
        Book bookToBorrow2 = library.searchBook("Pride and Prejudice");
        if (bookToBorrow2 != null && bookToBorrow2.isBorrowed() == null) {
            student.borrowBook(bookToBorrow2);
        } else {
            System.out.println("The book is already borrowed or not found.");
        }

        // List all books in the library
        System.out.println("\nBooks in the library:");
        for (Book book : library.getBooks()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: "
                    + book.getIsbn() + ")");
        }

        // List all books in the library that are not borrowed currently
        System.out.println("\nBooks in the library - not borrowed currently:");
        for (Book book : library.getBooksIfNotBorrowed()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: "
                    + book.getIsbn() + ")");
        }

        // Try to borrow a book as the librarian while currently borrowed by student
        if (bookToBorrow != null && bookToBorrow.isBorrowed() == null) {
            librarian.borrowBook(bookToBorrow);
        } else if (bookToBorrow != null && bookToBorrow.isBorrowed() != null) {
            librarian.borrowBook(bookToBorrow);
        } else {
            System.out.println("The book is not found.");
        }

        // return the borrowed book
        Book bookToReturn = library.searchBook("Effective Java");
        if (bookToReturn != null && bookToReturn.isBorrowed() != null) {
            // late return
            // student.returnBook(bookToReturn, LocalDate.of(2030, 8, 16));
            // same day return
            student.returnBook(bookToReturn, LocalDate.now());
        } else {
            System.out.println("The book is not borrowed or not found.");
        }

        // List all books in the library that are not borrowed currently
        System.out.println("\nBooks in the library - not borrowed currently:");
        for (Book book : library.getBooksIfNotBorrowed()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: "
                    + book.getIsbn() + ")");
        }

        // List all books in the library of category Book.Category.EDUCATION
        System.out.println("\nBooks in the education category in the library:");
        for (Book book : library.getBooksWithCategory(Book.Category.EDUCATION)) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: "
                    + book.getIsbn() + ")");
        }
    }
}
