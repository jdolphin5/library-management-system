package com.lms;

class Student extends User {
    public Student(String name, String userId) {
        super(name, userId);
    }

    @Override
    public void borrowBook(Book book) {
        System.out.println(this.getName() + " borrowed the book: " + book.getTitle());
        book.setBorrowed(true);
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("\n" + this.getName() + " returned the book: " + book.getTitle());
        book.setBorrowed(false);
    }
}
