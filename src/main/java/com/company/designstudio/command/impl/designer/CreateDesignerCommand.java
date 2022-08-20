package com.company.designstudio.command.impl.designer;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.entity.Role;
import com.company.designstudio.entity.Specialization;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.UUID;

public class CreateDesignerCommand implements Command {
    private final DesignerService service;

    public CreateDesignerCommand(DesignerService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        try {
            Part part = req.getPart("avatar");
            if (part != null) {
                String fileName = UUID.randomUUID() + "_" + part.getSubmittedFileName();
                part.write("D:\\" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }


        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Long experience = Long.valueOf(req.getParameter("experience"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Specialization specialization = Specialization.valueOf(req.getParameter("specialization"));
        Role role = Role.valueOf(req.getParameter("role"));
        DesignerDto designer = new DesignerDto();
        designer.setFirstName(firstName);
        designer.setLastName(lastName);
        designer.setExperience(experience);
        designer.setEmail(email);
        designer.setPassword(password);
        designer.setSpecialization(specialization);
        designer.setRole(role);
        DesignerDto created = service.save(designer);
        req.setAttribute("designer", created);
        req.setAttribute("message", "New designer was created");
        return "jsp/designer.jsp";
    }
}