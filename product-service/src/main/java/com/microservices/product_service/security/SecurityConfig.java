
package com.microservices.product_service.security;

import com.microservices.product_service.filter.RoleHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    RoleHeaderFilter roleHeaderFilter() {
        return new RoleHeaderFilter();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/*/**").permitAll()
                        .anyRequest()
                        .authenticated()
                );

        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.addFilterBefore(roleHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}

