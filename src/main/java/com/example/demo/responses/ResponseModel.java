package com.example.demo.responses;

import com.example.demo.constants.MessageCode;
import com.example.demo.exceptions.ApplicationException;

/**
 * ResponseModel class for return to client
 *
 * @param <T> the type parameter
 */
public class ResponseModel<T> {
    private Boolean success;
    private T data;
    private Integer errorCode;
    private String errorMessage;

    /**
     * Default Controller
     */
    public ResponseModel() {
        super();
    }

    /**
     * Gets success.
     *
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.success = true;
        this.data = data;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Success.
     */
    public void success() {
        this.success = true;
    }


    /**
     * Build error response model.
     *
     * @param messageCode the message code
     * @return the response model
     */
    public ResponseModel buildError(MessageCode messageCode) {
        this.success = false;
        this.errorCode = messageCode.value();
        this.errorMessage = messageCode.getReasonPhrase();
        return this;
    }

    /**
     * Build error response model.
     *
     * @param ae the ae
     * @return the response model
     */
    public ResponseModel buildError(ApplicationException ae) {
        this.success = false;
        this.errorCode = ae.getErrorCode();
        this.errorMessage = ae.getErrorMessage();
        return this;
    }

    /**
     * Build error response model.
     *
     * @param errorCode the error code
     * @param reason    the reason
     * @return the response model
     */
    public ResponseModel buildError(Integer errorCode, String reason) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = reason;
        return this;
    }

}
