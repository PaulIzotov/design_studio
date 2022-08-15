package com.company.designstudio.command.impl.project;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.ProjectDto;
import com.company.designstudio.service.ProjectService;
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
