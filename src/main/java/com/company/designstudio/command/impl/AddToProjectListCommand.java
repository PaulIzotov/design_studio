package com.company.designstudio.command.impl;

import com.company.designstudio.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class AddToProjectListCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        Long projectId = Long.parseLong(req.getParameter("projectId"));
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, Integer> list = (Map<Long, Integer>) session.getAttribute("list");
        if (list == null) {
            list = new HashMap<>();
        }
        list.merge(projectId, 1, Integer::sum);
        session.setAttribute("list", list);
        return "jsp/projects.jsp";
    }
}
