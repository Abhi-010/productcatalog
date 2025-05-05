package dev.abhi.productcatalog.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

//    private final JwtAuthenticationConverter jwtAuthenticationConverter;
//    public SpringSecurityConfig(JwtAuthenticationConverter jwtAuthenticationConverter){
//        this.jwtAuthenticationConverter = jwtAuthenticationConverter ;
//    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/products").hasAuthority("ROLE_admin")
                        .anyRequest().permitAll()
                )
                .csrf().disable()
                .cors().disable()
                .formLogin().disable();
                //.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt-> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))) ;
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                //.formLogin(Customizer.withDefaults());
//                .formLogin().disable();

        return http.build();
    }
}
