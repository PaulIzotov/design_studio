package com.company.design_studio.service;

import com.company.design_studio.dto.DesignerDto;

import java.util.List;

public interface DesignerService extends AbstractService<Long, DesignerDto> {
    DesignerDto login(String email, String password);

    }
}
