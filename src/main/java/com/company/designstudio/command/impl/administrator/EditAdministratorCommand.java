package com.company.designstudio.command.impl.administrator;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.AdministratorDto;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.entity.Role;
import com.company.designstudio.entity.Specialization;
import com.company.designstudio.service.AdministratorService;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

public class EditAdministratorCommand implements Command {
    private final AdministratorService service;

    public EditAdministratorCommand(AdministratorService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        AdministratorDto entity = service.findById(Long.parseLong(req.getParameter("id")));
        entity.setFirstName(req.getParameter("firstName"));
        entity.setLastName(req.getParameter("lastName"));
        entity.setEmail(req.getParameter("email"));
        entity.setRole(Role.valueOf(req.getParameter("role")));
        AdministratorDto updated = service.update(entity);
        req.setAttribute("administrator", updated);
        req.setAttribute("message", "Administrator data was saved");
        return "jsp/administrator.jsp";
    }
}
