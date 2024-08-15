package com.lms.auth;

import com.lms.*;
import java.util.*;
import org.springframework.security.crypto.bcrypt.*;

public class AuthSystem {
    private Map<String, Librarian> librarianDatabase = new HashMap<>();

    public void registerLibrarian(Librarian librarian, String password) {
        // Hash the password and store the librarian
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        librarian.setHashedPassword(hashedPassword);
        librarianDatabase.put(librarian.getUsername(), librarian);
    }

    public Librarian login(String username, String password) {
        Librarian librarian = librarianDatabase.get(username);
        if (librarian != null && librarian.authenticate(password)) {
            return librarian; // Authentication successful
        } else {
            throw new IllegalArgumentException("Invalid username or password.");
        }
    }
}
