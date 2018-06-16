package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import javax.validation.Valid;

import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.ItemStatus;
import vn.uit.mobilestore.constants.URL;

import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.OrderBill;
import vn.uit.mobilestore.entities.OrderDetail;
import vn.uit.mobilestore.entities.User;

import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.BindingModel.OrderBill.OrderDetailBindingModel;
import vn.uit.mobilestore.repositories.ItemRepository;
import vn.uit.mobilestore.responses.ResponseModel;

import vn.uit.mobilestore.repositories.UserRepository;
import vn.uit.mobilestore.repositories.ItemRepository;

import vn.uit.mobilestore.models.OrderBillModel;
import vn.uit.mobilestore.models.BindingModel.OrderBill.OrderBillBindingModel;

import vn.uit.mobilestore.services.ItemService;
import vn.uit.mobilestore.services.OrderBillService;
import vn.uit.mobilestore.services.VariantService;
import vn.uit.mobilestore.services.OrderDetailService;

@RestController
@RequestMapping(URL.ORDER_BILL)
public class OrderBillController extends AbstractController<OrderBillService, OrderBill> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final OrderBillService orderBillService;

    private final VariantService variantService;

    private final ItemService itemService;

    private final OrderDetailService orderDetailService;

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    // OrderBillController Constructor
    @Autowired
    public OrderBillController(OrderBillService orderBillService,
                               VariantService variantService,
                               ItemService itemService,
                               OrderDetailService orderDetailService,
                               UserRepository userRepository,
                               ItemRepository itemRepository) {
        this.orderBillService = orderBillService;
        this.variantService = variantService;
        this.itemService = itemService;
        this.orderDetailService = orderDetailService;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    // Support function
    private User getUserByUsername(Object principal) {
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = this.userRepository.getUserByName(username);

        return user;
    }

    // GET OrderBills
    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<OrderBill>> getOrderBillList(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        return this.listAll(size, page, LOG, this.orderBillService);
    }

    // Get OrderBill by Id
    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<OrderBill> getOrderBillById(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<OrderBill> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getOrderBillById ");
            //Get supplier by Id
            OrderBill orderBill = this.orderBillService.getById(id);
            response.setData(orderBill);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " getOrderBillById : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getOrderBillById ");
        }
    }

    // Add OrderBill
    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<OrderBill> saveOrderBill(@RequestBody @Valid OrderBillModel orderBillModel) {
        ResponseModel<OrderBill> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveOrderBill ");
            //Save StockReceivingOder
            OrderBill orderBill = this.orderBillService.saveOrderBill(orderBillModel.toEntity());
            response.setData(orderBill);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " saveOrderBill : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveOrderBill ");
        }
    }

    // Update OrderBill by Id
    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<OrderBill> updateOrderBillById(@PathVariable(value = Const.PATH_ID) Integer id,
                                                                            @RequestBody @Valid OrderBillModel orderBillModel) {
        ResponseModel<OrderBill> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateOrderBillById ");
            //Update Supplier
            OrderBill orderBill = this.orderBillService.updateById(id, orderBillModel);
            response.setData(orderBill);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " updateOrderBillById : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " updateOrderBillById ");
        }
    }

    // Delete OrderBill by Id
    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<String> deleteOrderBill(@PathVariable(value = Const.PATH_ID) Integer id) {
        return this.deleteOne(id, LOG, this.orderBillService);
    }

    // Extra APIs
    // Add OrderBill with all info
    @RequestMapping(value = URL.STOCK_RECEIVING_ORDER_INFO + URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<OrderBill> saveOrderBillInfo(@RequestBody @Valid OrderBillBindingModel orderBillBindingModel) {
        Object principal = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        User user = this.getUserByUsername(principal);

        ResponseModel<OrderBill> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveOrderBillInfo");
            // Check number valid
            this.variantService.checkListInStockValid(orderBillBindingModel.getOrderDetailBindingModelList());
            // Save orderBill
            OrderBill orderBill = this.orderBillService.saveOrderBill(orderBillBindingModel.toEntity(user));
//            OrderBill orderBill = orderBillBindingModel.toEntity(user);

            List<OrderDetailBindingModel> orderDetailBindingModelList = orderBillBindingModel.getOrderDetailBindingModelList();
            long total = 0;

            for (int i = 0; i < orderDetailBindingModelList.size(); i++) {
                OrderDetailBindingModel orderDetailBindingModel = orderDetailBindingModelList.get(i);

                // Check valid number
                List<Item> itemList = this.variantService.checkInStockValid(orderDetailBindingModel.getVariantID(), orderDetailBindingModel.getCountNumber());

                // Change Item Status
                List<Item> updatedItemList = this.itemService.updateItemsSoldStatus(itemList, orderDetailBindingModel.getCountNumber());

                // Add OrderDetail
                for (int j = 0; j < updatedItemList.size(); j++) {
                    Item updatedItem = updatedItemList.get(j);
                    // Save OrderDetail
                    OrderDetail orderDetail = this.orderDetailService.saveOrderDetail(
                            OrderDetail.CreateNewOrderDetail(
                                    updatedItem,
                                    orderBill,
                                    orderDetailBindingModel.getPriceEachUnit()
                            )
                    );

                    // update again total
                    total += orderDetail.getPrice();
                }
            }
            // Update orderBill total
            OrderBill updatedOrderBill = this.orderBillService.updateTotalById(orderBill.getOrderBillID(), total);

            response.setData(updatedOrderBill);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " saveOrderBillInfo : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveOrderBillInfo ");
        }
    }
}
