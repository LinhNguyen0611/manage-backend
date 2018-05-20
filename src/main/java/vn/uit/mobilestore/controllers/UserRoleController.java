package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.entities.UserRole;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.UserModel;
import vn.uit.mobilestore.models.UserRoleModel;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.UserRoleService;
import vn.uit.mobilestore.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {this.userRoleService = userRoleService;}

    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<UserRole> setUserRole(@RequestBody @Valid UserRoleModel userRoleModel){
        ResponseModel<UserRole> response = new ResponseModel<>();
        try{
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + "registerUser" + userRoleModel);
            if(userRoleService.IsExist(userRoleModel.getUserID(), userRoleModel.getRoleID()))
            {
                response.buildError(400, "Role Duplicated");
                return response;
            }
            //save user
            UserRole user = userRoleService.saveData(userRoleModel.toEntity());
            response.setData(user);
            return response;
        }catch (ApplicationException ae)
        {
            LOG.error(Const.LOGGING_ERROR + " saveUser: {}", ae.getMessage());
            response.buildError(ae);
            return response;
        }finally{
            LOG.info(Const.LOGGING_CONTROLLER_END + "saveUser");
        }
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<List<UserRole>> getRoles()
    {
        LOG.info("STARTING CONTROLLER");
        ResponseModel<List<UserRole>> rm = new ResponseModel<>();
        List<UserRole> userRoles = userRoleService.listAllUserRoles();
        if(userRoles.size() == 0)
        {
            rm.buildError(404, "no role available");
        }

        rm.setData(userRoles);
        LOG.info("FINSHED GET ROLES CONTROLLER");
        return rm;
    }
}
