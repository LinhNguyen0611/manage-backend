package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;

import vn.uit.mobilestore.entities.OrderDetail;
import vn.uit.mobilestore.entities.Item;

import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.BindingModel.OrderBill.OrderDetailBindingModel;
import vn.uit.mobilestore.responses.ResponseModel;

import vn.uit.mobilestore.models.OrderDetailModel;

import vn.uit.mobilestore.services.OrderDetailService;
import vn.uit.mobilestore.services.ItemService;
import vn.uit.mobilestore.services.VariantService;

import java.util.List;

@RestController
@RequestMapping(URL.ORDER_DETAIL)
public class OrderDetailController extends AbstractController<OrderDetailService, OrderDetail> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final OrderDetailService orderDetailService;

    private final ItemService itemService;

    private final VariantService variantService;

    // OrderBillController Constructor
    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService, ItemService itemService, VariantService variantService) {
        this.orderDetailService = orderDetailService;
        this.itemService = itemService;
        this.variantService = variantService;
    }

    // GET OrderDetail
    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<OrderDetail>> getOrderDetailList(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        return this.listAll(size, page, LOG, this.orderDetailService);
    }

    // Get OrderDetail by Id
    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<OrderDetail> getOrderDetailById(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<OrderDetail> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getOrderDetailById ");
            //Get supplier by Id
            OrderDetail orderDetail = this.orderDetailService.getById(id);
            response.setData(orderDetail);

            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " getOrderDetailById : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getOrderDetailById ");
        }
    }

    // Add OrderDetail
    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<OrderDetail> saveOrderDetail(@RequestBody @Valid OrderDetailModel orderDetailModel) {
        ResponseModel<OrderDetail> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveOrderDetail ");

            //Save StockReceivingOder
            OrderDetail orderDetail = this.orderDetailService.saveOrderDetail(orderDetailModel.toEntity());

            // Update Sold status
            Item item = this.itemService.updateSoldStatus(orderDetail.getItemID());

            response.setData(orderDetail);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " saveOrderDetail : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveOrderDetail ");
        }
    }

    // Update OrderBill by Id
    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<OrderDetail> updateOrderDetailById(@PathVariable(value = Const.PATH_ID) Integer id,
                                                        @RequestBody @Valid OrderDetailModel orderDetailModel) {
        ResponseModel<OrderDetail> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateOrderDetailById ");
            //Update Supplier
            OrderDetail orderDetail = this.orderDetailService.updateById(id, orderDetailModel);
            response.setData(orderDetail);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " updateOrderDetailById : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " updateOrderDetailById ");
        }
    }

    // Delete OrderDetail by Id
    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<String> deleteOrderDetail(@PathVariable(value = Const.PATH_ID) Integer id) {
        return this.deleteOne(id, LOG, this.orderDetailService);
    }

    // Extra APIs
    // Get OrderDetail List by OrderBillID
    @RequestMapping(value = URL.GET_BY_ORDER_BILL_ID, method = RequestMethod.GET)
    public ResponseModel<List<OrderDetail>> getByOrderBillId(@PathVariable(value = Const.PATH_ID) Integer orderBillId) {
        ResponseModel<List<OrderDetail>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getByOrderBillId ");
            //Get supplier by Id
            List<OrderDetail> orderDetailList = this.orderDetailService.getByOrderBillId(orderBillId);

            response.setData(orderDetailList);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " getByOrderBillId : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getByOrderBillId ");
        }
    }

    // Check orderDetail valid countNumber
    @RequestMapping(value = URL.CHECK_ORDER_DETAIL_VALID, method = RequestMethod.POST)
    public ResponseModel<Boolean> checkOrderDetailValid(@RequestBody @Valid OrderDetailBindingModel orderDetailBindingModel) {
        ResponseModel<Boolean> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " checkOrderDetailValid ");
            // Check VariantID and countNumber valid
            this.variantService.checkInStockValid(
                    orderDetailBindingModel.getVariantID(),
                    orderDetailBindingModel.getCountNumber()
            );

            response.setData(true);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " checkOrderDetailValid : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " checkOrderDetailValid ");
        }
    }

}
