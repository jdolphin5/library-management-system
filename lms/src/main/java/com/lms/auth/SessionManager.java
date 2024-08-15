package com.lms.auth;

import com.lms.*;

public class SessionManager {
    private Librarian currentLibrarian;

    public void login(Librarian librarian) {
        this.currentLibrarian = librarian;
    }

    public void logout() {
        this.currentLibrarian = null;
    }

    public boolean isAuthenticated() {
        return this.currentLibrarian != null;
    }

    public Librarian getCurrentLibrarian() {
        return currentLibrarian;
    }
}
