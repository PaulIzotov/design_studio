package com.company.designstudio.command.impl.designer;

import com.company.designstudio.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class CreateDesignerFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/create_designer_form.jsp";
    }
}
