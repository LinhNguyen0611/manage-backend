package vn.uit.mobilestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The type Web config.
 */
@Configuration
public class WebConfig {

    /**
     * Cors configurer web mvc configurer.
     *
     * @return the web mvc configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}