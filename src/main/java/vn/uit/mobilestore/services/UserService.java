package vn.uit.mobilestore.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<UserRepository, User, Integer> implements UserDetailsService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService(UserRepository repository) { super(repository);}

    public Page<User> listAllUsers(Integer page, Integer size){
        PageRequest pageRequest = new PageRequest(page, size);

        Page<User> users = findAll(pageRequest);
        return users;
    }

    public boolean isExist(String username)
    {
        if(repository.getUserByName(username) != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.getUserByName(s);

        LOG.info("Loading user by Name");
        LOG.info("User:" + user);
        LOG.info("User's Role:" + user.getRoles());
        if (user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            LOG.info("Role Name: " + role.getRoleName());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getPassword(), authorities);

        LOG.info(userDetails.toString());

        return userDetails;
    }
}
