package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.ItemModel;
import vn.uit.mobilestore.models.ModelModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.ModelService;

import javax.validation.Valid;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@RestController
@RequestMapping(URL.MODEL_CONTROLLER)
public class ModelController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<Model> saveItem(@RequestBody @Valid ModelModel modelModel) {
        ResponseModel<Model> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveItem ");
            //Save item
            Model item = modelService.saveData(modelModel.toEntity());
            response.setData(item);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " saveItem : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveItem ");
        }
    }
}
