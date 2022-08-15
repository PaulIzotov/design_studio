package com.company.designstudio.command.impl.designer;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.entity.Role;
import com.company.designstudio.entity.Specialization;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

public class EditDesignerCommand implements Command {
    private final DesignerService service;

    public EditDesignerCommand(DesignerService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        DesignerDto entity = service.findById(Long.parseLong(req.getParameter("id")));
        entity.setFirstName(req.getParameter("firstName"));
        entity.setLastName(req.getParameter("lastName"));
        entity.setEmail(req.getParameter("email"));
        entity.setExperience(Long.parseLong(req.getParameter("experience")));
        entity.setSpecialization(Specialization.valueOf(req.getParameter("specialization")));
        entity.setRole(Role.valueOf(req.getParameter("role")));
        DesignerDto updated = service.update(entity);
        req.setAttribute("designer", updated);
        req.setAttribute("message", "Designer data was saved");
        return "jsp/designer.jsp";
    }
}
