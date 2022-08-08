package com.company.design_studio.command;

import com.company.design_studio.command.impl.*;
import com.company.design_studio.service.AdministratorService;
import com.company.design_studio.service.DesignerService;
import com.company.design_studio.service.ProjectService;
import com.company.design_studio.service.ServiceFactory;

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
        commands.put("designers", new DesignersCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("designer", new DesignerCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("administrators", new AdministratorsCommand(ServiceFactory.INSTANCE.getService(AdministratorService.class)));
        commands.put("administrator", new AdministratorCommand(ServiceFactory.INSTANCE.getService(AdministratorService.class)));
        commands.put("projects", new ProjectsCommand(ServiceFactory.INSTANCE.getService(ProjectService.class)));
        commands.put("project", new ProjectCommand(ServiceFactory.INSTANCE.getService(ProjectService.class)));
        commands.put("create_designer_form", new CreateDesignerFormCommand());
        commands.put("create_designer", new CreateDesignerCommand(ServiceFactory.INSTANCE.getService(DesignerService.class)));
        commands.put("error", new ErrorCommand());
    }

    public Command getCommand(String command) {
        Command commandInstance = commands.get(command);
        if (commandInstance == null) {
            commandInstance = commands.get("error");
        }
        return commandInstance;
    }
}
