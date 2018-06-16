package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.OrderBill;
import vn.uit.mobilestore.entities.OrderDetail;

import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.entities.Item;

import vn.uit.mobilestore.exceptions.ApplicationException;

import vn.uit.mobilestore.repositories.ItemRepository;
import vn.uit.mobilestore.repositories.OrderBillRepository;
import vn.uit.mobilestore.repositories.OrderDetailRepository;

import vn.uit.mobilestore.models.OrderDetailModel;

import java.util.List;

@Service
public class OrderDetailService extends BaseService<OrderDetailRepository, OrderDetail, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderBillRepository orderBillRepository;

    @Autowired
    OrderDetailService(OrderDetailRepository repository) {
        super(repository);

    }

    // Extra methods
    private OrderBill checkOrderBillValid(Integer orderBillID) {
        OrderBill user = this.orderBillRepository.findOne(orderBillID);
        // Check if exists
        if (user == null) {
            throw new ApplicationException(MessageCode.ERROR_ORDER_BILL_ID_NOT_FOUND);
        }
        return user;
    }

    private Item checkItemValid(Integer itemID) {
        Item item = this.itemRepository.findOne(itemID);
        // Check if exists
        if (item == null) {
            throw new ApplicationException(MessageCode.ERROR_ITEM_ID_NOT_FOUND);
        }
        return item;
    }

    // get list orderDetails
    public Page<OrderDetail> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<OrderDetail> orderDetail = findAll(pageRequest);
        return orderDetail;
    }

    // Add orderDetail
    public OrderDetail saveOrderDetail (OrderDetail orderDetail) {
        // Check is valid
        this.checkOrderBillValid(orderDetail.getOrderBillID());
        this.checkItemValid(orderDetail.getItemID());

        // Save and return object
        return this.saveData(orderDetail);
    }

    // Update orderBill by Id
    public OrderDetail updateById (Integer orderBillId, OrderDetailModel orderDetailModel) {
        // get stockReceivingOrder object and valid it
        OrderDetail orderDetail = repository.findOne(orderBillId);
        if (orderDetail == null) {
            throw new ApplicationException(MessageCode.ERROR_ORDER_BILL_ID_NOT_FOUND);
        }

        // Check Item and OrderBill ID
        this.checkItemValid(orderDetailModel.getItemID());
        this.checkOrderBillValid(orderDetailModel.getOrderBillID());

        // update stockReceivingOrder object
        orderDetail = orderDetail.updateOrderDetail(orderDetailModel);

        // Update to database
        this.updateData(orderDetail);

        // return function
        return orderDetail;
    }

    // Get by orderBillID
    public List<OrderDetail> getByOrderBillId(Integer orderBillId) {
        this.checkOrderBillValid(orderBillId);

        List<OrderDetail> orderDetailList = this.repository.getOrderDetailByOrderBillID(orderBillId);

        return orderDetailList;
    }
}
