package vn.uit.mobilestore.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TokenAuthencationFilter extends BasicAuthenticationFilter {

    private static final String tokenHeader = "Authorization";

    private UserService userService;

    public TokenAuthencationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain chain) throws IOException, ServletException {
        TokenAuthencation authentication = getAuthentication(httpServletRequest);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    private TokenAuthencation getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        User user = userService.findByToken(token);
        if (user != null) {
            return new TokenAuthencation(user, null, user.getAuthorities());
        }

        return null;
    }
}
