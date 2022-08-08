package com.company.design_studio.entity;

import lombok.Data;
import com.company.design_studio.entity.Designer;
@Data
public class ProjectInfo {
    private Long id;
    private Designer designer;
    private Double total_price;
    private Status status;
}
