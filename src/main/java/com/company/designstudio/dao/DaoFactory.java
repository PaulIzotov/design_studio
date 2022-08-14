package com.company.designstudio.dao;

import com.company.designstudio.connection.DataSource;
import com.company.designstudio.dao.impl.AdministratorDaoImpl;
import com.company.designstudio.dao.impl.DesignerDaoImpl;
import com.company.designstudio.dao.impl.ProjectDaoImpl;
import com.company.designstudio.dao.impl.ProjectInfoDaoImpl;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private final Map<Class<?>, Object> map;
    public static final DaoFactory INSTANCE = new DaoFactory();

    public DaoFactory() {
        map = new HashMap<>();
        map.put(DesignerDao.class, new DesignerDaoImpl(DataSource.INSTANCE));
        map.put(AdministratorDao.class, new AdministratorDaoImpl(DataSource.INSTANCE));
        map.put(ProjectInfoDao.class, new ProjectInfoDaoImpl(DataSource.INSTANCE,
                getDao(DesignerDao.class)));
        map.put(ProjectDao.class, new ProjectDaoImpl(DataSource.INSTANCE,
                getDao(DesignerDao.class),
                getDao(AdministratorDao.class),
                getDao(ProjectInfoDao.class)));
    }
    @SuppressWarnings("unchecked")
    public <T> T getDao (Class<T> clazz) {
        return (T) map.get(clazz);
    }
}
