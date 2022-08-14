package com.company.designstudio.entity;

import lombok.Data;

@Data
public class Administrator {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
