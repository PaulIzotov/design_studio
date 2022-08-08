package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import com.company.design_studio.dto.AdministratorDto;
import com.company.design_studio.service.AdministratorService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AdministratorsCommand implements Command {
    private final AdministratorService service;

    public AdministratorsCommand(AdministratorService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<AdministratorDto> administrators = service.findAll();
        req.setAttribute("administrators", administrators);
        return "jsp/administrators.jsp";
    }
}
