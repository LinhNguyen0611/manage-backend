package vn.uit.mobilestore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.ModelModel;
import vn.uit.mobilestore.repositories.BrandRepository;
import vn.uit.mobilestore.repositories.ModelRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@Service
public class ModelService extends BaseService <ModelRepository, Model, Integer> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    ModelService(ModelRepository repository, BrandRepository brandRepository, VariantService variantService) {
        super(repository);
        this.brandRepository = brandRepository;
        this.variantService = variantService;
    }
    private final BrandRepository brandRepository;
    private final VariantService variantService;

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

    @Override
    @Transactional
    public void deleteOne(Integer id) {
        LOG.info(Const.LOGGING_SERVICE_BEGIN + " deleteOne  {}", id);
        Model entity = this.getById(id);
        // Delete child
        List<Integer> ids = new ArrayList<>();
        for (Variant variant : entity.getVariantList()) {
            ids.add(variant.getVariantId());
        }
        ids.forEach(variantService::deleteOne);
        entity.setActive(false);
        this.saveData(entity);
    }
}
