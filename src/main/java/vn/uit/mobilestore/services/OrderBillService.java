package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import vn.uit.mobilestore.constants.MessageCode;

import vn.uit.mobilestore.entities.OrderBill;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.entities.OrderDetail;

import vn.uit.mobilestore.repositories.OrderBillRepository;
import vn.uit.mobilestore.repositories.UserRepository;

import vn.uit.mobilestore.models.OrderBillModel;

import vn.uit.mobilestore.exceptions.ApplicationException;

@Service
public class OrderBillService extends BaseService<OrderBillRepository, OrderBill, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderBillService(OrderBillRepository repository) {
        super(repository);
    }

    // Extra methods
    private User checkUserValid(Integer userID) {
        User user = userRepository.findOne(userID);
        // Check if exists
        if (user == null) {
            throw new ApplicationException(MessageCode.ERROR_USER_ID_NOT_FOUND);
        }
        return user;
    }

    // get list orderBills
    public Page<OrderBill> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<OrderBill> orderBill = findAll(pageRequest);
        return orderBill;
    }

    // Add orderBill
    public OrderBill saveOrderBill (OrderBill orderBill) {
        // Check is valid
        this.checkUserValid(orderBill.getUserID());
        // Save and return object
        return this.saveData(orderBill);
    }

    // Update orderBill by Id
    public OrderBill updateById (Integer orderBillId, OrderBillModel orderBillModel) {
        // get stockReceivingOrder object and valid it
        OrderBill orderBill = repository.findOne(orderBillId);
        if (orderBill == null) {
            throw new ApplicationException(MessageCode.ERROR_ORDER_BILL_ID_NOT_FOUND);
        }
        this.checkUserValid(orderBillModel.getUserID());

        // update stockReceivingOrder object
        orderBill = orderBill.updateOrderBill(orderBillModel);

        // Update to database
        this.updateData(orderBill);

        // return function
        return orderBill;
    }

    // Update Total by id
    public OrderBill updateTotalById(Integer orderBillID, Long total) {
        OrderBill orderBill = this.repository.findOne(orderBillID);

        // Update again total
        orderBill.setTotal(total);

        // Update to database
        this.updateData(orderBill);

        return orderBill;
    }
}
