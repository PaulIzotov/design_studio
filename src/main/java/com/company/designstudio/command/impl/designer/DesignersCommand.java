package com.company.designstudio.command.impl.designer;

import com.company.designstudio.command.Command;
import com.company.designstudio.command.impl.util.PagingUtil;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class DesignersCommand implements Command {
    private final DesignerService service;
    private final PagingUtil pagingUtil;

    public DesignersCommand(DesignerService service, PagingUtil pagingUtil) {
        this.service = service;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public String execute(HttpServletRequest req) {
        PagingUtil.Paging paging = pagingUtil.getPaging(req);
        List<DesignerDto> designers = service.findAll(paging.getLimit(), paging.getOffset());
        long totalEntities = service.count();
        long totalPages = pagingUtil.getTotalPages(totalEntities, paging.getLimit());
        req.setAttribute("currentPage", paging.getPage());
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("designers", designers);
        return "jsp/designers.jsp";
    }
}
