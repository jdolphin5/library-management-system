package com.lms.auth;

import com.lms.*;
import java.time.LocalDate;

public class AuditLog {
    public static void log(String action, Librarian librarian) {
        System.out.println(LocalDate.now() + ": " + action + " by " + librarian.getName());
    }
}
