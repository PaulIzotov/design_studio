package com.company.design_studio.dto;

import com.company.design_studio.entity.Administrator;
import com.company.design_studio.entity.Designer;
import lombok.Data;


@Data
public class ProjectDto {
    private Long id;
    private Administrator administrator;
    private Designer designer;
    private Double price_for_m2;
    private Double square;
}
