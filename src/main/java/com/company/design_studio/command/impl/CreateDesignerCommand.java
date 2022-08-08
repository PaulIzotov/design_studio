package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import com.company.design_studio.dto.DesignerDto;
import com.company.design_studio.entity.Role;
import com.company.design_studio.entity.Specialization;
import com.company.design_studio.service.DesignerService;
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
        Specialization specialization = Specialization.valueOf(req.getParameter("specialization"));
        DesignerDto designer = new DesignerDto();
        designer.setFirstName(firstName);
        designer.setLastName(lastName);
        designer.setExperience(experience);
        designer.setEmail(email);
        designer.setPassword(password);
        designer.setSpecialization(specialization);
        designer.setRole(Role.DESIGNER);
        DesignerDto created = service.save(designer);
        req.setAttribute("designer", created);
        req.setAttribute("message", "New designer was created");
        return "jsp/designer.jsp";
    }
}
