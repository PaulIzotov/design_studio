package com.company.designstudio.command.impl;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.entity.Role;
import com.company.designstudio.entity.Specialization;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

public class CreateDesignerCommand implements Command {
    private final DesignerService service;

    public CreateDesignerCommand(DesignerService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Long experience = Long.valueOf(req.getParameter("experience"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        DesignerDto designer = new DesignerDto();
        designer.setFirstName(firstName);
        designer.setLastName(lastName);
        designer.setExperience(experience);
        designer.setEmail(email);
        designer.setPassword(password);
        designer.setSpecialization(Specialization.INTERIOR);
        designer.setRole(Role.DESIGNER);
        DesignerDto created = service.save(designer);
        req.setAttribute("designer", created);
        req.setAttribute("message", "New designer was created");
        return "jsp/designer.jsp";
    }
}