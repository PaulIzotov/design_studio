package com.company.design_studio.command.impl;

import com.company.design_studio.command.Command;
import com.company.design_studio.dto.DesignerDto;
import com.company.design_studio.service.DesignerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final DesignerService service;

    public LoginCommand(DesignerService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        DesignerDto designer = service.login(email, password);
        HttpSession session = req.getSession();
        session.setAttribute("designer", designer);
        req.setAttribute("message", "Successful login");
        return "index.jsp";
    }
}
