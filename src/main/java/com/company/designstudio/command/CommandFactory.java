package com.company.designstudio.command;

import com.company.designstudio.command.impl.*;
import com.company.designstudio.command.impl.administrator.AdministratorCommand;
import com.company.designstudio.command.impl.administrator.AdministratorsCommand;
import com.company.designstudio.command.impl.designer.*;
import com.company.designstudio.command.impl.project.ProjectCommand;
import com.company.designstudio.command.impl.project.ProjectsCommand;
import com.company.designstudio.command.impl.util.PagingUtil;
import com.company.designstudio.service.AdministratorService;
import com.company.designstudio.service.DesignerService;
import com.company.designstudio.service.ProjectService;
import com.company.designstudio.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final CommandFactory INSTANCE = new CommandFactory();
    public final Map<String, Command> commands;

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    private CommandFactory() {
        commands = new HashMap<>();
        commands.put("designers", new DesignersCommand(ServiceFactory.INSTANCE.getService(DesignerService.class),
                PagingUtil.INSTANCE));
        commands.put("designer", new DesignerCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("administrators", new AdministratorsCommand(ServiceFactory.INSTANCE.getService(AdministratorService.class),
                PagingUtil.INSTANCE));
        commands.put("administrator", new AdministratorCommand(ServiceFactory.INSTANCE.getService(AdministratorService.class)));
        commands.put("projects", new ProjectsCommand(ServiceFactory.INSTANCE.getService(ProjectService.class),
                PagingUtil.INSTANCE));
        commands.put("project", new ProjectCommand(ServiceFactory.INSTANCE.getService(ProjectService.class)));
        commands.put("create_designer_form", new CreateDesignerFormCommand());
        commands.put("create_designer", new CreateDesignerCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("error", new ErrorCommand());
        commands.put("login_form", new LoginFormCommand());
        commands.put("login", new LoginCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("logout", new LogoutCommand());
        commands.put("edit_designer_form", new EditDesignerFormCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("edit_designer", new EditDesignerCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("home", new HomeCommand());
    }

    public Command getCommand(String command) {
        Command commandInstance = commands.get(command);
        if (commandInstance == null) {
            commandInstance = commands.get("error");
        }
        return commandInstance;
    }
}
