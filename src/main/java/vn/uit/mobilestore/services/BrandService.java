package vn.uit.mobilestore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.models.BrandModel;
import vn.uit.mobilestore.repositories.BrandRepository;
import vn.uit.mobilestore.repositories.ModelRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Service
public class BrandService extends BaseService<BrandRepository,Brand, Integer> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final ModelService modelService;
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    BrandService(BrandRepository repository, ModelService modelService) {
        super(repository);
        this.modelService = modelService;
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

    @Override
    @Transactional
    public void deleteOne(Integer id) {
        LOG.info(Const.LOGGING_SERVICE_BEGIN + " deleteOne  {}", id);
        Brand entity = this.getById(id);
        // Delete child
        List<Integer> ids = new ArrayList<>();
        for (Model model : entity.getModelList()) {
            ids.add(model.getModelID());
        }
        ids.forEach(modelService::deleteOne);
        entity.setActive(false);
        this.saveData(entity);
    }
}
