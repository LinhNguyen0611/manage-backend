package vn.uit.mobilestore.exceptions;


import vn.uit.mobilestore.constants.MessageCode;

/**
 * The type Application exceptions.
 */
public class ApplicationException extends RuntimeException {

    private Integer errorCode;
    private String errorMessage;

    /**
     * Default constructor
     */
    public ApplicationException() {
        super();
    }

    /**
     * Instantiates a new Application exceptions.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     */
    public ApplicationException(Integer errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Instantiates a new Application exceptions.
     *
     * @param errorMessage the error message
     */
    public ApplicationException(MessageCode errorMessage) {
        this(errorMessage, null);
        this.errorMessage = errorMessage.getReasonPhrase();
    }

    /**
     * Constructor
     *
     * @param errorMessage  the error message
     * @param extendMessage the extend message
     */
    public ApplicationException(MessageCode errorMessage, String extendMessage) {
        super(errorMessage.getReasonPhrase());
        this.errorCode = errorMessage.value();
        this.errorMessage = errorMessage.getReasonPhrase() + (extendMessage != null ? extendMessage : "");
    }

    /**
     * Constructor
     *
     * @param e the e
     */
    public ApplicationException(Exception e) {
        super(e);
        this.errorMessage = e.getMessage();
        this.errorCode = MessageCode.ERROR_OTHER.value();
    }

    /**
     * Constructor
     *
     * @param message the message
     */
    public ApplicationException(String message) {
        super(message);
        this.errorMessage = message;
        this.errorCode = MessageCode.ERROR_OTHER.value();
    }

    /**
     * Constructor
     *
     * @param message the message
     * @param cause   the cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
        this.errorCode = MessageCode.ERROR_OTHER.value();
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
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
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
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
