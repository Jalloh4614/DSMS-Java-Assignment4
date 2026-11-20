package dsms.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginValidatorTest {
    private final LoginValidator validator = new LoginValidator();

    @Test
    void acceptsFilledUsernameAndPassword() {
        assertTrue(validator.isValid("cashier", "pass123"));
    }

    @Test
    void rejectsEmptyUsername() {
        assertFalse(validator.isValid("", "pass123"));
    }

    @Test
    void rejectsPasswordShorterThanFourCharacters() {
        assertFalse(validator.isValid("cashier", "123"));
    }
}
