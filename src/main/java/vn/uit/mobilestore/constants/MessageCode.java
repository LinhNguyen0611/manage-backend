package vn.uit.mobilestore.constants;

/**
 * Created by TienLX on 12/29/2017.
 */
public enum MessageCode {
    //Wrong parameter
    ERROR_BAD_REQUEST(400, "Bad request"),
    //User or request object not found
    ERROR_NOT_FOUND(404, "Not found"),

    ERROR_MODEL_ID_NOT_FOUND(4041, "Model ID not found"),
    ERROR_BRAND_ID_NOT_FOUND(4042, "Brand ID not found"),
    ERROR_VARIANT_ID_NOT_FOUND(4043, "Variant ID not found"),
    ERROR_STOCKRECEIVINGITEM_ID_NOT_FOUND(4044, "StockReceivingItem ID not found"),

    // 60xx Supplier Error
    ERROR_SUPPLIER_ID_NOT_FOUND(6000, "Supplier Id not found"),

    // 61xx StockReceivingOrder Error
    ERROR_STOCK_RECEIVING_ORDER_ID_NOT_FOUND(6100, "Stock Receiving Order Id not found"),

    // 32xx StockReceivingItem Error
    ERROR_STOCK_RECEIVING_ITEM_ID_NOT_FOUND(6100, "Stock Receiving Item Id not found"),

    //Other error
    ERROR_OTHER(9999, "Unexpected error");

    private final int value;
    private final String reasonPhrase;

    MessageCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    /**
     * Return the integer value of this status code.
     *
     * @return the int
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     *
     * @return the reason phrase
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    /**
     * Return the enum constant of this type with the specified numeric value.
     *
     * @param statusCode the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
     */
    public static MessageCode valueOf(int statusCode) {
        for (MessageCode status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }
}
