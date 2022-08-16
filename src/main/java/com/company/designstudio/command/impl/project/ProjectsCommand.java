package com.company.designstudio.command.impl.project;

import com.company.designstudio.command.Command;
import com.company.designstudio.command.impl.util.PagingUtil;
import com.company.designstudio.dto.ProjectDto;
import com.company.designstudio.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ProjectsCommand implements Command {
    private final ProjectService service;
    private final PagingUtil pagingUtil;

    public ProjectsCommand(ProjectService service, PagingUtil pagingUtil) {
        this.service = service;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public String execute(HttpServletRequest req) {
        PagingUtil.Paging paging = pagingUtil.getPaging(req);
        List<ProjectDto> projects = service.findAll(paging.getLimit(), paging.getOffset());
        long totalEntities = service.count();
        long totalPages = pagingUtil.getTotalPages(totalEntities, paging.getLimit());
        req.setAttribute("currentPage", paging.getPage());
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("projects", projects);
        return "jsp/projects.jsp";
    }
}
