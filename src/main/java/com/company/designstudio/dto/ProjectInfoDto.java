package com.company.designstudio.dto;

import com.company.designstudio.entity.Designer;
import com.company.designstudio.entity.Project;
import com.company.designstudio.entity.Status;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectInfoDto {
    private Long id;
    private Designer designer;
    private Long projectId;
    private BigDecimal totalPrice;
    private Status status;
}
