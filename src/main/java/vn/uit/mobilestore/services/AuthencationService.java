package vn.uit.mobilestore.services;

import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.requests.LoginRequest;
import vn.uit.mobilestore.requests.UserRequest;
import vn.uit.mobilestore.requests.users.UserCreateRequest;
import vn.uit.mobilestore.responses.LoginResponse;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
public interface AuthencationService {

    LoginResponse login(LoginRequest loginRequest);

    UserDto create(UserCreateRequest userRequest);
}
