package com.company.designstudio.service.impl;

import com.company.designstudio.dao.ProjectDao;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.dto.ProjectDto;
import com.company.designstudio.entity.Designer;
import com.company.designstudio.entity.Project;
import com.company.designstudio.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public List<ProjectDto> findAll() {
        List<Project> list = projectDao.findAll();
        List<ProjectDto> listDto = new ArrayList<>();
        for (Project entity : list) {
            ProjectDto entityDto = toEntityDto(entity);
            listDto.add(entityDto);
        }
        return listDto;
    }

    @Override
    public ProjectDto findById(Long id) {
        Project entity = projectDao.findById(id);
        if (entity == null) {
            throw new RuntimeException("Can't create project with such id: " + id);
        }
        return toEntityDto(entity);
    }

    @Override
    public ProjectDto save(ProjectDto entityDto) {
        Project existing = projectDao.findById(entityDto.getId());
        if (existing != null) {
            throw new RuntimeException("Project already exists");
        }
        return toEntityDto(projectDao.save(toEntity(entityDto)));
    }

    @Override
    public ProjectDto update(ProjectDto entityDto) {
        Project existing = projectDao.findById(entityDto.getId());
        if (existing != null && existing.getId() != entityDto.getId()) {
            throw new RuntimeException("Project already exists");
        }
        return toEntityDto(projectDao.update(toEntity(entityDto)));
    }

    @Override
    public void delete(Long id) {
        if (!projectDao.delete(id)) {
            throw new RuntimeException("Couldn't delete project " + id);
        }
    }

    private ProjectDto toEntityDto(Project entity) {
        ProjectDto entityDto = new ProjectDto();
        entityDto.setId(entity.getId());
        entityDto.setAdmin(entity.getAdmin());
        entityDto.setDesigner(entity.getDesigner());
        entityDto.setPriceM2(entity.getPriceM2());
        entityDto.setSquare(entity.getSquare());
        return entityDto;
    }

    private Project toEntity(ProjectDto entityDto) {
        Project entity = new Project();
        entity.setId(entityDto.getId());
        entity.setAdmin(entityDto.getAdmin());
        entity.setDesigner(entityDto.getDesigner());
        entity.setPriceM2(entityDto.getPriceM2());
        entity.setSquare(entityDto.getSquare());
        return entity;
    }
}
