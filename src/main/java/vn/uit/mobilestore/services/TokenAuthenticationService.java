package vn.uit.mobilestore.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Value("${security.jwt.client-id}")
    static String clientId;

    static String clientSecret = "XY7kmzoNzl100";

    @Value("${security.jwt.grant-type}")
    static String grantType;

    @Value("${security.jwt.scope-read}")
    static String scopeRead;

    @Value("${security.jwt.scope-write}")
    static String scopeWrite = "write";

    @Value("${security.jwt.resource-ids}")
    static String resourceIds;

    public static void addAuthentication(HttpServletResponse res, String username) throws IOException, JSONException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, clientSecret)
                .compact();
        res.addHeader("Content-Type", "Application/json; charset: utf-8");

        JSONObject json = new JSONObject();
        json.put("Type", TOKEN_PREFIX);
        json.put("Token", JWT);

        res.getWriter().write(json.toString());
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(clientSecret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
