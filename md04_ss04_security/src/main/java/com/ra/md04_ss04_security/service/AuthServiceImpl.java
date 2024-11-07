package com.ra.md04_ss04_security.service;

import com.ra.md04_ss04_security.model.dto.UserLoginRequestDTO;
import com.ra.md04_ss04_security.model.dto.UserLoginResponseDTO;
import com.ra.md04_ss04_security.repository.UserRepository;
import com.ra.md04_ss04_security.security.UserPrinciple;
import com.ra.md04_ss04_security.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDTO.getUsername(),
                        userLoginRequestDTO.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userPrinciple);
        return UserLoginResponseDTO.builder()
                .accessToken(token)
                .typeToken("Bearer ")
                .username(userPrinciple.getUsername())
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }
}
