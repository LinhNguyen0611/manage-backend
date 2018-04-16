package vn.uit.mobilestore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.UserRole;
import vn.uit.mobilestore.repositories.UserRoleRepository;

@Service
public class UserRoleService extends BaseService<UserRoleRepository, UserRole, Integer> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRoleService(UserRoleRepository repository) { super(repository);}

    public Page<UserRole> listAllUsers(Integer page, Integer size){
        PageRequest pageRequest = new PageRequest(page, size);

        Page<UserRole> users = findAll(pageRequest);
        return users;
    }
}
