package vn.uit.mobilestore.exception.security;

import vn.uit.mobilestore.exception.status.ForbiddenException;

public class TokenInvalidException extends ForbiddenException {

    public TokenInvalidException() {
        super("TOKEN_IS_INVALID", "The token is invalid");
    }
}
