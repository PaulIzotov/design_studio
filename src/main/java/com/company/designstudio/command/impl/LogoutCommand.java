package com.company.designstudio.command.impl;

import com.company.designstudio.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().removeAttribute("designer");
        return "index.jsp";
    }
}
