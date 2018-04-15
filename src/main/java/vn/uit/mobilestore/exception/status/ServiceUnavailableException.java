package vn.uit.mobilestore.exception.status;

import org.springframework.http.HttpStatus;
import vn.uit.mobilestore.exception.RestException;

public class ServiceUnavailableException extends RestException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.SERVICE_UNAVAILABLE;

    public ServiceUnavailableException() {
        super(HTTP_STATUS);
    }

    public ServiceUnavailableException(String message) {
        super(HTTP_STATUS, HTTP_STATUS.name(), message);
    }

    public ServiceUnavailableException(String code, String message) {
        super(HTTP_STATUS, code, message);
    }
}
