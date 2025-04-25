package com.hms.com.hms.Configuration;

import com.hms.com.hms.Entity.Appusers;
import com.hms.com.hms.Repository.AppusersRepository;
import com.hms.com.hms.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
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
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token!=null && token.startsWith("Bearer")){
            String tokenval = token.substring(8, token.length() - 1);
            String username = jwtService.getUsername(tokenval);
            Optional<Appusers> opusername = appusersRepository.findByUsername(username);
            if (opusername.isPresent()){

            }
        }
        filterChain.doFilter(request,response);
    }
}
