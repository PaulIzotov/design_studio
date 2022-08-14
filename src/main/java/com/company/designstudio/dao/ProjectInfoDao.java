package com.company.designstudio.dao;

import com.company.designstudio.entity.ProjectInfo;

import java.util.List;

public interface ProjectInfoDao extends AbstractDao<Long, ProjectInfo>{
    List<ProjectInfo> findByProjectId(Long id);
}
