package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
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
    public ResponseModel<Model> saveModel(@RequestBody @Valid ModelModel modelModel) {
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

    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<Model> getModel(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Model> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getModel ");
            //Get item
            Model item = modelService.getById(id);
            response.setData(item);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getModel : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getModel ");
        }
    }

    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<Model> deleteItem(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Model> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " deleteModel ");
            //Get item
            Model item = modelService.deleteById(id);
            response.setData(item);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " deleteModel : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " deleteModel ");
        }
    }

    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<Model> updateModel(@PathVariable(value = Const.PATH_ID) Integer id,
                                          @RequestBody @Valid ModelModel modelModel) {
        ResponseModel<Model> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateItem ");
            //Update ite
            Model model = modelService.updateModel(id, modelModel);
            response.setData(model);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " updateItem : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " updateItem ");
        }
    }

    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<Model>> listAll(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        ResponseModel<Page<Model>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " listAll [page={}],[size = {}] ", page, size);
            //List all
            response.setData(modelService.listAll(page, size));
            return response;
        } catch (ApplicationException ex) {
            LOG.error(Const.LOGGING_ERROR + "listByName: {}", ex.getMessage());
            response.buildError(ex);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " listByName ");
        }
    }
}
