package com.company.designstudio.dto;

import com.company.designstudio.entity.Role;
import com.company.designstudio.entity.Specialization;
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
