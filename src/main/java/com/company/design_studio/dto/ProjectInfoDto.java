package com.company.design_studio.dto;

import com.company.design_studio.entity.Designer;
import com.company.design_studio.entity.Status;
import lombok.Data;

@Data
public class ProjectInfoDto {
    private Long id;
    private Designer designer;
    private Double total_price;
    private Status status;
}
