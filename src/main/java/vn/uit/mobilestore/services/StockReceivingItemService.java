package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.StockReceivingItem;
import vn.uit.mobilestore.repositories.StockReceivingItemRepository;

@Service
public class StockReceivingItemService extends BaseService<StockReceivingItemRepository, StockReceivingItem, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    StockReceivingItemService(StockReceivingItemRepository repository) {
        super(repository);
    }

    public Page<StockReceivingItem> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<StockReceivingItem> stockReceivingItems = findAll(pageRequest);
        return stockReceivingItems;
    }

    //CRUD method is provided by Base Service. Add another method as needed
}
