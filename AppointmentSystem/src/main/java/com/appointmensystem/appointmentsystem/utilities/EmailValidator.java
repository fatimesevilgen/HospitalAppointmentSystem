package com.appointmensystem.appointmentsystem.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    // Email regex pattern
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    // Method to validate email address
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
