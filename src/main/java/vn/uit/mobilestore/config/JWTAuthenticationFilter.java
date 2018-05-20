package vn.uit.mobilestore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.repositories.UserRepository;
import vn.uit.mobilestore.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static vn.uit.mobilestore.config.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public JWTAuthenticationFilter(UserService userService, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        logger.info("Attempting");
        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);
            logger.info("USER CREDDDS:" + creds.getRoles());

            UserDetails userDetails = userService.loadUserByUsername(creds.getUserName());

            logger.info("USER DETAILLLL:" + userDetails.toString());
            logger.info("USER CREDDDS:" + creds.getRoles());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserName(),
                            creds.getPassword(),
                            userDetails.getAuthorities())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        logger.info("Success Auth");

        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        JSONObject json = new JSONObject();
        json.put(HEADER_STRING, TOKEN_PREFIX + token);
        res.addHeader("Content-Type", "Application/json");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST");
        res.addHeader("Access-Control-Allow-Headers", "accept, authority");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin","*");
        res.getWriter().write(json.toString());
    }
}
