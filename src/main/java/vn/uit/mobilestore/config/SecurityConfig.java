package vn.uit.mobilestore.config;

import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   private final Logger LOG = LoggerFactory.getLogger(this.getClass());

   @Value("${security.signing-key}")
   private String signingKey;

   @Value("${security.encoding-strength}")
   private Integer encodingStrength;

   @Value("${security.security-realm}")
   private String securityRealm;

   @Autowired
   private UserDetailsService userDetailsService;

   @Bean
   @Override
   protected AuthenticationManager authenticationManager() throws Exception {
      return super.authenticationManager();
   }

//   @Override
//   public void configure(HttpSecurity http) throws Exception {
//      http.csrf().disable().authorizeRequests()
//              .antMatchers(HttpMethod.POST, "/login").permitAll()
//              .anyRequest().authenticated()
//              .and()
//              .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//                      UsernamePasswordAuthenticationFilter.class)
//              .addFilterBefore(new JWTAuthenticationFilter(),
//                      UsernamePasswordAuthenticationFilter.class);
//   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      //this is to create an account;
      auth.userDetailsService(userDetailsService)
              .passwordEncoder(new ShaPasswordEncoder(encodingStrength));
   }

   @Bean
   public JwtAccessTokenConverter accessTokenConverter() {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
      converter.setSigningKey(signingKey);
       LOG.info("Access Token Converter");
      return converter;
   }

   @Bean
   public TokenStore tokenStore() {
      TokenStore tknStore =  new JwtTokenStore(accessTokenConverter());
      LOG.info("Token Store" + tknStore);
      return tknStore;
   }

   @Bean
   @Primary //Making this primary to avoid any accidental duplication with another token service instance of the same name
   public DefaultTokenServices tokenServices() {
      DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
      defaultTokenServices.setTokenStore(tokenStore());
      defaultTokenServices.setSupportRefreshToken(true);
      return defaultTokenServices;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .httpBasic()
              .realmName(securityRealm)
              .and()
              .csrf()
              .disable();

   }
}