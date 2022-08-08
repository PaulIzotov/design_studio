package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LoginFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/login_form.jsp";
    }
}
