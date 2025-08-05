package com.vishal.booking_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JwtFilter extends BasicAuthenticationFilter {

    private BeanFactory factory;

    public JwtFilter(AuthenticationManager authenticationManager, BeanFactory f){
        super(authenticationManager);
        factory=f;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer")){
            JwtUtils utils=factory.getBean(JwtUtils.class);

            String token=header.substring(7);
            token=utils.validateToken(token);
            JsonParser parser= JsonParserFactory.getJsonParser();
            Map<String, Object> m=parser.parseMap(token);
            String user= (String) m.get(JwtUtils.USER_CLAIM);
            List<String> role= (List<String>) m.get(JwtUtils.ROLE_CLAIM);

            Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role.toArray(new String[0]));
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }
}
