package com.company.design_studio.dto;

import com.company.design_studio.entity.Role;
import com.company.design_studio.entity.Specialization;
import lombok.Data;

@Data
public class DesignerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long experience;
    private String email;
    private String password;
    private Specialization specialization;
    private Role role;
}
