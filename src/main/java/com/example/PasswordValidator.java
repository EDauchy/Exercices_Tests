package com.example;

public class PasswordValidator {

    public static final String MSG_NULL = "Password must not be null";
    public static final String MSG_LENGTH = "Password must contain at least 8 characters";
    public static final String MSG_LOWERCASE = "Password must contain at least one lowercase letter";
    public static final String MSG_UPPERCASE = "Password must contain at least one uppercase letter";
    public static final String MSG_DIGIT = "Password must contain at least one digit";
    public static final String MSG_SPECIAL = "Password must contain at least one special character";
    public static final String MSG_VALID = "Password is valid";

    private static final String LOWERCASE_PATTERN = ".*[a-z].*";
    private static final String UPPERCASE_PATTERN = ".*[A-Z].*";
    private static final String DIGIT_PATTERN = ".*[0-9].*";
    private static final String SPECIAL_PATTERN = ".*[!@#$%].*";

    public boolean isValid(String password) {
        return MSG_VALID.equals(getErrorMessage(password));
    }

    public String getErrorMessage(String password) {
        if (password == null) {
            return MSG_NULL;
        }
        if (password.length() < 8) {
            return MSG_LENGTH;
        }
        if (!password.matches(LOWERCASE_PATTERN)) {
            return MSG_LOWERCASE;
        }
        if (!password.matches(UPPERCASE_PATTERN)) {
            return MSG_UPPERCASE;
        }
        if (!password.matches(DIGIT_PATTERN)) {
            return MSG_DIGIT;
        }
        if (!password.matches(SPECIAL_PATTERN)) {
            return MSG_SPECIAL;
        }
        return MSG_VALID;
    }
}
