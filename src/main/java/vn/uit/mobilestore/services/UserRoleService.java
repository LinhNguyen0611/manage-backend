package vn.uit.mobilestore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.UserRole;
import vn.uit.mobilestore.repositories.UserRoleRepository;

import java.util.List;

@Service
public class UserRoleService extends BaseService<UserRoleRepository, UserRole, Integer> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRoleService(UserRoleRepository repository) { super(repository);}

    public boolean IsExist(Integer userID, Integer roleID) // TODO: not finshed yet
    {
        List<UserRole> uRoles =  repository.getRolesById(userID);
        LOG.info("Role ID " + roleID + "UserID" + userID);
        for (UserRole uRole : uRoles) {
            LOG.info(uRole.getUserID().toString());
        }
        for(UserRole uRole : uRoles)
        {
            LOG.info("Role ID " + uRole.getRoleID() + "and roleID" + roleID);
            if(uRole.getRoleID() == roleID)
            {
                return true;
            }
        }
        return false;
    }

    public List<UserRole> listAllUserRoles(){

        List<UserRole> userRoles = repository.getRoles();
        return userRoles;
    }
}
