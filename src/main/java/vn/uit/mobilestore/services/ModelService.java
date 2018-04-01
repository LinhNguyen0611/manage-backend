package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Model;
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
}
