package com.company.designstudio.command.impl;

import com.company.designstudio.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "index.jsp";
    }
}
