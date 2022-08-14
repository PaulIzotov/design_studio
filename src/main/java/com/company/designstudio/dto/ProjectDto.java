package com.company.designstudio.dto;

import com.company.designstudio.entity.Administrator;
import com.company.designstudio.entity.Designer;
import com.company.designstudio.entity.ProjectInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class ProjectDto {
    private Long id;
    private Administrator admin;
    private Designer designer;
    private BigDecimal priceM2;
    private BigDecimal square;
    private List<ProjectInfoDto> details;
}
