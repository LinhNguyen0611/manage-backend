package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.Variant;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.VariantModel;
import vn.uit.mobilestore.repositories.ModelRepository;
import vn.uit.mobilestore.repositories.VariantRepository;

/**
 * Created by Linh Nguyen on 4/11/2018.
 */
@Service
public class VariantService extends BaseService <VariantRepository, Variant, Integer>{
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    VariantService(VariantRepository repository) {
        super(repository);
    }

    @Autowired
    ModelRepository modelRepository;

    public Page<Variant> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Variant> variants = findAll(pageRequest);
        return variants;
    }

    public Variant updateVariant(Integer id, VariantModel variantModel) {
        // Find item
        Variant variant = repository.findOne(id);
        // Update
        variant = variant.updateVariant(variantModel);
        this.updateData(variant);
        return variant;
    }

    public Variant saveVariant(Variant variant) {
        // Validate model id
        Model model = modelRepository.findOne(variant.getModelID());
        if (model == null) {
            throw new ApplicationException(MessageCode.ERROR_MODEL_ID_NOT_FOUND);
        }
        return this.saveData(variant);
    }
}
