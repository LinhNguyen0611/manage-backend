package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;
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
}
