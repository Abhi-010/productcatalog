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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

//    private final JwtAuthenticationConverter jwtAuthenticationConverter;
//
//    public SpringSecurityConfig(JwtAuthenticationConverter jwtAuthenticationConverter){
//        this.jwtAuthenticationConverter = jwtAuthenticationConverter ;
//    }

//   // @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, JwtAuthenticationConverter jwtAuthenticationConverter)
//            throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/products").hasRole("USER")
//                        .anyRequest().permitAll()
//                )
//                .csrf().disable()
//                .cors().disable()
//                .formLogin().disable() ;
//                //.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt-> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))) ;
//                // Form login handles the redirect to the login page from the
//                // authorization server filter chain
//                //.formLogin(Customizer.withDefaults());
////                .formLogin().disable();
//
//        return http.build();
//    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<Map<String, String>> roles = jwt.getClaim("roles");

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.get("roleName")))
                    .collect(Collectors.toList());
        });

        return converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtFilter)
            throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/products/**").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
