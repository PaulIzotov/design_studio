package com.company.designstudio.dto;

import com.company.designstudio.entity.Role;
import lombok.Data;

@Data
public class AdministratorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
