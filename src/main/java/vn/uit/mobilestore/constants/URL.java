package vn.uit.mobilestore.constants;

/**
 * The interface Url.
 */
public interface URL {
    /**
     * Internal URL
     */
    String ADD_ACTION = "/add";
    /**
     * The constant UPDATE_ACTION.
     */
    String UPDATE_ACTION = "/update/{id}";
    /**
     * The constant DELETE_ACTION.
     */
    String DELETE_ACTION = "/delete/{id}";
    /**
     * The constant GET_ACTION.
     */
    String GET_ACTION = "/get/{id}";
    /**
     * The constant LIST_PAGING.
     */
    String LIST_PAGING = "/list/{size}/{page}";


    /**
     * The constant PRODUCT_CONTROLLER.
     */
    String PRODUCT_CONTROLLER= "item";

    String MODEL_CONTROLLER = "model";

    String BRAND_CONTROLLER ="brand";

    String VARIANT_CONTROLLER ="variant";

    String USER_CONTROLLER = "users";

    String ROLE_CONTROLLER = "role";

    String SUPPLIER_CONTROLLER = "supplier";

    String STOCK_RECEIVING_ORDER = "stockReceivingOrder";

    String STOCK_RECEIVING_ITEM = "stockReceivingItem";

    /**
     * The constant for Extra STOCK_RECEIVING_ORDER_CONTROLLER.
     */
    String STOCK_RECEIVING_ORDER_INFO = "/stockReceivingOrderInfo";

}

