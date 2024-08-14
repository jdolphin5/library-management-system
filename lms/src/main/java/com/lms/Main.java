package com.lms;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Alice", "L001");
        Student student = new Student("Bob", "S001");

        // Librarian adds books
        librarian.addBook(library, new Book("Effective Java", "Joshua Bloch", "9780134686097"));
        librarian.addBook(library, new Book("Clean Code", "Robert C. Martin", "9780136083238"));

        // Student borrows a book
        Book bookToBorrow = library.searchBook("Effective Java");
        if (bookToBorrow != null && !bookToBorrow.isBorrowed()) {
            student.borrowBook(bookToBorrow);
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

        // return the borrowed book
        Book bookToReturn = library.searchBook("Effective Java");
        if (bookToReturn != null && bookToReturn.isBorrowed()) {
            student.returnBook(bookToReturn);
        } else {
            System.out.println("The book is not borrowed or not found.");
        }

        // List all books in the library that are not borrowed currently
        System.out.println("\nBooks in the library - not borrowed currently:");
        for (Book book : library.getBooksIfNotBorrowed()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: "
                    + book.getIsbn() + ")");
        }

    }
}
