package com.company.design_studio.entity;

import lombok.Data;

@Data
public class Administrator {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
