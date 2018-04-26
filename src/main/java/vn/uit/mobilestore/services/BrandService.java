package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.models.BrandModel;
import vn.uit.mobilestore.repositories.BrandRepository;

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

    public Brand updateBrand(Integer id, BrandModel brandModel) {
        // Find item
        Brand brand = repository.findOne(id);
        // Update
        brand = brand.updateBrand(brandModel);
        this.updateData(brand);
        return brand;
    }

    public Page<Model> listModelByBrandId(Integer id, Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);

        return repository.listModelByBrandId(id, pageRequest);
    }
}
