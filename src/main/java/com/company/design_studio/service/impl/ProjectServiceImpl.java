package com.company.design_studio.service.impl;

import com.company.design_studio.dao.ProjectDao;
import com.company.design_studio.dto.ProjectDto;
import com.company.design_studio.entity.Project;
import com.company.design_studio.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

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
        entityDto.setAdministrator(entity.getAdministrator());
        entityDto.setDesigner(entity.getDesigner());
        entityDto.setPrice_for_m2(entity.getPrice_for_m2());
        entityDto.setSquare(entity.getSquare());
        return entityDto;
    }

    private Project toEntity(ProjectDto entityDto) {
        Project entity = new Project();
        entity.setId(entityDto.getId());
        entity.setAdministrator(entityDto.getAdministrator());
        entity.setDesigner(entityDto.getDesigner());
        entity.setPrice_for_m2(entityDto.getPrice_for_m2());
        entity.setSquare(entityDto.getSquare());
        return entity;
    }
}
