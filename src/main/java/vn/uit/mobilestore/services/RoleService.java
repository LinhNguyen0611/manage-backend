package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService extends BaseService<RoleRepository, Role, Integer>{

    @Autowired
    RoleService(RoleRepository repository) {super(repository);}

    public List<Role> listAllRoles(){
        List<Role> roles = repository.getRoles();
        return roles;
    }

    public boolean isExist(String roleName)
    {
        if(repository.getRoleByName(roleName) != null)
            return true;
        return false;
    }

}
