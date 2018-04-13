package vn.uit.mobilestore.services;

import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.models.ItemModel;
import vn.uit.mobilestore.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Page<Item> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Item> items = findAll(pageRequest);
        return items;
    }

    //CRUD method is provided by Base Service. Add another method as needed
}
