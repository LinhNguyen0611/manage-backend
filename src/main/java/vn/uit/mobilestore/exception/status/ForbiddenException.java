package vn.uit.mobilestore.exception.status;

import org.springframework.http.HttpStatus;
import vn.uit.mobilestore.exception.RestException;

public class ForbiddenException extends RestException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

    public ForbiddenException() {
        super(HTTP_STATUS);
    }

    public ForbiddenException(String message) {
        super(HTTP_STATUS, HTTP_STATUS.name(), message);
    }

    public ForbiddenException(String code, String message) {
        super(HTTP_STATUS, code, message);
    }
}
