package vn.uit.mobilestore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.UserModel;
import vn.uit.mobilestore.models.UserRoleModel;
import vn.uit.mobilestore.regex.UsernameValidator;
import vn.uit.mobilestore.responses.ResponseModel;
import vn.uit.mobilestore.services.UserService;

import javax.validation.Valid;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private final UsernameValidator usernameValidator = new UsernameValidator();

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value= "/sign-up", method = RequestMethod.POST)
    public ResponseModel<User> signUp(@RequestBody UserModel userModel) {
        LOG.info("Signing up");
        ResponseModel<User> response = new ResponseModel<>();
        if(!usernameValidator.validate(userModel.getUserName())){
           return response.buildError(400, "Invalid Username");
        }

        if(userService.isExist(userModel.getUserName()))
        {
            return response.buildError(400, "Username is already used");
        }

        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        User user = userService.saveData(userModel.toEntity());
        response.setData(user);
        response.success();
        return response;
    }

    @RequestMapping(value = URL.GET_ACTION , method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<User> getUser(@PathVariable(Const.PATH_ID) Integer id) {
        ResponseModel<User> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getUSer ");
            //Get item
            User user = userService.getById(id);
            response.setData(user);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getUser : {}", ae.getMessage());
            response.buildError(500, ae.getErrorMessage());
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getUset ");
        }
    }

    @RequestMapping(value = "/get/customers/list" , method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<List<User>> getCustomers() {
        ResponseModel<List<User>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getUSer ");
            //Get item
            List<User> users = userService.listAllCustomers();
            response.setData(users);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getUser : {}", ae.getMessage());
            response.buildError(500, ae.getErrorMessage());
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getUset ");
        }
    }

    @RequestMapping(value = "/get/token" , method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<Object> getUserByToken() {
        ResponseModel<Object> response = new ResponseModel<>();
        try {
            Object user = SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            response.setData(user);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getUser : {}", ae.getMessage());
            response.buildError(500, ae.getErrorMessage());
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getUset ");
        }
    }

    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseModel<Page<User>> listAll(
            @PathVariable(Const.PATH_SIZE) Integer size,
            @PathVariable(Const.PATH_PAGE) Integer page) {
        ResponseModel<Page<User>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " listAll [page={}],[size = {}] ", page, size);
            //List all
            response.setData(userService.listAllUsers(page, size));
            return response;
        } catch (ApplicationException ex) {
            LOG.error(Const.LOGGING_ERROR + "listByName: {}", ex.getMessage());
            response.buildError(ex);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " listByName ");
        }
    }
}
