package vn.uit.mobilestore.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.exception.business.ResourceNotFoundException;
import vn.uit.mobilestore.exception.security.TokenInvalidException;
import vn.uit.mobilestore.repositories.UserRepository;
import vn.uit.mobilestore.requests.UserRequest;
import vn.uit.mobilestore.services.UserService;
import vn.uit.mobilestore.utils.CryptoUtils;
import vn.uit.mobilestore.utils.ValidateUtils;

/**
 * Created by HieuNP on 15/04/2018.
 */
@Service
public class UserServiceImpl extends CrudServiceImpl<UserDto, User, UserRequest> implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${security.secret}")
    private String secretKey;

    @Autowired
    protected UserRepository userRepository;

    @Override
    public User findByToken(String token) {
        String tokenDecoded = CryptoUtils.blowfishDecrypt(token, secretKey);
        if (ValidateUtils.isNotNullAndEmpty(tokenDecoded)) {
            try
            {
                String[] tokenSplitter = tokenDecoded.split("\\.");
                if (tokenSplitter.length == 2) {
                    Long userId = Long.parseLong(tokenSplitter[0]);
                    String tokenId = tokenSplitter[1];

                    User user = repository.findOne(userId);
                    if (user != null && ValidateUtils.isNotNullAndEmpty(user.getToken()) && user.getToken().equals(tokenId)) {
                        return user;
                    }
                }
            }
            catch (Exception e) {
                LOGGER.warn("Decode token has error: {}", e);
            }
        }

        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException());
    }
}
