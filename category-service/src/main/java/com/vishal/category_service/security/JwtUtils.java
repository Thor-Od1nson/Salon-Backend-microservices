package com.vishal.category_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtUtils {

    private static final String SECRET_KEY="iam vishalkumarsawiamfromjharkhandworkinginRebit";
    public static final String USER_CLAIM="USER";
    public static final String ROLE_CLAIM="ROLE";


    public String validateToken(String token){
        String validToken = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getPayload();
        return new String(Base64.getDecoder().decode(validToken));
    }
}
