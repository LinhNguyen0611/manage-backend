package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.BrandModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.BrandService;

import javax.validation.Valid;

/**
 * Created by Linh Nguyen on 4/17/2018.
 */
@RestController
@RequestMapping(URL.BRAND_CONTROLLER)
public class BrandController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<Brand> saveBrand(@RequestBody @Valid BrandModel brandModel) {
        ResponseModel<Brand> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveBrand ");
            //Save brand
            Brand brand = brandService.saveData(brandModel.toEntity());
            response.setData(brand);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " saveBrand : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveBrand ");
        }
    }

    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<Brand> getModel(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Brand> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getBrand ");
            //Get item
            Brand brand = brandService.getById(id);
            response.setData(brand);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getBrand : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getBrand ");
        }
    }

    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<Brand> updateBrand(@PathVariable(value = Const.PATH_ID) Integer id,
                                            @RequestBody @Valid BrandModel brandModel) {
        ResponseModel<Brand> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateItem ");
            //Update ite
            Brand brand = brandService.updateBrand(id, brandModel);
            response.setData(brand);
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
    public ResponseModel<Page<Brand>> listAll(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        ResponseModel<Page<Brand>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " listAll [page={}],[size = {}] ", page, size);
            //List all
            response.setData(brandService.listAll(page, size));
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
