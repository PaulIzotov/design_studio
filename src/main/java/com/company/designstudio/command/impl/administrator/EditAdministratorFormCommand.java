package com.company.designstudio.command.impl.administrator;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.AdministratorDto;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.service.AdministratorService;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

public class EditAdministratorFormCommand implements Command {
    private final AdministratorService service;

    public EditAdministratorFormCommand(AdministratorService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("administratorId"));
        AdministratorDto administratorDto = service.findById(id);
        req.setAttribute("administrator", administratorDto);
        return "jsp/edit_administrator_form.jsp";
    }

}
