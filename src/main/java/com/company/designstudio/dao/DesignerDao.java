package com.company.designstudio.dao;

import com.company.designstudio.entity.Designer;

public interface DesignerDao extends AbstractDao<Long, Designer>{

    Designer findByEmail(String email);
}
