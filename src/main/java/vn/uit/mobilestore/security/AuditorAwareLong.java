package vn.uit.mobilestore.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.uit.mobilestore.entities.User;

public class AuditorAwareLong implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();

            return user.getId();
        }

        return null;
    }
}
