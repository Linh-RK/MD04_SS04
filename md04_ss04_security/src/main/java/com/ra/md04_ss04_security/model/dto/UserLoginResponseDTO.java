package com.ra.md04_ss04_security.model.dto;

import com.ra.md04_ss04_security.model.entity.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginResponseDTO {
    private String username;
    private String accessToken;
    private String typeToken;
    private Set<Role> roles ;

}
