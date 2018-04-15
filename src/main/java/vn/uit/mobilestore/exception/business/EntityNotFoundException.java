package vn.uit.mobilestore.exception.business;

import vn.uit.mobilestore.exception.status.NotFoundException;

public class EntityNotFoundException extends NotFoundException {

    private static final String CODE = "ENTITY_NOT_FOUND";

    public EntityNotFoundException() {
        super(CODE, "Entity not found");
    }

    public EntityNotFoundException(String message) {
        super(CODE, message);
    }
}