package com.company.designstudio.entity;

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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
