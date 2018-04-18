package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Variant;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.VariantModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.VariantService;

import javax.validation.Valid;

/**
 * Created by Linh Nguyen on 4/17/2018.
 */
@RestController
@RequestMapping(URL.VARIANT_CONTROLLER)
public class VariantController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final VariantService variantService;

    @Autowired
    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }
    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<Variant> saveVariant(@RequestBody @Valid VariantModel variantModel) {
        ResponseModel<Variant> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveVariant ");
            //Save variant - create method in service to validate
            Variant variant = variantService.saveVariant(variantModel.toEntity());
            response.setData(variant);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " saveVariant : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveVariant ");
        }
    }

    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<Variant> getModel(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Variant> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getVariant ");
            //Get item
            Variant variant = variantService.getById(id);
            response.setData(variant);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getVariant : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getVariant ");
        }
    }

    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<Variant> updateVariant(@PathVariable(value = Const.PATH_ID) Integer id,
                                              @RequestBody @Valid VariantModel variantModel) {
        ResponseModel<Variant> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateItem ");
            //Update ite
            Variant variant = variantService.updateVariant(id, variantModel);
            response.setData(variant);
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
    public ResponseModel<Page<Variant>> listAll(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        ResponseModel<Page<Variant>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " listAll [page={}],[size = {}] ", page, size);
            //List all
            response.setData(variantService.listAll(page, size));
            return response;
        } catch (ApplicationException ex) {
            LOG.error(Const.LOGGING_ERROR + "listAll: {}", ex.getMessage());
            response.buildError(ex);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " listAll ");
        }
    }
}
