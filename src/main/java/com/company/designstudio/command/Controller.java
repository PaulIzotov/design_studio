package com.company.designstudio.command;

import com.company.designstudio.connection.DataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig(maxFileSize = Controller.MB * 5, maxRequestSize = Controller.MB * 100)
public class Controller extends HttpServlet {

    public static final String REDIRECT = "redirect:";
    public static final int MB = 1024 * 1024;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        Command commandInstance = CommandFactory.getInstance().getCommand(command);
        String page = commandInstance.execute(req);
        if (page.startsWith(REDIRECT)){
            resp.sendRedirect(req.getContextPath() + "/" + page.substring(REDIRECT.length()));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
    @Override
    public void destroy() {
        DataSource.INSTANCE.close();
    }


}
