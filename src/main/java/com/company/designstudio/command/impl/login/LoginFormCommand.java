package com.company.designstudio.command.impl.login;

import com.company.designstudio.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LoginFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/login_form.jsp";
    }
}
