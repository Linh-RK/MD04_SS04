package com.ra.md04_ss04_security.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        response.getWriter().write("Unauthorized");
//        logger.error("Unauthorized {}", authException.getMessage());
        logger.error("Un Authentication {}", authException.getMessage());
        response.setHeader("error", "UNAUTHORIZED");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(401);
        Map<String, Object> errors = new HashMap<>();
        errors.put("code", 401);
        errors.put("error", authException.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), errors);
    }
}
