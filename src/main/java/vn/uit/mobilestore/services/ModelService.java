package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.ModelModel;
import vn.uit.mobilestore.repositories.BrandRepository;
import vn.uit.mobilestore.repositories.ModelRepository;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@Service
public class ModelService extends BaseService <ModelRepository, Model, Integer> {


    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    ModelService(ModelRepository repository) {
        super(repository);
    }
    @Autowired
    BrandRepository brandRepository;

    public Model updateModel(Integer id, ModelModel modelModel) {
        // Find item
        Model model = this.getById(id);
        // Validate brandID
        Brand brand = brandRepository.findOne(modelModel.getBrandID());
        if (brand == null){
            throw new ApplicationException(MessageCode.ERROR_BRAND_ID_NOT_FOUND);
        }
        // Update
        model = model.updateModel(modelModel);
        this.updateData(model);
        return model;
    }

    public Model saveModel(Model model) {
        Brand brand = brandRepository.findOne(model.getBrandID());
        if (brand == null){
            throw new ApplicationException(MessageCode.ERROR_BRAND_ID_NOT_FOUND);
        }
        return this.saveData(model);

    }

    public Page<Variant> listVariantByModelId(Integer id, Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);

        return repository.listVariantByModelId(id, pageRequest);
    }
}
