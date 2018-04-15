package vn.uit.mobilestore.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.exception.business.ResourceNotFoundException;
import vn.uit.mobilestore.repositories.UserRepository;
import vn.uit.mobilestore.requests.UserRequest;
import vn.uit.mobilestore.services.UserService;
import vn.uit.mobilestore.utils.CryptoUtils;

/**
 * Created by HieuNP on 15/04/2018.
 */
@Service
public class UserServiceImpl extends CrudServiceImpl<UserDto, User, UserRequest> implements UserService {

    protected UserRepository userRepository;

    @Value("${security.token.secret}")
    private String secretKey;

    @Override
    public User findByToken(String token) {
        String tokenDecoded = CryptoUtils.blowfishDecrypt(token, secretKey);
        if (tokenDecoded != null) {
            String[] tokenSplitter = tokenDecoded.split("\\.");
            if (tokenSplitter.length == 2) {
                Long userId = Long.parseLong(tokenSplitter[0]);
                String tokenId = tokenSplitter[1];

                User user = repository.findOne(userId);
                if (user != null && user.getToken().equals(tokenId)) {
                    return user;
                }
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
