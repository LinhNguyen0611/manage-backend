package vn.uit.mobilestore.exception.security;


import vn.uit.mobilestore.exception.status.NotFoundException;

public class IdentifierPasswordInvalidException extends NotFoundException {

    private static final String CODE = "USERNAME_OR_PASSWORD_INVALID";

    public IdentifierPasswordInvalidException() {
        super(CODE, "The username/password doesn't not match");
    }

    public IdentifierPasswordInvalidException(String message) {
        super(CODE, message);
    }
}
