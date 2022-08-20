package com.company.designstudio.command.impl.administrator;

import com.company.designstudio.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class CreateAdministratorFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/create_administrator_form.jsp";
    }
}
