package com.lms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import com.lms.auth.*;
import java.time.LocalDate;

class LibraryTest {

    private SessionManager sessionManager;
    private Library library;
    private Student student;
    private Book book;
    private Book book2;

    @BeforeEach
    public void setup() {
        sessionManager = new SessionManager();
        library = new Library(sessionManager);
        book = new Book("Effective Java", "Joshua Bloch", "9780134686097", Book.Category.EDUCATION);
        library.addBookWithoutAuth(book);
        book2 = new Book("Clean Code", "Robert C. Martin", "9780136083238",
                Book.Category.EDUCATION);
        library.addBookWithoutAuth(book2);
        library.addBookWithoutAuth(new Book("Pride and Prejudice", "Jane Austen", "9780140434262",
                Book.Category.ROMANCE));
        library.addBookWithoutAuth(new Book("Crime and Punishment", "Fyodor Dostoevsky",
                "9780140449136", Book.Category.THRILLER));

        student = new Student("Bob", "S001", 1);
    }

    @Test
    public void testAddBook() {
        assertTrue(library.getBooks().contains(book));
    }

    @Test
    public void testSearchBook() {
        Book foundBook = library.searchBook("Effective Java");
        assertNotNull(foundBook);
        assertEquals("Effective Java", foundBook.getTitle());
    }

    @Test
    public void testReturnBook() {
        student.borrowBook(book);
        assertTrue(book.isBorrowed() != null);
        student.returnBook(book, LocalDate.now());
        assertTrue(book.isBorrowed() == null);
    }

    @Test
    public void testBorrowLimit() {
        student.borrowBook(book);
        assertTrue(book.isBorrowed() != null);
        student.borrowBook(book2);
        assertTrue(student.getBookCount() == student.getLimit());
    }

    @Test
    public void testLibrarianAuth() {
        AuthSystem authSystem = new AuthSystem();
        Librarian librarian = new Librarian("TestName", "1", "TestUsername");
        authSystem.registerLibrarian(librarian, "TestPassword");
        Librarian authenticatedLibrarian = authSystem.login("TestUsername", "TestPassword");

        assertFalse(sessionManager.isAuthenticated());
        sessionManager.login(authenticatedLibrarian);
        assertTrue(sessionManager.isAuthenticated());
    }

}
