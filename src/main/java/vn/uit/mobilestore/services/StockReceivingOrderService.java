package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.StockReceivingOrder;
import vn.uit.mobilestore.entities.Supplier;
import vn.uit.mobilestore.models.StockReceivingOrderModel;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.repositories.StockReceivingOrderRepository;
import vn.uit.mobilestore.repositories.SupplierRepository;

@Service
public class StockReceivingOrderService extends BaseService<StockReceivingOrderRepository, StockReceivingOrder, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */

    // Add supplierRepository to check
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    StockReceivingOrderService(StockReceivingOrderRepository repository) {
        super(repository);
    }
    //CRUD method is provided by Base Service. Add another method as needed

    // Extra methods
    private Supplier checkSupplierValid(Integer supplierId) {
        Supplier supplier = supplierRepository.findOne(supplierId);
        // Check if exists
        if (supplier == null) {
            throw new ApplicationException(MessageCode.ERROR_SUPPLIER_ID_NOT_FOUND);
        }
        return supplier;
    }

    // get list stockReceivingOrder
    public Page<StockReceivingOrder> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<StockReceivingOrder> stockReceivingOrders = findAll(pageRequest);
        return stockReceivingOrders;
    }

    // Update stockReceivingOrder by Id
    public StockReceivingOrder updateById (Integer stockReceivingOrderId, StockReceivingOrderModel stockReceivingOrderModel) {
        // get stockReceivingOrder object and valid it
        StockReceivingOrder stockReceivingOrder = repository.findOne(stockReceivingOrderId);
        if (stockReceivingOrder == null) {
            throw new ApplicationException(MessageCode.ERROR_STOCK_RECEIVING_ORDER_ID_NOT_FOUND);
        }
        this.checkSupplierValid(stockReceivingOrder.getSupplierID());

        // update stockReceivingOrder object
        stockReceivingOrder = stockReceivingOrder.updateStockReceivingOrder(stockReceivingOrderModel);

        // Update to database
        this.updateData(stockReceivingOrder);

        // return function
        return stockReceivingOrder;
    }

    // Add stockReceivingOrder
    public StockReceivingOrder saveStockReceivingOrder (StockReceivingOrder stockReceivingOrder) {
        // Check is valid
        this.checkSupplierValid(stockReceivingOrder.getSupplierID());
        // Save and return object
        return this.saveData(stockReceivingOrder);
    }

}
