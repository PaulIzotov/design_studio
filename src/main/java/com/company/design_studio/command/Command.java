package com.company.design_studio.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest req);
}
