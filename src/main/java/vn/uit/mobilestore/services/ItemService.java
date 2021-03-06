package vn.uit.mobilestore.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vn.uit.mobilestore.constants.ItemStatus;
import vn.uit.mobilestore.constants.MessageCode;

import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.StockReceivingItem;
import vn.uit.mobilestore.entities.Variant;

import vn.uit.mobilestore.exceptions.ApplicationException;

import vn.uit.mobilestore.models.ItemModel;
import vn.uit.mobilestore.models.BindingModel.StockReceiving.ItemBindingModel;

import vn.uit.mobilestore.repositories.ItemRepository;
import vn.uit.mobilestore.repositories.StockReceivingItemRepository;
import vn.uit.mobilestore.repositories.VariantRepository;


@Service
public class ItemService extends BaseService<ItemRepository, Item, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    ItemService(ItemRepository repository) {
        super(repository);

    }

    @Autowired
    VariantRepository variantRepository;

    @Autowired
    StockReceivingItemRepository stockReceivingItemRepository;

//    @Autowired
//    StockReceivingItemService stockReceivingItemService;

    public Item updateItem(Integer id, ItemModel itemModel) {
        // Find item
        Item item = this.getById(id);
        //Validate variantID and StockReceivingItem
        Variant variant = variantRepository.findOne(itemModel.getVariantId());
        if (variant == null) {
            throw new ApplicationException(MessageCode.ERROR_VARIANT_ID_NOT_FOUND);
        }
        if (itemModel.getStockReceivingItemId() != null) {
            StockReceivingItem stockReceivingItem =
                    stockReceivingItemRepository.findOne(itemModel.getStockReceivingItemId());
            if (stockReceivingItem == null){
                throw new ApplicationException(MessageCode.ERROR_STOCKRECEIVINGITEM_ID_NOT_FOUND);
            }
        }
        // Update
        item = item.updateItem(itemModel);
        this.updateData(item);
        return item;
    }

    public Item saveItem(Item item) {
        Variant variant = variantRepository.findOne(item.getVariantId());
        if (variant == null) {
            throw new ApplicationException(MessageCode.ERROR_VARIANT_ID_NOT_FOUND);
        }
        if (item.getStockReceivingItemId() != null) {
            StockReceivingItem stockReceivingItem = stockReceivingItemRepository.findOne(item.getStockReceivingItemId());
            if (stockReceivingItem == null){
                throw new ApplicationException(MessageCode.ERROR_STOCKRECEIVINGITEM_ID_NOT_FOUND);
            }
        }
        return this.saveData(item);
    }

    //CRUD method is provided by Base Service. Add another method as needed

    // Begin StockReceivingOrder feature methods
    public List<Item> saveItemList(List<ItemBindingModel> itemBindingModelList, Integer stockReceivingItemID) {
        List<Item> itemList = new ArrayList<>();

        for (int index = 0; index < itemBindingModelList.size(); index++) {
            // Get each ItemBindingModel
            ItemBindingModel itemBindingModel = itemBindingModelList.get(index);

            // Save each ItemBindingModel
            Item item = this.saveItemBindingModelInfo(itemBindingModel.toEntity(stockReceivingItemID));

            // Add item into itemList
            itemList.add(item);
        }

        return itemList;
    }

    public Item saveItemBindingModelInfo(Item item) {
        // Save each ItemBindingModel
        Item itemObject = this.saveItem(item);

        return itemObject;
    }
    // End StockReceivingOrder feature methods

    // Get Item List by StockReceivingItemId
    public StockReceivingItem GetStockReceivingItemById (Integer stockReceivingItemId) {
        StockReceivingItem stockReceivingItem = this.stockReceivingItemRepository.findOne(stockReceivingItemId);
        if (stockReceivingItem == null) {
            throw new ApplicationException(MessageCode.ERROR_NOT_FOUND);
        }
        return stockReceivingItem;
    }

    public Page<Item> listItemByStockReceivingItemId(Integer stockReceivingItemId, Integer page, Integer size) {
        this.GetStockReceivingItemById(stockReceivingItemId);
        PageRequest pageRequest = new PageRequest(page, size);
        System.out.println("Test");
        Page<Item> itemPage = this.stockReceivingItemRepository.listItemByStockReceivingItemId(stockReceivingItemId, pageRequest);
        System.out.println("End of test");
        return itemPage;
    }

    // OrderBill Feature
    // Update SOLD status
    public Item updateSoldStatus(Integer id) {
        Item item = this.getById(id);
        item.setStatus(ItemStatus.SOLD);

        this.updateData(item);
        return item;
    }

    public Item updateSoldStatus(Item item) {
        item.setStatus(ItemStatus.SOLD);

        this.updateData(item);
        return item;
    }

    // Update Items SOLD status
    public List<Item> updateItemsSoldStatus(List<Item> itemList, Integer countNumber) {
        List<Item> updatedItemList = new ArrayList<>();
        for (int i = 0; i < countNumber; i++) {
            Item item = this.updateSoldStatus(itemList.get(i));

            updatedItemList.add(item);
        }
        return updatedItemList;
    }
}
