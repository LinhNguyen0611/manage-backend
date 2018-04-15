package vn.uit.mobilestore.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {

    protected HttpStatus httpStatus;

    protected String code;

    protected String message;

    public RestException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public RestException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR.name(), message);
    }

    public RestException(HttpStatus httpStatus) {
        this(httpStatus, httpStatus.name(), httpStatus.getReasonPhrase());
    }

    public RestException(String code, String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, code.toUpperCase(), message);
    }

    public RestException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code.toUpperCase();
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
