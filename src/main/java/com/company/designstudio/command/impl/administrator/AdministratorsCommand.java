package com.company.designstudio.command.impl.administrator;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.AdministratorDto;
import com.company.designstudio.service.AdministratorService;
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
