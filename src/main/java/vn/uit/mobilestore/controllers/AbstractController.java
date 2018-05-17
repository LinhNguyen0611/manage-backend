package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.entities.BaseEntity;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.IService;

/**
 * Created by Linh Nguyen on 4/19/2018.
 */
abstract class AbstractController<Service extends IService, Entity extends BaseEntity> {
    /**
     * Soft delete one item
     *
     * @param id Primary key
     * @param LOG Log object
     * @param service Service object
     * @return Success/Error code
     */
    ResponseModel<String> deleteOne(Integer id, Logger LOG, Service service) {
        ResponseModel<String> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " deleteOne ");
            //Get item
            service.deleteOne(id);
            response.setData("Success");
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " deleteOne : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " deleteOne ");
        }
    }

    /**
     * List paging item by page and size
     *
     * @param size Page size
     * @param page Page number (start from 0)
     * @param LOG Log object
     * @param service Service object
     * @return Page
     */
    ResponseModel<Page<Entity>> listAll(Integer size, Integer page, Logger LOG, Service service) {
        ResponseModel<Page<Entity>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " listAll [page={}],[size = {}] ", page, size);
            //List all
            response.setData(service.listAll(page, size));
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
