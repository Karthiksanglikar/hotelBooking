package com.hms.com.hms.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SecurityConfig {
    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity
    ) throws Exception {
        //h(cd)2
        httpSecurity.csrf().disable().cors().disable();
        //when filter run before
        httpSecurity.addFilterBefore(jwtFilter,AuthorizationFilter.class);

        //haap
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
       return httpSecurity.build();
    }

}
