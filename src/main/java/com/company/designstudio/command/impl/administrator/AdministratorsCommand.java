package com.company.designstudio.command.impl.administrator;

import com.company.designstudio.command.Command;
import com.company.designstudio.command.impl.util.PagingUtil;
import com.company.designstudio.dto.AdministratorDto;
import com.company.designstudio.service.AdministratorService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AdministratorsCommand implements Command {
    private final AdministratorService service;
    private final PagingUtil pagingUtil;

    public AdministratorsCommand(AdministratorService service, PagingUtil pagingUtil) {
        this.service = service;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public String execute(HttpServletRequest req) {
        PagingUtil.Paging paging = pagingUtil.getPaging(req);
        List<AdministratorDto> administrators = service.findAll(paging.getLimit(), paging.getOffset());
        long totalEntities = service.count();
        long totalPages = pagingUtil.getTotalPages(totalEntities, paging.getLimit());
        req.setAttribute("currentPage", paging.getPage());
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("administrators", administrators);
        return "jsp/administrators.jsp";
    }
}
