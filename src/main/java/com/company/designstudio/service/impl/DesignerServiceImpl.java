package com.company.designstudio.service.impl;

import com.company.designstudio.dao.DesignerDao;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.entity.Designer;
import com.company.designstudio.entity.Specialization;
import com.company.designstudio.entity.Role;
import com.company.designstudio.service.DesignerService;

import java.util.ArrayList;
import java.util.List;

public class DesignerServiceImpl implements DesignerService {
    private final DesignerDao designerDao;

    public DesignerServiceImpl(DesignerDao designerDao) {
        this.designerDao = designerDao;
    }

    @Override
    public List<DesignerDto> findAll(int limit, long offset) {
        List<Designer> list = designerDao.findAll(limit, offset);
        List<DesignerDto> listDto = new ArrayList<>();
        for (Designer entity : list) {
            DesignerDto entityDto = toEntityDto(entity);
            listDto.add(entityDto);
        }
        return listDto;
    }

    @Override
    public DesignerDto findById(Long id) {
        Designer entity = designerDao.findById(id);
        if (entity == null) {
            throw new RuntimeException("Can't find designer with such id: " + id);
        }
        return toEntityDto(entity);
    }

    @Override
    public DesignerDto findByEmail(String email) {
        Designer entity = designerDao.findByEmail(email);
        if (entity == null) {
            throw new RuntimeException("Can't create designer with such email: " + email);
        }
        return toEntityDto(entity);
    }

    @Override
    public DesignerDto save(DesignerDto entityDto) {
        Designer existing = designerDao.findByEmail(entityDto.getEmail());
        if (existing != null) {
            throw new RuntimeException("Designer already exists");
        }
        return toEntityDto(designerDao.save(toEntity(entityDto)));
    }

    @Override
    public DesignerDto update(DesignerDto entityDto) {
        Designer existing = designerDao.findById(entityDto.getId());
        if (existing != null && existing.getId() != entityDto.getId()) {
            throw new RuntimeException("Designer already exists");
        }
        return toEntityDto(designerDao.update(toEntity(entityDto)));
    }

    @Override
    public void delete(Long id) {
        if (!designerDao.delete(id)) {
            throw new RuntimeException("Couldn't delete book " + id);
        }
    }

    @Override
    public long count() {
        return designerDao.count();
    }

    public DesignerDto toEntityDto(Designer entity) {
        DesignerDto entityDto = new DesignerDto();
        entityDto.setId(entity.getId());
        entityDto.setFirstName(entity.getFirstName());
        entityDto.setLastName(entity.getLastName());
        entityDto.setExperience(entity.getExperience());
        entityDto.setEmail(entity.getEmail());
        entityDto.setPassword(entity.getPassword());
        entityDto.setSpecialization(Specialization.valueOf(entity.getSpecialization().toString()));
        entityDto.setRole(Role.valueOf(entity.getRole().toString()));
        return entityDto;
    }

    private Designer toEntity(DesignerDto entityDto) {
        Designer entity = new Designer();
        entity.setId(entityDto.getId());
        entity.setFirstName(entityDto.getFirstName());
        entity.setLastName(entityDto.getLastName());
        entity.setExperience(entityDto.getExperience());
        entity.setEmail(entityDto.getEmail());
        entity.setPassword(entityDto.getPassword());
        Specialization dtoSpecialization = entityDto.getSpecialization();
        Specialization specialization = Specialization.valueOf(dtoSpecialization.toString());
        entity.setSpecialization(specialization);
        Role dtoRole = entityDto.getRole();
        Role role = Role.valueOf(dtoRole.toString());
        entity.setRole(role);
        return entity;
    }

    @Override
    public DesignerDto login(String email, String password) {
        Designer entity = designerDao.findByEmail(email);
        if (entity == null) {
            throw new RuntimeException("Can't create designer with such email: " + email);
        }
        if (!entity.getPassword().equals(password)){
            throw new RuntimeException("Wrong password with email: " + email);
        }
        return toEntityDto(entity);
    }


}
