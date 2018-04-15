package vn.uit.mobilestore.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.requests.LoginRequest;
import vn.uit.mobilestore.requests.UserRequest;
import vn.uit.mobilestore.requests.users.UserCreateRequest;
import vn.uit.mobilestore.responses.LoginResponse;
import vn.uit.mobilestore.services.AuthencationService;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
@RestController
@RequestMapping("/auth")
public class AuthencationAPI {

    @Autowired
    private AuthencationService authencationService;

    @PostMapping("/login")
    @ApiOperation("Get token by username/email and password")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authencationService.login(loginRequest);
    }

    @PostMapping
    @ApiOperation("Create user")
    public UserDto create(@RequestBody UserCreateRequest userCreateRequest) {
        return authencationService.create(userCreateRequest);
    }
}
