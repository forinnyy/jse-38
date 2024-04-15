package ru.forinnyy.tm.component;

import ru.forinnyy.tm.api.*;
import ru.forinnyy.tm.constant.ArgumentConst;
import ru.forinnyy.tm.constant.CommandConst;
import ru.forinnyy.tm.controller.CommandController;
import ru.forinnyy.tm.controller.ProjectController;
import ru.forinnyy.tm.controller.TaskController;
import ru.forinnyy.tm.repository.CommandRepository;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.service.CommandService;
import ru.forinnyy.tm.service.ProjectService;
import ru.forinnyy.tm.service.TaskService;
import ru.forinnyy.tm.util.TerminalUtil;

public final class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final IProjectController projectController = new ProjectController(projectService);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final ITaskService taskService = new TaskService(taskRepository);

    private final ITaskController taskController = new TaskController(taskService);

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
            case CommandConst.TASK_CLEAR:
                taskController.clearTasks();
                break;
            case CommandConst.TASK_CREATE:
                taskController.createTask();
                break;
            case CommandConst.TASK_LIST:
                taskController.showTasks();
                break;
            case CommandConst.PROJECT_SHOW_BY_INDEX:
                projectController.showProjectByIndex();
                break;
            case CommandConst.PROJECT_SHOW_BY_ID:
                projectController.showProjectById();
                break;
            case CommandConst.PROJECT_UPDATE_BY_INDEX:
                projectController.updateProjectByIndex();
                break;
            case CommandConst.PROJECT_UPDATE_BY_ID:
                projectController.updateProjectById();
                break;
            case CommandConst.PROJECT_REMOVE_BY_INDEX:
                projectController.removeProjectByIndex();
                break;
            case CommandConst.PROJECT_REMOVE_BY_ID:
                projectController.removeProjectById();
                break;
            case CommandConst.PROJECT_CHANGE_STATUS_BY_INDEX:
                projectController.changeProjectStatusByIndex();
                break;
            case CommandConst.PROJECT_CHANGE_STATUS_BY_ID:
                projectController.changeProjectStatusById();
                break;
            case CommandConst.PROJECT_START_BY_INDEX:
                projectController.startProjectByIndex();
                break;
            case CommandConst.PROJECT_START_BY_ID:
                projectController.startProjectById();
                break;
            case CommandConst.PROJECT_COMPLETE_BY_INDEX:
                projectController.completeProjectByIndex();
                break;
            case CommandConst.PROJECT_COMPLETE_BY_ID:
                projectController.completeProjectById();
                break;
            case CommandConst.TASK_SHOW_BY_INDEX:
                taskController.showTaskByIndex();
                break;
            case CommandConst.TASK_SHOW_BY_ID:
                taskController.showTaskById();
                break;
            case CommandConst.TASK_UPDATE_BY_INDEX:
                taskController.updateTaskByIndex();
                break;
            case CommandConst.TASK_UPDATE_BY_ID:
                taskController.updateTaskById();
                break;
            case CommandConst.TASK_REMOVE_BY_INDEX:
                taskController.removeTaskByIndex();
                break;
            case CommandConst.TASK_REMOVE_BY_ID:
                taskController.removeTaskById();
                break;
            case CommandConst.TASK_CHANGE_STATUS_BY_INDEX:
                taskController.changeTaskStatusByIndex();
                break;
            case CommandConst.TASK_CHANGE_STATUS_BY_ID:
                taskController.changeTaskStatusById();
                break;
            case CommandConst.TASK_START_BY_INDEX:
                taskController.startTaskByIndex();
                break;
            case CommandConst.TASK_START_BY_ID:
                taskController.startTaskById();
                break;
            case CommandConst.TASK_COMPLETE_BY_INDEX:
                taskController.completeTaskByIndex();
                break;
            case CommandConst.TASK_COMPLETE_BY_ID:
                taskController.completeTaskById();
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
