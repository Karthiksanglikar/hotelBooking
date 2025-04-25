package com.hms.com.hms.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hms.com.hms.Entity.Appusers;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.algorithm.key}")
    private String Algorithmkey;
    @Value("${jwt.Issuer}")
    private String Issuer;
    @Value("${jwt.expiry.duration}")
    private int expiryTime;
    private Algorithm algorithm;

    @PostConstruct
    public void Postconstructor() throws UnsupportedEncodingException {

       algorithm =Algorithm.HMAC256(Algorithmkey);

    }
    public String generateTocken(String username){
        return JWT.create()
                .withClaim("name",username)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(Issuer)
                .sign(algorithm);
    }
    public String getUsername(String token){
        DecodedJWT decodedJWT = JWT.require(algorithm).
                withIssuer(Issuer).
                build().
                verify(token);
        String name = decodedJWT.getClaim("name").asString();
        return name;
    }
}
