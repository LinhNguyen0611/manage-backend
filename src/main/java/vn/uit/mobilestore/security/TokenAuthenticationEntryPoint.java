package vn.uit.mobilestore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import vn.uit.mobilestore.config.AppConfig;
import vn.uit.mobilestore.responses.ErrorResponse;
import vn.uit.mobilestore.utils.ValidateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String token = request.getHeader(AppConfig.TOKEN_HEADER);
        ErrorResponse errorResponse = new ErrorResponse();
        if (ValidateUtils.isNotNullAndEmpty(token)) {
            errorResponse.setCode("TOKEN_IS_INVALID");
            errorResponse.setMessage("The token is invalid");
        }
        else {
            errorResponse.setCode(HttpStatus.UNAUTHORIZED.name());
            errorResponse.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }

        response.reset();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}