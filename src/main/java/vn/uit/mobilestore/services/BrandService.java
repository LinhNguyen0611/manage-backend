package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.repositories.BrandRepository;
import vn.uit.mobilestore.repositories.ItemRepository;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Service
public class BrandService extends BaseService<BrandRepository,Brand, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    BrandService(BrandRepository repository) {
        super(repository);
    }
}
