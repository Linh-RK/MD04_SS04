package com.ra.md04_ss04_security.security.jwt;

import com.ra.md04_ss04_security.model.entity.User;
import com.ra.md04_ss04_security.security.UserDetailService;
import com.ra.md04_ss04_security.security.UserPrinciple;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailService userDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String token = getTokenFromRequest(request);
       try{
           if (token != null && jwtProvider.validateToken(token)) {
               String username = jwtProvider.getUsernameFromToken(token);
               UserDetails userDetails = userDetailService.loadUserByUsername(username);
               if(userDetails != null) {
                   UsernamePasswordAuthenticationToken authenticationToken =
                           new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                  User loginUser= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                   la

               }
           }
       }catch (Exception e) {
           logger.error(e.getMessage());
       }

       filterChain.doFilter(request, response);

    }
    // lay token gui len tu request
    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if ((header != null && header.startsWith("Bearer "))) {
            return header.substring(7);
        }
        return null;
    }
}
