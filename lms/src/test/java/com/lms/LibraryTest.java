package com.lms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testAddBook() {
        Library library = new Library();
        Book book = new Book("Effective Java", "Joshua Bloch", "9780134686097",
                Book.Category.EDUCATION);
        library.addBook(book);

        assertTrue(library.getBooks().contains(book));
    }

    @Test
    void testSearchBook() {
        Library library = new Library();
        Book book = new Book("Effective Java", "Joshua Bloch", "9780134686097",
                Book.Category.EDUCATION);
        library.addBook(book);

        Book foundBook = library.searchBook("Effective Java");
        assertNotNull(foundBook);
        assertEquals("Effective Java", foundBook.getTitle());
    }


}
