package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Branch;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.BranchModel;
import vn.uit.mobilestore.models.ModelModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.BranchService;
import vn.uit.mobilestore.services.ModelService;

import javax.validation.Valid;

/**
 * Created by Linh Nguyen on 4/3/2018.
 */
@RestController
@RequestMapping(value = URL.BRANCH_CONTROLLER)
class BranchController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<Branch> saveBranch(@RequestBody @Valid BranchModel branchModel) {
        ResponseModel<Branch> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveBranch ");
            //Save branch
            Branch branch = branchService.saveData(branchModel.toEntity());
            response.setData(branch);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " saveBranch : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveBranch ");
        }
    }

    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<Branch> getModel(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Branch> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getBranch ");
            //Get item
            Branch branch = branchService.getById(id);
            response.setData(branch);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getBranch : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getBranch ");
        }
    }

    @RequestMapping(value = URL.DELETE_ACTION, method = RequestMethod.DELETE)
    public ResponseModel<Branch> deleteBranch(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<Branch> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " deleteBranch ");
            //Get item
            Branch branch = branchService.deleteById(id);
            response.setData(branch);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " deleteBranch : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " deleteBranch ");
        }
    }

    @RequestMapping(value = URL.UPDATE_ACTION, method = RequestMethod.POST)
    public ResponseModel<Branch> updateBranch(@PathVariable(value = Const.PATH_ID) Integer id,
                                            @RequestBody @Valid BranchModel branchModel) {
        ResponseModel<Branch> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " updateItem ");
            //Update ite
            Branch branch = branchService.updateBranch(id, branchModel);
            response.setData(branch);
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
    public ResponseModel<Page<Branch>> listAll(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        ResponseModel<Page<Branch>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " listAll [page={}],[size = {}] ", page, size);
            //List all
            response.setData(branchService.listAll(page, size));
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
