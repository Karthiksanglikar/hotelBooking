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
       // httpSecurity.authorizeHttpRequests().anyRequest().permitAll();

        //only these 2 url can access permit all,other than this authenticated
        httpSecurity.authorizeHttpRequests().
                anyRequest().permitAll();
//                requestMatchers("/api/v1/auth/login","/api/v1/auth/sign-up","/api/v1/auth/signUp-PropertyOwner","api/properties/searh-hotelsystem")
//                .permitAll();
//                .requestMatchers("/api/v1/country/addCountry","api/properties/searh-hotelsystem?CityName=ooty")
//                .hasAnyRole("ADMIN","OWNER")
//                .anyRequest().authenticated();
       return httpSecurity.build();
    }

}
