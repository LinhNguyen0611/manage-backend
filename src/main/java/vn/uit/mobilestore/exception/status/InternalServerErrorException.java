package vn.uit.mobilestore.exception.status;

import org.springframework.http.HttpStatus;
import vn.uit.mobilestore.exception.RestException;

public class InternalServerErrorException extends RestException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerErrorException() {
        super(HTTP_STATUS);
    }

    public InternalServerErrorException(String message) {
        super(HTTP_STATUS, HTTP_STATUS.name(), message);
    }

    public InternalServerErrorException(String code, String message) {
        super(HTTP_STATUS, code, message);
    }
}
