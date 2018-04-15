package vn.uit.mobilestore.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "vn.uit")
@EntityScan(basePackages = {"vn.uit"})
@EnableJpaRepositories(basePackages = {"vn.uit"})
public class AppConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);

        return builder;
    }

    @Bean
    public PageableHandlerMethodArgumentResolver sortCustomizer() {
        return new PageableHandlerMethodArgumentResolver();
    }
}
