package com.company.design_studio.dto;

import com.company.design_studio.entity.Role;
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
