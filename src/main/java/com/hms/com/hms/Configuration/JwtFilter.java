package com.hms.com.hms.Configuration;

import com.hms.com.hms.Entity.Appusers;
import com.hms.com.hms.Repository.AppusersRepository;
import com.hms.com.hms.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private AppusersRepository appusersRepository;

    public JwtFilter(JwtService jwtService, AppusersRepository appusersRepository) {
        this.jwtService = jwtService;
        this.appusersRepository = appusersRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // http token comes here variable token
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token!=null && token.startsWith("Bearer")){
            String tokenval = token.substring(8, token.length() - 1);
            String username = jwtService.getUsername(tokenval);
            //to connect with database
            Optional<Appusers> opusername = appusersRepository.findByUsername(username);
            if (opusername.isPresent()){
                Appusers appusers = opusername.get();

                //"This block creates an authentication object using the user info from the database.
                // Then it attaches request details like IP address. Finally,
                // it stores the authentication in Spring Security’s context,
                // which officially marks the user as logged in for the current request.
                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken
                                (appusers,null, Collections.singleton(new SimpleGrantedAuthority(appusers.getRole())));
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }
        //When your filter finishes its work (like verifying a JWT),
        // you must pass the request forward so the next filter can do its job too
        filterChain.doFilter(request,response);
    }
}
