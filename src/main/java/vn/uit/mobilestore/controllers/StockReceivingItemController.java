package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.StockReceivingItem;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.StockReceivingOrderModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.StockReceivingItemService;
import vn.uit.mobilestore.models.StockReceivingItemModel;

import javax.validation.Valid;


@RestController
@RequestMapping(URL.STOCK_RECEIVING_ITEM)
public class StockReceivingItemController extends AbstractController<StockReceivingItemService, StockReceivingItem> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final StockReceivingItemService stockReceivingItemService;

    // StockReceivingItemController Constructor
    @Autowired
    public StockReceivingItemController(StockReceivingItemService stockReceivingItemService) {
        this.stockReceivingItemService = stockReceivingItemService;
    }


    // GET stockReceivingItems
    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<StockReceivingItem>> getStockReceivingItemList(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        return this.listAll(size, page, LOG, this.stockReceivingItemService);
    }

    // Get stockReceivingOrders by Id
    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<StockReceivingItem> getStockReceivingItemById(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<StockReceivingItem> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getStockReceivingItemById ");
            // Get stockReceivingItem by Id
            StockReceivingItem stockReceivingItem = stockReceivingItemService.getById(id);
            response.setData(stockReceivingItem);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " getStockReceivingItemById : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getStockReceivingItemById ");
        }
    }

    // POST stockReceivingItem (Add stockReceivingItem)
    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<StockReceivingItem> saveStockReceivingItem(@RequestBody @Valid StockReceivingItemModel stockReceivingItemModel) {
        ResponseModel<StockReceivingItem> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveStockReceivingItem ");
            //Save StockReceivingOder
            StockReceivingItem stockReceivingOrder = stockReceivingItemService.saveStockReceivingItem(stockReceivingItemModel.toEntity());
            response.setData(stockReceivingOrder);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " saveStockReceivingItem : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveStockReceivingItem ");
        }
    }

    // Update stockReceivingOrder by Id
    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<StockReceivingItem> updateStockReceivingItemById(@PathVariable(value = Const.PATH_ID) Integer id,
                                                                          @RequestBody @Valid StockReceivingItemModel stockReceivingItemModel) {
        ResponseModel<StockReceivingItem> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateStockReceivingItemById ");
            //Update StockReceivingItem
            StockReceivingItem stockReceivingItem = stockReceivingItemService.updateById(id, stockReceivingItemModel);
            response.setData(stockReceivingItem);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " updateStockReceivingItemById : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " updateStockReceivingItemById ");
        }
    }

    // Delete stockReceivingItem by Id
    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<String> deleteStockReceivingItem(@PathVariable(value = Const.PATH_ID) Integer id) {
        return this.deleteOne(id, LOG, stockReceivingItemService);
    }

    // GET StockReceivingItem List by Id
    @RequestMapping(value = URL.GET_STOCK_RECEIVING_ITEM_BY_ORDER_ID + URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<StockReceivingItem>> getStockReceivingItemList(
            @PathVariable(value = Const.PATH_ID) Integer id,
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page
    )
    {
        ResponseModel<Page<StockReceivingItem>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getListStockReceivingItemByOrderId [page={}],[size = {}] ", page, size);
            //List all
            response.setData(stockReceivingItemService.listStockReceivingItemByOrderId(id, page, size));
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
