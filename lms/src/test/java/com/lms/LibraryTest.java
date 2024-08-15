package com.lms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

class LibraryTest {

    private Library library;
    private Student student;
    private Book book;

    @BeforeEach
    public void setup() {
        library = new Library();
        book = new Book("Effective Java", "Joshua Bloch", "9780134686097", Book.Category.EDUCATION);
        library.addBook(book);
        student = new Student("Bob", "S001");
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

}
