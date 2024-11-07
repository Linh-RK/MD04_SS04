package com.ra.md04_ss04_security.security.jwt;

import com.ra.md04_ss04_security.security.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${expired}")
    private Long EXPIRED;
    @Value("${secret_key}")
    private String SECRET_KEY;

    private final Logger logger = (Logger) LoggerFactory.getLogger(JwtEntryPoint.class);

    public String generateToken(UserPrinciple userPrinciple) {
//        1. tao tgian song token
//        Date dateExpired = new Date(System.currentTimeMillis() + EXPIRED);
        Date dateExpired = new Date(new Date().getTime() + EXPIRED);
        return  Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }
//      2. xac thuc token
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (ExpressionException | SignatureException | ExpiredJwtException | MalformedJwtException e) {
            logger.error("Exception Authentication {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
