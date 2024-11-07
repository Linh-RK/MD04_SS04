package com.ra.md04_ss04_security.service.user;

import com.ra.md04_ss04_security.model.dto.UserLoginResponseDTO;
import com.ra.md04_ss04_security.model.dto.UserRegisterRequestDTO;
import com.ra.md04_ss04_security.model.dto.UserRegisterResponseDTO;
import com.ra.md04_ss04_security.model.entity.Role;
import com.ra.md04_ss04_security.model.entity.User;
import com.ra.md04_ss04_security.repository.RoleRepository;
import com.ra.md04_ss04_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findRoleByRoleName("USER");
        roles.add(role);
        User user = User.builder()
                .userName(userRegisterRequestDTO.getUsername())
                .password(passwordEncoder.encode(userRegisterRequestDTO.getPassword()))
                .status(true)
                .build();
        User userNew = userRepository.save(user);

        return UserRegisterResponseDTO.builder()
                .username(userNew.getUserName())
                .build();
    }

    @Override
    public boolean checkNameExist(String username) {
        List<User> users = userRepository.findByUserName(username);
        // isEmpty() nếu rỗng trả về  true khác rỗng trar ve false
        return !users.isEmpty();
    }

    //--------chua-------
    @Override
    public List<UserRegisterResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserRegisterResponseDTO> userLoginResponseDTOS;
        userLoginResponseDTOS = users.stream().map(user -> {
            return UserRegisterResponseDTO.builder()
                   .username(user.getUserName())
                   .build();
        }).toList();
        return userLoginResponseDTOS;
    }

    @Override
    public UserLoginResponseDTO findById(Long id) {
       User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException( "Customer not found"));
       return UserLoginResponseDTO.builder()
               .username(user.getUserName())
               .build();
    }

}
