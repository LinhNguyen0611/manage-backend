package vn.uit.mobilestore.services;

import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.StockReceivingItem;
import vn.uit.mobilestore.entities.Variant;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.ItemModel;
import vn.uit.mobilestore.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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

    public Page<Item> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Item> items = findAll(pageRequest);
        return items;
    }


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
}
