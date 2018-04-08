package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.repositories.RoleRepository;

@Service
public class RoleService extends BaseService<RoleRepository, Role, Integer>{

    @Autowired
    RoleService(RoleRepository repository) {super(repository);}

    public Page<Role> listAllRoles(Integer page, Integer size){
        PageRequest pageRequest = new PageRequest(page, size);

        Page<Role> roles = findAll(pageRequest);
        return roles;
    }

}
