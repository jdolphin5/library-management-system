package com.lms;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import com.lms.*;
import com.lms.auth.AuthSystem;
import com.lms.auth.SessionManager;

public class JUnitTest {

    private SessionManager sessionManager;
    private Library library;
    private Student student;
    private Book book;
    private Book book2;

    // Fixtures is a fixed state of a set of objects used as a baseline for running tests
    @BeforeEach
    void setup() {
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
    void testAddBook() {
        assertTrue(library.getBooks().contains(book));
    }

    @Test
    void testSearchBook() {
        Book foundBook = library.searchBook("Effective Java");
        assertNotNull(foundBook);
        assertEquals("Effective Java", foundBook.getTitle());
    }

    @Test
    void testReturnBook() {
        student.borrowBook(book);
        assertTrue(book.isBorrowed() != null);
        student.returnBook(book, LocalDate.now());
        assertTrue(book.isBorrowed() == null);
    }

    @Test
    void testBorrowLimit() {
        student.borrowBook(book);
        assertTrue(book.isBorrowed() != null);
        student.borrowBook(book2);
        assertTrue(student.getBookCount() == student.getLimit());
    }

    @Test
    void testLibrarianAuth() {
        AuthSystem authSystem = new AuthSystem();
        Librarian librarian = new Librarian("TestName", "1", "TestUsername");
        authSystem.registerLibrarian(librarian, "TestPassword");
        Librarian authenticatedLibrarian = authSystem.login("TestUsername", "TestPassword");

        assertFalse(sessionManager.isAuthenticated());
        sessionManager.login(authenticatedLibrarian);
        assertTrue(sessionManager.isAuthenticated());
    }

    @Test
    void timeoutNotExceededLibrarianAuth() {
        assertTimeout(ofMillis(200), () -> testAuth());
    }

    void testAuth() throws InterruptedException {
        AuthSystem authSystem = new AuthSystem();
        Librarian librarian = new Librarian("TestName", "1", "TestUsername");
        authSystem.registerLibrarian(librarian, "TestPassword");
        Librarian authenticatedLibrarian = authSystem.login("TestUsername", "TestPassword");

        sessionManager.login(authenticatedLibrarian);

        if (!sessionManager.isAuthenticated())
            throw new InterruptedException("authentication failed");
    }
}
