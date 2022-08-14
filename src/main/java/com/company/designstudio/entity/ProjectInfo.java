package com.company.designstudio.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectInfo {
    private Long id;
    private Designer designer;
    private Long projectId;
    private BigDecimal totalPrice;
    private Status status;
}
