package com.ra.md04_ss04_security.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
