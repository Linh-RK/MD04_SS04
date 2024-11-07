package com.ra.md04_ss04_security.service;

import com.ra.md04_ss04_security.model.dto.UserLoginRequestDTO;
import com.ra.md04_ss04_security.model.dto.UserLoginResponseDTO;

public interface AuthService {
    UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);

}
