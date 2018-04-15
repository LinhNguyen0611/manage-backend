package vn.uit.mobilestore.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AppSecretAuthencation extends UsernamePasswordAuthenticationToken {
    public AppSecretAuthencation(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AppSecretAuthencation(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
