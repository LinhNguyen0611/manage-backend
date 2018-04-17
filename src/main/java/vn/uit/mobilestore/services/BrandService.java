package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.models.BrandModel;
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

    public Page<Brand> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Brand> brands = findAll(pageRequest);
        return brands;
    }

    public Brand updateBrand(Integer id, BrandModel brandModel) {
        // Find item
        Brand brand = repository.findOne(id);
        // Update
        brand = brand.updateBrand(brandModel);
        this.updateData(brand);
        return brand;
    }
}
