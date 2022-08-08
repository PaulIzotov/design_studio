package com.company.design_studio.entity;

import lombok.Data;

@Data
public class Designer {
    private Long id;
    private String firstName;
    private String lastName;
    private Long experience;
    private String email;
    private String password;
    private Specialization specialization;
    private Role role;
}
