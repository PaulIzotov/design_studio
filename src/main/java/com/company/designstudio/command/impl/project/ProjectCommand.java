package com.company.designstudio.command.impl.project;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.ProjectDto;
import com.company.designstudio.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;

public class ProjectCommand implements Command {
    private final ProjectService service;

    public ProjectCommand(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        ProjectDto project = service.findById(id);
        req.setAttribute("project", project);
        return "jsp/project.jsp";
    }
}
