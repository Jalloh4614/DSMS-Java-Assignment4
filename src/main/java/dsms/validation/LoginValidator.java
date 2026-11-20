package dsms.validation;

public class LoginValidator {
    public boolean isValid(String username, String password) {
        return username != null && !username.trim().isEmpty()
                && password != null && password.length() >= 4;
    }
}
