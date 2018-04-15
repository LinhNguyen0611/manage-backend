package vn.uit.mobilestore.services;

import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.requests.UserRequest;

/**
 * Created by HieuNP on 15/04/2018.
 */
public interface UserService extends CrudService<UserDto, User, UserRequest> {

    User findByToken(String token);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

}
