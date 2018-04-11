package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.StockReceivingOrder;
import vn.uit.mobilestore.repositories.StockReceivingOrderRepository;

@Service
public class StockReceivingOrderService extends BaseService<StockReceivingOrderRepository, StockReceivingOrder, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    StockReceivingOrderService(StockReceivingOrderRepository repository) {
        super(repository);
    }

    public Page<StockReceivingOrder> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<StockReceivingOrder> stockReceivingOrders = findAll(pageRequest);
        return stockReceivingOrders;
    }

    //CRUD method is provided by Base Service. Add another method as needed
}
