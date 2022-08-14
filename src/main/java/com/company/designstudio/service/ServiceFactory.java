package com.company.designstudio.service;

import com.company.designstudio.dao.AdministratorDao;
import com.company.designstudio.dao.DesignerDao;
import com.company.designstudio.dao.ProjectDao;
import com.company.designstudio.service.impl.AdministratorServiceImpl;
import com.company.designstudio.service.impl.DesignerServiceImpl;
import com.company.designstudio.service.impl.ProjectServiceImpl;
import com.company.designstudio.dao.DaoFactory;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private final Map<Class<?>, Object> map;
    public static final ServiceFactory INSTANCE = new ServiceFactory();

    public ServiceFactory() {
        map = new HashMap<>();
        map.put(DesignerService.class, new DesignerServiceImpl(DaoFactory.INSTANCE.getDao(DesignerDao.class)));
        map.put(AdministratorService.class, new AdministratorServiceImpl(DaoFactory.INSTANCE.getDao(AdministratorDao.class)));
        map.put(ProjectService.class, new ProjectServiceImpl(DaoFactory.INSTANCE.getDao(ProjectDao.class)));
    }
    @SuppressWarnings("unchecked")
    public <T> T getService (Class<T> clazz) {
        return (T) map.get(clazz);
    }


}
