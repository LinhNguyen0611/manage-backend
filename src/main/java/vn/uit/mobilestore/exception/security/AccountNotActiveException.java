package vn.uit.mobilestore.exception.security;

import vn.uit.mobilestore.exception.status.ForbiddenException;

public class AccountNotActiveException extends ForbiddenException {

    public AccountNotActiveException() {
        super("ACCOUNT_NOT_ACTIVE", "The account isn't active");
    }
}
