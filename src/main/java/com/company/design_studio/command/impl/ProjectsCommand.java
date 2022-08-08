package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import com.company.design_studio.dto.DesignerDto;
import com.company.design_studio.dto.ProjectDto;
import com.company.design_studio.service.DesignerService;
import com.company.design_studio.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ProjectsCommand implements Command {
    private final ProjectService service;

    public ProjectsCommand(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<ProjectDto> projects = service.findAll();
        req.setAttribute("projects", projects);
        return "jsp/projects.jsp";
    }
}
