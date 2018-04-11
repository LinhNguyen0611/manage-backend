package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.models.ModelModel;
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

    public Page<Model> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Model> models = findAll(pageRequest);
        return models;
    }

    public Model updateModel(Integer id, ModelModel modelModel) {
        // Find item
        Model model = repository.findOne(id);
        // Update
        model = model.updateModel(modelModel);
        this.updateData(model);
        return model;
    }
}