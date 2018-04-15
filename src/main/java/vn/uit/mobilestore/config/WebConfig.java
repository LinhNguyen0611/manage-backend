package vn.uit.mobilestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * The type Web config.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api-documentation", "/swagger-ui.html");
        registry.addViewController("/").setViewName("index");
        registry.addViewController(buildIgnorePath(
                "api",
                "js",
                "css",
                "fonts",
                "statics",
                "img",
                "webjars",
                "api-docs",
                "configuration",
                "swagger-resources")).setViewName("forward:/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    protected String buildIgnorePath(String... ignores) {
        String template = "/{spring:%s\\b\\w+}/**";
        StringBuilder stringBuilder = new StringBuilder();
        for (String ignore : ignores) {
            stringBuilder.append(String.format("(?!%s\\b)", ignore));
        }
        return String.format(template, stringBuilder.toString());
    }
}