package com.company.designstudio.command.impl.administrator;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.AdministratorDto;
import com.company.designstudio.service.AdministratorService;
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
