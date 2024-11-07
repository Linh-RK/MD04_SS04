package com.ra.md04_ss04_security.controller;

import com.ra.md04_ss04_security.model.dto.UserLoginRequestDTO;
import com.ra.md04_ss04_security.model.dto.UserLoginResponseDTO;
import com.ra.md04_ss04_security.model.dto.UserRegisterRequestDTO;
import com.ra.md04_ss04_security.model.dto.UserRegisterResponseDTO;
import com.ra.md04_ss04_security.service.AuthService;
import com.ra.md04_ss04_security.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserRegisterResponseDTO>> index() {
        List<UserRegisterResponseDTO> responseDTOs = userService.findAll();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> auth(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return new ResponseEntity<>(authService.login(userLoginRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> auth(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return new ResponseEntity<>(userService.registerUser(userRegisterRequestDTO), HttpStatus.OK);
    }



}
