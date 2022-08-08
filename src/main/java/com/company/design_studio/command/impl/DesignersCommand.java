package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import com.company.design_studio.dto.DesignerDto;
import com.company.design_studio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class DesignersCommand implements Command {
    private final DesignerService service;

    public DesignersCommand(DesignerService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<DesignerDto> designers = service.findAll();
        req.setAttribute("designers", designers);
        return "jsp/designers.jsp";
    }
}
