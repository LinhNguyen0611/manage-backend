package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;

import vn.uit.mobilestore.entities.StockReceivingOrder;

import vn.uit.mobilestore.exceptions.ApplicationException;

import vn.uit.mobilestore.models.BidingModel.StockReceiving.StockReceivingOrderBindingModel;
import vn.uit.mobilestore.models.StockReceivingOrderModel;

import vn.uit.mobilestore.responses.ResponseModel;

import vn.uit.mobilestore.services.StockReceivingOrderService;
import vn.uit.mobilestore.services.SupplierService;


@RestController
@RequestMapping(URL.STOCK_RECEIVING_ORDER)
public class StockReceivingOrderController extends AbstractController<StockReceivingOrderService, StockReceivingOrder> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final StockReceivingOrderService stockReceivingOrderService;
    private final SupplierService supplierService;


    // StockReceivingOrderController Constructor
    @Autowired
    public StockReceivingOrderController(StockReceivingOrderService stockReceivingOrderService, SupplierService supplierService) {
        this.stockReceivingOrderService = stockReceivingOrderService;
        this.supplierService = supplierService;
    }

    // GET stockReceivingOrders
    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<StockReceivingOrder>> getStockReceivingOrderList(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        return this.listAll(size, page, LOG, this.stockReceivingOrderService);
    }


    // Get stockReceivingOrders by Id
    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<StockReceivingOrder> getStockReceivingOrderById(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<StockReceivingOrder> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getStockReceivingOder ");
            //Get supplier by Id
            StockReceivingOrder stockReceivingOrder = stockReceivingOrderService.getById(id);
            response.setData(stockReceivingOrder);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " getStockReceivingOder : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getStockReceivingOder ");
        }
    }

    // Add stockReceivingOrder
    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<StockReceivingOrder> saveStockReceivingOrder(@RequestBody @Valid StockReceivingOrderModel stockReceivingOrderModel) {
        ResponseModel<StockReceivingOrder> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveStockReceivingOder ");
            //Save StockReceivingOder
            StockReceivingOrder stockReceivingOrder = stockReceivingOrderService.saveStockReceivingOrder(stockReceivingOrderModel.toEntity());
            response.setData(stockReceivingOrder);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " saveStockReceivingOder : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveStockReceivingOder ");
        }
    }

    // Update stockReceivingOrder by Id
    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<StockReceivingOrder> updateStockReceivingOrderById(@PathVariable(value = Const.PATH_ID) Integer id,
                                                      @RequestBody @Valid StockReceivingOrderModel stockReceivingOrderModel) {
        ResponseModel<StockReceivingOrder> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateStockReceivingOrder ");
            //Update Supplier
            StockReceivingOrder stockReceivingOrder = stockReceivingOrderService.updateById(id, stockReceivingOrderModel);
            response.setData(stockReceivingOrder);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " updateStockReceivingOrder : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " updateStockReceivingOrder ");
        }
    }

    // Delete stockReceivingOrder by Id
    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<String> deleteStockReceivingOrder(@PathVariable(value = Const.PATH_ID) Integer id) {
        return this.deleteOne(id, LOG, stockReceivingOrderService);
    }

    // Extra function
    // Add stockReceivingOrder with all info
    @RequestMapping(value = URL.STOCK_RECEIVING_ORDER_INFO + URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<StockReceivingOrderBindingModel> saveStockReceivingOrderInfo(@RequestBody @Valid StockReceivingOrderBindingModel stockReceivingOrderBindingModel) {
        ResponseModel<StockReceivingOrderBindingModel> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveStockReceivingOrderInfo ");
            // Save StockReceivingOder
            StockReceivingOrder stockReceivingOrder = stockReceivingOrderService.parseStockReceivingOrderAllInfo(stockReceivingOrderBindingModel);
            response.setData(stockReceivingOrderBindingModel);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " saveStockReceivingOrderInfo : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveStockReceivingOrderInfo ");
        }
    }

}
