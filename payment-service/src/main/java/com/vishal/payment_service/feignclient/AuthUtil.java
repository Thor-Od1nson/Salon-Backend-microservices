package com.vishal.payment_service.feignclient;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vishal.payment_service.client.UserServiceClient;
import com.vishal.payment_service.payload.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuthUtil {

    @Autowired
    private UserServiceClient userServiceClient;


    public UserDto getCurrentUser() {
        String token = getToken();

        DecodedJWT decodedJWT = JWT.decode(token);
        String email = decodedJWT.getClaim("USER").asString();

        if (email == null) {
            throw new RuntimeException("Email not present in token");
        }

        UserDto response = userServiceClient.findUserByEmail(email);

        if (response == null) {
            throw new RuntimeException("Failed to fetch user for email: " + email);
        }

        return response;
    }

    private static String getToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("No request context");
        }
        HttpServletRequest request = attributes.getRequest();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        return token;
    }
}

