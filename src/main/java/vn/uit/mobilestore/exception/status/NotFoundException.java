package vn.uit.mobilestore.exception.status;

import org.springframework.http.HttpStatus;
import vn.uit.mobilestore.exception.RestException;

public class NotFoundException extends RestException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super(HTTP_STATUS);
    }

    public NotFoundException(String message) {
        super(HTTP_STATUS, HTTP_STATUS.name(), message);
    }

    public NotFoundException(String code, String message) {
        super(HTTP_STATUS, code, message);
    }
}
