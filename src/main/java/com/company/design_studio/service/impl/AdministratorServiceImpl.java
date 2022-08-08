package com.company.design_studio.service.impl;

import com.company.design_studio.dao.AdministratorDao;
import com.company.design_studio.dto.AdministratorDto;
import com.company.design_studio.entity.Role;
import com.company.design_studio.entity.Administrator;
import com.company.design_studio.service.AdministratorService;

import java.util.ArrayList;
import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorDao administratorDao;

    public AdministratorServiceImpl(AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

    @Override
    public List<AdministratorDto> findAll() {
        List<Administrator> list = administratorDao.findAll();
        List<AdministratorDto> listDto = new ArrayList<>();
        for (Administrator entity : list) {
            AdministratorDto entityDto = toEntityDto(entity);
            listDto.add(entityDto);
        }
        return listDto;
    }

    @Override
    public AdministratorDto findById(Long id) {
        Administrator entity = administratorDao.findById(id);
        if (entity == null) {
            throw new RuntimeException("Can't create administrator with such id: " + id);
        }
        return toEntityDto(entity);
    }

    @Override
    public AdministratorDto save(AdministratorDto entityDto) {
        Administrator existing = administratorDao.findById(entityDto.getId());
        if (existing != null) {
            throw new RuntimeException("Administrator already exists");
        }
        return toEntityDto(administratorDao.save(toEntity(entityDto)));
    }

    @Override
    public AdministratorDto update(AdministratorDto entityDto) {
        Administrator existing = administratorDao.findById(entityDto.getId());
        if (existing != null && existing.getId() != entityDto.getId()) {
            throw new RuntimeException("Administrator already exists");
        }
        return toEntityDto(administratorDao.update(toEntity(entityDto)));
    }

    @Override
    public void delete(Long id) {
        if (!administratorDao.delete(id)) {
            throw new RuntimeException("Couldn't delete administrator " + id);
        }
    }

    private AdministratorDto toEntityDto(Administrator entity) {
        AdministratorDto entityDto = new AdministratorDto();
        entityDto.setId(entity.getId());
        entityDto.setFirstName(entity.getFirstName());
        entityDto.setLastName(entity.getLastName());
        entityDto.setEmail(entity.getEmail());
        entityDto.setPassword(entity.getPassword());
        entityDto.setRole(Role.valueOf(entity.getRole().toString()));
        return entityDto;
    }

    private Administrator toEntity(AdministratorDto entityDto) {
        Administrator entity = new Administrator();
        entity.setId(entityDto.getId());
        entity.setFirstName(entityDto.getFirstName());
        entity.setLastName(entityDto.getLastName());
        entity.setEmail(entityDto.getEmail());
        entity.setPassword(entityDto.getPassword());
        Role dtoRole = entityDto.getRole();
        Role role = Role.valueOf(dtoRole.toString());
        entity.setRole(role);
        return entity;
    }
}
