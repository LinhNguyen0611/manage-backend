package vn.uit.mobilestore.exception.status;

import org.springframework.http.HttpStatus;
import vn.uit.mobilestore.exception.RestException;

public class UnauthorizedException extends RestException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException() {
        super(HTTP_STATUS);
    }

    public UnauthorizedException(String message) {
        super(HTTP_STATUS, HTTP_STATUS.name(), message);
    }

    public UnauthorizedException(String code, String message) {
        super(HTTP_STATUS, code, message);
    }
}
