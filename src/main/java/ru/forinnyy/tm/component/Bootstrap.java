package ru.forinnyy.tm.component;

import ru.forinnyy.tm.api.controller.ICommandController;
import ru.forinnyy.tm.api.controller.IProjectController;
import ru.forinnyy.tm.api.controller.IProjectTaskController;
import ru.forinnyy.tm.api.controller.ITaskController;
import ru.forinnyy.tm.api.repository.ICommandRepository;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.service.*;
import ru.forinnyy.tm.constant.ArgumentConst;
import ru.forinnyy.tm.constant.CommandConst;
import ru.forinnyy.tm.controller.CommandController;
import ru.forinnyy.tm.controller.ProjectController;
import ru.forinnyy.tm.controller.ProjectTaskController;
import ru.forinnyy.tm.controller.TaskController;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.AbstractException;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.system.ArgumentNotSupportedException;
import ru.forinnyy.tm.exception.system.CommandNotSupportedException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.repository.CommandRepository;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.service.*;
import ru.forinnyy.tm.util.TerminalUtil;

public final class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final ITaskService taskService = new TaskService(taskRepository);

    private final ITaskController taskController = new TaskController(taskService);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    private final IProjectTaskController projectTaskController = new ProjectTaskController(projectTaskService);

    private final IProjectController projectController = new ProjectController(projectService, projectTaskService);

    private final ILoggerService loggerService = new LoggerService();

    private void initDemoData() throws AbstractFieldException, AbstractEntityException {
        projectService.add(new Project("TEST PROJECT", Status.IN_PROGRESS));
        projectService.add(new Project("DEMO PROJECT", Status.NOT_STARTED));
        projectService.add(new Project("ALPHA PROJECT", Status.IN_PROGRESS));
        projectService.add(new Project("BETA PROJECT", Status.COMPLETED));

        taskService.create("MEGA TASK");
        taskService.create("BETA TASK");
    }

    private void processCommands() throws AbstractEntityException, CommandNotSupportedException, AbstractFieldException {
        System.out.println("*** *** WELCOME TO TASK MANAGER *** ***");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ENTER COMMAND: ");
            final String command = TerminalUtil.nextLine();
            processCommand(command);
        }
    }

    private boolean processArguments(final String[] args) throws ArgumentNotSupportedException {
        if (args == null || args.length < 1) return false;
        processArgument(args[0]);
        return true;
    }

    private void exit() {
        System.exit(0);
    }

    private void processArgument(final String arg) throws ArgumentNotSupportedException {
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
                throw new ArgumentNotSupportedException(arg);
        }
    }

    private void processCommand(final String command) throws CommandNotSupportedException, AbstractFieldException, AbstractEntityException {
        switch (command) {
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
            case CommandConst.TASK_BIND_TO_PROJECT:
                projectTaskController.bindTaskToProject();
                break;
            case CommandConst.TASK_UNBIND_FROM_PROJECT:
                projectTaskController.unbindTaskToProject();
                break;
            default:
                throw new CommandNotSupportedException(command);
        }
    }

    private void initLogger() {
        loggerService.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                loggerService.info("** TASK-MANAGER IS SHUTTING DOWN **");
            }
        });
    }

    public void run(final String[] args) throws AbstractException {
        if (processArguments(args)) System.exit(0);

        initDemoData();
        initLogger();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("ENTER COMMAND:");
                final String command = TerminalUtil.nextLine();
                processCommand(command);
                System.out.println("[OK]");
                loggerService.command(command);
            } catch (final Exception e) {
                loggerService.error(e);
                System.out.println("[FAIL]");
            }
        }
    }

}
