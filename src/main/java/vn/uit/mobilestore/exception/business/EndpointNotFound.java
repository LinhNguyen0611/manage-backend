package vn.uit.mobilestore.exception.business;


import vn.uit.mobilestore.exception.status.NotFoundException;

public class EndpointNotFound extends NotFoundException {

    private static final String CODE = "ENDPOINT_NOT_FOUND";

    public EndpointNotFound() {
        super(CODE, "The endpoint not found");
    }

    public EndpointNotFound(String message) {
        super(CODE, message);
    }
}
