package com.company.designstudio.service;

import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.entity.Designer;

public interface DesignerService extends AbstractService<Long, DesignerDto> {
    DesignerDto login(String email, String password);

    DesignerDto findByEmail(String email);

}

