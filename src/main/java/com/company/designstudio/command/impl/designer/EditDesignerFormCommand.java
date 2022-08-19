package com.company.designstudio.command.impl.designer;

import com.company.designstudio.command.Command;
import com.company.designstudio.dto.DesignerDto;
import com.company.designstudio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;

public class EditDesignerFormCommand implements Command {
    private final DesignerService service;

    public EditDesignerFormCommand(DesignerService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("designerId"));
        DesignerDto designerDto = service.findById(id);
        req.setAttribute("designer", designerDto);
        return "jsp/edit_designer_form.jsp";
    }

}
