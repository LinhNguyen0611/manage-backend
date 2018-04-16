package vn.uit.mobilestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import vn.uit.mobilestore.security.AuditorAwareLong;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

    @Bean(name = "auditorAware")
    public AuditorAware<Long> getAuditorAware() {
        return new AuditorAwareLong();
    }
}
