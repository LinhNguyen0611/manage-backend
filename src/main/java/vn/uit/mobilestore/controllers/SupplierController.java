package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Supplier;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.SupplierModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.SupplierService;
import vn.uit.mobilestore.models.ComplexReturnModel.SupplierReturnModel;


@RestController
@RequestMapping(URL.SUPPLIER_CONTROLLER)
public class SupplierController extends AbstractController<SupplierService, Supplier> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final SupplierService supplierService;


    // SupplierController Constructor
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    // Get List Supplier
    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<Supplier>> getSuppliers(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        return this.listAll(size, page, LOG, supplierService);
    }

    // Add Supplier
    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<Supplier> saveSupplier(@RequestBody @Valid SupplierModel supplierModel) {
        ResponseModel<Supplier> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveSupplier ");
            //Save supplier
            Supplier supplier = supplierService.saveData(supplierModel.toEntity());
            response.setData(supplier);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " saveSupplier : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveSupplier ");
        }
    }

    // Update Supplier by Id
    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<Supplier> updateSupplierById(@PathVariable(value = Const.PATH_ID) Integer id,
                                                      @RequestBody @Valid SupplierModel supplierModel) {
        ResponseModel<Supplier> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateItem ");
            //Update Supplier
            Supplier supplier = supplierService.updateById(id, supplierModel);
            response.setData(supplier);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " updateItem : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " updateItem ");
        }
    }

    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<Supplier> getSupplierById(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Supplier> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getBrand ");
            //Get supplier by Id
            Supplier supplier = supplierService.getById(id);
            response.setData(supplier);
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " getBrand : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getBrand ");
        }
    }

    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<String> deleteBrand(@PathVariable(value = Const.PATH_ID) Integer id) {
        return this.deleteOne(id, LOG, supplierService);
    }

    // Test
    @RequestMapping(value = URL.GET_ACTION + "/getExtraInfo", method = RequestMethod.GET)
    public ResponseModel<SupplierReturnModel> getExtraSupplierById(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<SupplierReturnModel> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getExtraSupplierById ");
            //Get supplier by Id
            Supplier supplier = supplierService.getById(id);
            response.setData(SupplierReturnModel.create(supplier));
            return response;
        } catch (ApplicationException applicationException) {
            LOG.error(Const.LOGGING_ERROR + " getExtraSupplierById : {}", applicationException.getMessage());
            response.buildError(applicationException);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getExtraSupplierById ");
        }
    }
}
