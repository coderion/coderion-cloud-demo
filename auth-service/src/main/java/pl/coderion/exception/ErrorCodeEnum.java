package pl.coderion.exception;

public enum ErrorCodeEnum {

    // User
    USER_INACTIVE_ACCOUNT("Account is not active"),
    USER_INVALID_EMAIL("Invalid e-mail adress"),
    USER_INVALID_USER_OR_PASSWORD("Invalid username or password"),
    USER_SAME_EMAIL("User e-mail has not changed"),
    USER_USER_ALREADY_EXISTS("User already exists");

    private String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return name();
    }
}