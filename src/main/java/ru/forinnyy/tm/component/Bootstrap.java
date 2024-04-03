package ru.forinnyy.tm.component;

import ru.forinnyy.tm.api.*;
import ru.forinnyy.tm.constant.ArgumentConst;
import ru.forinnyy.tm.constant.CommandConst;
import ru.forinnyy.tm.controller.CommandController;
import ru.forinnyy.tm.controller.ProjectController;
import ru.forinnyy.tm.repository.CommandRepository;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.service.CommandService;
import ru.forinnyy.tm.service.ProjectService;
import ru.forinnyy.tm.util.TerminalUtil;

public final class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final IProjectController projectController = new ProjectController(projectService);

    private void processCommands() {
        System.out.println("*** *** WELCOME TO TASK MANAGER *** ***");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ENTER COMMAND: ");
            final String command = TerminalUtil.nextLine();
            processCommand(command);
        }
    }

    private void processArguments(final String[] args) {
        if (args == null || args.length < 1) return;
        processArgument(args[0]);
        exit();
    }

    private void exit() {
        System.exit(0);
    }

    private void processArgument(final String arg) {
        switch (arg) {
            case ArgumentConst.VERSION:
                commandController.showVersion();
                break;
            case ArgumentConst.ABOUT:
                commandController.showAbout();
                break;
            case ArgumentConst.HELP:
                commandController.showHelp();
                break;
            case ArgumentConst.INFO:
                commandController.showSystemInfo();
                break;
            default:
                commandController.showErrorArgument();
        }
    }

    private void processCommand(final String argument) {
        switch (argument) {
            case CommandConst.VERSION:
                commandController.showVersion();
                break;
            case CommandConst.ABOUT:
                commandController.showAbout();
                break;
            case CommandConst.HELP:
                commandController.showHelp();
                break;
            case CommandConst.EXIT:
                exit();
                break;
            case CommandConst.INFO:
                commandController.showSystemInfo();
                break;
            case CommandConst.PROJECT_LIST:
                projectController.showProjects();
                break;
            case CommandConst.PROJECT_CREATE:
                projectController.createProject();
                break;
            case CommandConst.PROJECT_CLEAR:
                projectController.clearProjects();
                break;
            default:
                commandController.showErrorCommand();
        }
    }

    public void run(final String... args) {
        processArguments(args);
        processCommands();
    }

}
