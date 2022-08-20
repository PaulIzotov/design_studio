package com.company.designstudio.command.impl.administrator;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.AdministratorDto;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.entity.Role;
import com.company.designstudio.entity.Specialization;
import com.company.designstudio.service.AdministratorService;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.UUID;

public class CreateAdministratorCommand implements Command {
    private final AdministratorService service;

    public CreateAdministratorCommand(AdministratorService service) {
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
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }


        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));
        AdministratorDto administrator = new AdministratorDto();
        administrator.setFirstName(firstName);
        administrator.setLastName(lastName);
        administrator.setEmail(email);
        administrator.setPassword(password);
        administrator.setRole(role);
        AdministratorDto created = service.save(administrator);
        req.setAttribute("administrator", created);
        req.setAttribute("message", "New administrator was created");
        return "jsp/administrator.jsp";
    }
}