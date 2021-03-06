package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

import vn.uit.mobilestore.constants.MessageCode;

import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.StockReceivingItem;
import vn.uit.mobilestore.entities.StockReceivingOrder;

import vn.uit.mobilestore.exceptions.ApplicationException;

import vn.uit.mobilestore.models.BindingModel.StockReceiving.StockReceivingItemBindingModel;
import vn.uit.mobilestore.models.StockReceivingItemModel;

import vn.uit.mobilestore.repositories.StockReceivingItemRepository;
import vn.uit.mobilestore.repositories.StockReceivingOrderRepository;

@Service
public class StockReceivingItemService extends BaseService<StockReceivingItemRepository, StockReceivingItem, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */

    // Add supplierRepository to check
    @Autowired
    StockReceivingOrderRepository stockReceivingOrderRepository;

    @Autowired
    ItemService itemService;

//    @Autowired
//    StockReceivingOrderService stockReceivingOrderService;

    @Autowired
    StockReceivingItemService(ItemService itemService,
                              StockReceivingItemRepository repository
    ) {
        super(repository);

        this.itemService = itemService;
    }

    public Page<StockReceivingItem> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<StockReceivingItem> stockReceivingItems = findAll(pageRequest);
        return stockReceivingItems;
    }

    //CRUD method is provided by Base Service. Add another method as needed

    // Extra methods
    private StockReceivingOrder checkstockReceivingOrderValid(Integer stockReceivingOrderId) {
        StockReceivingOrder stockReceivingOrder = stockReceivingOrderRepository.findOne(stockReceivingOrderId);
        // Check if exists
        if (stockReceivingOrder == null) {
            throw new ApplicationException(MessageCode.ERROR_STOCK_RECEIVING_ORDER_ID_NOT_FOUND);
        }
        return stockReceivingOrder;
    }

    // Update stockReceivingItem by Id
    public StockReceivingItem updateById (Integer stockReceivingItemId, StockReceivingItemModel stockReceivingItemModel) {
        // get stockReceivingOrder object and valid it
        StockReceivingItem stockReceivingItem = repository.findOne(stockReceivingItemId);
        if (stockReceivingItem == null) {
            throw new ApplicationException(MessageCode.ERROR_STOCK_RECEIVING_ITEM_ID_NOT_FOUND);
        }
        this.checkstockReceivingOrderValid(stockReceivingItemModel.getStockReceivingOrderID());

        // update stockReceivingOrder object
        stockReceivingItem = stockReceivingItem.updateStockReceivingItem(stockReceivingItemModel);

        // Update to database
        this.updateData(stockReceivingItem);

        // return function
        return stockReceivingItem;
    }

    // Add stockReceivingItem
    public StockReceivingItem saveStockReceivingItem (StockReceivingItem stockReceivingItem) {
        // Check is valid
        this.checkstockReceivingOrderValid(stockReceivingItem.getStockReceivingOrderID());
        // Save and return object
        return this.saveData(stockReceivingItem);
    }

    // Begin StockReceivingOrder feature methods
    // Add stockReceivingItem List
    public List<StockReceivingItem> saveStockReceivingItemList (
            List<StockReceivingItemBindingModel> stockReceivingItemBindingModelList,
            Integer stockReceivingOrderID
    ) {
        // Create an abstract stockReceivingItemList
        List<StockReceivingItem> stockReceivingItemList = new ArrayList<>();

        for (int index = 0; index < stockReceivingItemBindingModelList.size(); index++) {
            // Get Element in List StockReceivingItemBindingModel
            StockReceivingItemBindingModel stockReceivingItemBindingModel = stockReceivingItemBindingModelList.get(index);

            // Save stockReceivingItem data and return the saved object
            StockReceivingItem stockReceivingItem = this.saveStockReceivingItemInfo(stockReceivingItemBindingModel.toEntity(stockReceivingOrderID), stockReceivingItemBindingModel);

            // Add into stockReceivingItemList
            stockReceivingItemList.add(stockReceivingItem);
        }

        return stockReceivingItemList;
    }

    public StockReceivingItem saveStockReceivingItemInfo(
            StockReceivingItem stockReceivingItem,
            StockReceivingItemBindingModel stockReceivingItemBindingModel
    ) {
        // Save stockReceivingItem data and return the saved object
        StockReceivingItem stockReceivingItemObject = this.saveStockReceivingItem(stockReceivingItem);
        List<Item> itemList = itemService.saveItemList(
                stockReceivingItemBindingModel.getItemList(),
                stockReceivingItemObject.getStockReceivingItemID()
        );

        return stockReceivingItemObject;
    }

    // End StockReceivingOrder feature methods

    // Get StockReceivingItem List by StockReceivingOrderId
    public Page<StockReceivingItem> listStockReceivingItemByOrderId(Integer orderId, Integer page, Integer size) {
        this.checkstockReceivingOrderValid(orderId);
        PageRequest pageRequest = new PageRequest(page, size);

        return this.stockReceivingOrderRepository.listStockReceivingItemByOrderId(orderId, pageRequest);
    }
}
