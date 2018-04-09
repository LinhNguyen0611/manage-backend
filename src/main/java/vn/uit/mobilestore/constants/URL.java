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

    String USER_CONTROLLER = "users";

    String ROLE_CONTROLLER = "role";
}
