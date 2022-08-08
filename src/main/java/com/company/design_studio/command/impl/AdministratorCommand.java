package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import com.company.design_studio.dto.AdministratorDto;
import com.company.design_studio.dto.DesignerDto;
import com.company.design_studio.service.AdministratorService;
import com.company.design_studio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

public class AdministratorCommand implements Command {
    private final AdministratorService service;

    public AdministratorCommand(AdministratorService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        AdministratorDto administrator = service.findById(id);
        req.setAttribute("administrator", administrator);
        return "jsp/administrator.jsp";
    }
}
