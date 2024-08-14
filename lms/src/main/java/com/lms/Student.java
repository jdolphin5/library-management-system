package com.lms;
class Student extends User {
    public Student(String name, String userId) {
        super(name, userId);
    }

    @Override
    public void borrowBook(Book book) {
        System.out.println(getName() + " borrowed the book: " + book.getTitle());
        book.setBorrowed(true);
    }
}