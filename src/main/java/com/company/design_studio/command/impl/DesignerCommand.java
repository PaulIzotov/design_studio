package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import com.company.design_studio.dto.DesignerDto;
import com.company.design_studio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

public class DesignerCommand implements Command {
    private final DesignerService service;

    public DesignerCommand(DesignerService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        DesignerDto designer = service.findById(id);
        req.setAttribute("designer", designer);
        return "jsp/designer.jsp";
    }
}
