package com.company.design_studio.entity;

import lombok.Data;

import java.util.List;
@Data
public class Project {
    private Long id;
    private Administrator administrator;
    private Designer designer;
    private Double price_for_m2;
    private Double square;
}
