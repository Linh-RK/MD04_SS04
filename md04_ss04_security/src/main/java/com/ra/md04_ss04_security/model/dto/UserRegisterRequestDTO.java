package com.ra.md04_ss04_security.model.dto;

import com.ra.md04_ss04_security.validation.UniqueUser;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterRequestDTO {
    @NotBlank
    @UniqueUser
    private String username;
    @NotBlank
    private String password;
}
