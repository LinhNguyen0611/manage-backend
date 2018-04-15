package vn.uit.mobilestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.exception.security.IdentifierPasswordInvalidException;
import vn.uit.mobilestore.mappers.UserMapper;
import vn.uit.mobilestore.repositories.UserRepository;
import vn.uit.mobilestore.requests.LoginRequest;
import vn.uit.mobilestore.requests.UserRequest;
import vn.uit.mobilestore.requests.users.UserCreateRequest;
import vn.uit.mobilestore.responses.LoginResponse;
import vn.uit.mobilestore.services.AuthencationService;
import vn.uit.mobilestore.services.UserService;
import vn.uit.mobilestore.utils.CryptoUtils;
import vn.uit.mobilestore.utils.ValidateUtils;

import java.util.UUID;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
@Service
public class AuthencationServiceImpl implements AuthencationService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Value("${security.secret}")
    private String secretKey;

    private String generateToken(User user){
        user.setToken(UUID.randomUUID().toString());
        userRepository.saveAndFlush(user);

        String token = user.getId() + "." + user.getToken();
        token = CryptoUtils.blowfishEncrypt(token, secretKey);

        return token;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = null;
        if (ValidateUtils.isEmail(loginRequest.getCredential())) {
            user = userRepository.findByEmail(loginRequest.getCredential()).orElseThrow(() -> new IdentifierPasswordInvalidException());
        }
        else {
            user = userRepository.findByUsername(loginRequest.getCredential()).orElseThrow(() -> new IdentifierPasswordInvalidException());
        }
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            LoginResponse loginResponse = new LoginResponse();
            UserDto userDto = userMapper.toFullDto(user);
            loginResponse.setUser(userDto);
            loginResponse.setToken(generateToken(user));

            return loginResponse;
        }
        else {
            throw new IdentifierPasswordInvalidException();
        }
    }

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        User user = userMapper.createUser(userCreateRequest);
        user = userRepository.saveAndFlush(user);

        return userMapper.toFullDto(user);
    }
}
