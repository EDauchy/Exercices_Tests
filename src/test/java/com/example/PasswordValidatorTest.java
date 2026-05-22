package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
    }

    // --- Tests classiques ---

    @Test
    void isValid_password1Exclamation_returnsTrue() {
        assertTrue(validator.isValid("Password1!"));
        assertEquals(PasswordValidator.MSG_VALID, validator.getErrorMessage("Password1!"));
    }

    @Test
    void isValid_admin2024At_returnsTrue() {
        assertTrue(validator.isValid("Admin2024@"));
        assertEquals(PasswordValidator.MSG_VALID, validator.getErrorMessage("Admin2024@"));
    }

    @Test
    void isValid_short1Exclamation_returnsFalse() {
        assertFalse(validator.isValid("short1!"));
        assertEquals(PasswordValidator.MSG_LENGTH, validator.getErrorMessage("short1!"));
    }

    @Test
    void isValid_password1ExclamationNoLowercase_returnsFalse() {
        assertFalse(validator.isValid("PASSWORD1!"));
        assertEquals(PasswordValidator.MSG_LOWERCASE, validator.getErrorMessage("PASSWORD1!"));
    }

    @Test
    void isValid_password1ExclamationNoUppercase_returnsFalse() {
        assertFalse(validator.isValid("password1!"));
        assertEquals(PasswordValidator.MSG_UPPERCASE, validator.getErrorMessage("password1!"));
    }

    @Test
    void isValid_passwordExclamationNoDigit_returnsFalse() {
        assertFalse(validator.isValid("Password!"));
        assertEquals(PasswordValidator.MSG_DIGIT, validator.getErrorMessage("Password!"));
    }

    @Test
    void isValid_password1NoSpecial_returnsFalse() {
        assertFalse(validator.isValid("Password1"));
        assertEquals(PasswordValidator.MSG_SPECIAL, validator.getErrorMessage("Password1"));
    }

    @Test
    void isValid_null_returnsFalse() {
        assertFalse(validator.isValid(null));
        assertEquals(PasswordValidator.MSG_NULL, validator.getErrorMessage(null));
    }

    // --- @CsvSource ---

    @ParameterizedTest(name = "password={0} -> valid={1}, message={2}")
    @CsvSource({
            "Password1!,     true,  Password is valid",
            "Admin2024@,     true,  Password is valid",
            "short1!,        false, Password must contain at least 8 characters",
            "PASSWORD1!,     false, Password must contain at least one lowercase letter",
            "password1!,     false, Password must contain at least one uppercase letter",
            "Password!,      false, Password must contain at least one digit",
            "Password1,      false, Password must contain at least one special character"
    })
    void validatePassword_withCsvSource(String password, boolean expectedValid, String expectedMessage) {
        assertEquals(expectedValid, validator.isValid(password));
        assertEquals(expectedMessage, validator.getErrorMessage(password));
    }

    // --- @ValueSource ---

    @ParameterizedTest
    @ValueSource(strings = {"PASSWORD1!", "password1!", "short1!", "Password!", "Password1"})
    void isValid_invalidPasswordsFromValueSource_returnsFalse(String password) {
        assertFalse(validator.isValid(password));
    }

    // --- @MethodSource ---

    @ParameterizedTest(name = "password={0} -> valid={1}")
    @MethodSource("passwordValidationCases")
    void validatePassword_withMethodSource(String password, boolean expectedValid, String expectedMessage) {
        assertEquals(expectedValid, validator.isValid(password));
        assertEquals(expectedMessage, validator.getErrorMessage(password));
    }

    static Stream<Arguments> passwordValidationCases() {
        return Stream.of(
                Arguments.of("Password1!", true, PasswordValidator.MSG_VALID),
                Arguments.of("Admin2024@", true, PasswordValidator.MSG_VALID),
                Arguments.of("short1!", false, PasswordValidator.MSG_LENGTH),
                Arguments.of("PASSWORD1!", false, PasswordValidator.MSG_LOWERCASE),
                Arguments.of("password1!", false, PasswordValidator.MSG_UPPERCASE),
                Arguments.of("Password!", false, PasswordValidator.MSG_DIGIT),
                Arguments.of("Password1", false, PasswordValidator.MSG_SPECIAL),
                Arguments.of(null, false, PasswordValidator.MSG_NULL)
        );
    }

    // --- Bonus : @NullAndEmptySource ---

    @ParameterizedTest
    @NullAndEmptySource
    void getErrorMessage_nullOrEmpty_returnsExpectedMessage(String password) {
        if (password == null) {
            assertEquals(PasswordValidator.MSG_NULL, validator.getErrorMessage(password));
            assertFalse(validator.isValid(password));
        } else {
            assertEquals(PasswordValidator.MSG_LENGTH, validator.getErrorMessage(password));
            assertFalse(validator.isValid(password));
        }
    }
}
