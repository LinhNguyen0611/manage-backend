package vn.uit.mobilestore.exception.business;

import vn.uit.mobilestore.exception.status.NotFoundException;

public class ResourceNotFoundException extends NotFoundException {

    private static final String CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException() {
        super(CODE, "Resource not found");
    }

    public ResourceNotFoundException(String message) {
        super(CODE, message);
    }
}