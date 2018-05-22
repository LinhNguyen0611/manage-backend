package vn.uit.mobilestore.controllers;

import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.ItemModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The class Item controller.
 */
@RestController
@RequestMapping(URL.PRODUCT_CONTROLLER)
public class ItemController extends AbstractController<ItemService, Item> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<Item> saveItem(@RequestBody @Valid ItemModel itemModel) {
        ResponseModel<Item> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveItem ");
            //Save item
            Item item = itemService.saveItem(itemModel.toEntity());
            response.setData(item);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " saveItem : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveItem ");
        }
    }

    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<Item> getItem(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Item> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getItem ");
            //Get item
            Item item = itemService.getById(id);
            response.setData(item);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getItem : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getItem ");
        }
    }

    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<Item> updateItem(@PathVariable(value = Const.PATH_ID) Integer id,
                                          @RequestBody @Valid ItemModel itemModel) {
        ResponseModel<Item> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateItem ");
            //Update ite
            Item item = itemService.updateItem(id, itemModel);
            response.setData(item);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " updateItem : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " updateItem ");
        }
    }

    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<Item>> listAll(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        return this.listAll(size, page, LOG, itemService);
    }

    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<String> deleteItem(@PathVariable(value = Const.PATH_ID) Integer id) {
        return this.deleteOne(id, LOG, itemService);
    }

    // GET StockReceivingItem List by Id
    @RequestMapping(value = URL.GET_ITEM_BY_STOCK_RECEIVING_ITEM_ID + URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<Item>> getStockReceivingItemList(
            @PathVariable(value = Const.PATH_ID) Integer id,
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page
    )
    {
        ResponseModel<Page<Item>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getListStockReceivingItemByOrderId [page={}],[size = {}] ", page, size);
            //List all
            response.setData(itemService.listItemByStockReceivingItemId(id, page, size));
            return response;
        } catch (ApplicationException ex) {
            LOG.error(Const.LOGGING_ERROR + "getListStockReceivingItemByOrderId: {}", ex.getMessage());
            response.buildError(ex);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getListStockReceivingItemByOrderId ");
        }

    }
}
