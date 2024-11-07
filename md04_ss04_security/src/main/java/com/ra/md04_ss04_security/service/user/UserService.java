package com.ra.md04_ss04_security.service.user;

import com.ra.md04_ss04_security.model.dto.UserLoginResponseDTO;
import com.ra.md04_ss04_security.model.dto.UserRegisterRequestDTO;
import com.ra.md04_ss04_security.model.dto.UserRegisterResponseDTO;
import com.ra.md04_ss04_security.model.entity.User;

import java.util.List;

public interface UserService {
    UserRegisterResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO);
    boolean checkNameExist(String value);
    List<UserRegisterResponseDTO> findAll();

    UserLoginResponseDTO findById(Long id);
}
