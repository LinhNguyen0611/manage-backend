package vn.uit.mobilestore.exception.status;

import org.springframework.http.HttpStatus;
import vn.uit.mobilestore.exception.RestException;

public class BadRequestException extends RestException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException() {
        super(HTTP_STATUS);
    }

    public BadRequestException(String message) {
        super(HTTP_STATUS, HTTP_STATUS.name(), message);
    }

    public BadRequestException(String code, String message) {
        super(HTTP_STATUS, code, message);
    }
}
