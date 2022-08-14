package com.company.designstudio.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Project {
    private Long id;
    private Administrator admin;
    private Designer designer;
    private BigDecimal priceM2;
    private BigDecimal square;
    private List<ProjectInfo> details;
}
