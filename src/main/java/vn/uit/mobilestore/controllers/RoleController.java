package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.ItemModel;
import vn.uit.mobilestore.models.RoleModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.RoleService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(URL.ROLE_CONTROLLER)
public class RoleController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    //@PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<Role> saveItem(@RequestBody @Valid RoleModel roleModel) {
        ResponseModel<Role> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveItem ");
            //Save item
            if (roleService.isExist(roleModel.getRoleName()))
            {
                response.buildError(400, "Role Already Existed");
                return response;
            }
            Role role = roleService.saveData(roleModel.toEntity());
            response.setData(role);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " saveItem : {}", ae.getMessage());
            response.buildError(500, ae.getErrorMessage());
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveItem ");
        }
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<List<Role>> getRoles()
    {
        LOG.info("STARTING CONTROLLER");
        ResponseModel<List<Role>> rm = new ResponseModel<>();
        List<Role> roles = roleService.listAllRoles();
        if(roles.size() == 0)
        {
            rm.buildError(404, "no role available");
        }

        rm.setData(roles);
        LOG.info("List of ROLES:" + roles);
        return rm;
    }
}
