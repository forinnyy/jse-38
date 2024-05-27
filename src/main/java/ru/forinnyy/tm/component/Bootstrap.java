package ru.forinnyy.tm.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.forinnyy.tm.api.repository.ICommandRepository;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.*;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.command.project.*;
import ru.forinnyy.tm.command.system.*;
import ru.forinnyy.tm.command.task.*;
import ru.forinnyy.tm.command.user.*;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.AbstractException;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.system.AbstractSystemException;
import ru.forinnyy.tm.exception.system.ArgumentNotSupportedException;
import ru.forinnyy.tm.exception.system.CommandNotSupportedException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.repository.CommandRepository;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.repository.UserRepository;
import ru.forinnyy.tm.service.*;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;


public final class Bootstrap implements IServiceLocator {

    private final static Logger LOGGER_LIFECYCLE = LoggerFactory.getLogger("LIFECYCLE");

    private final static Logger LOGGER_COMMANDS = LoggerFactory.getLogger("COMMANDS");

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    private final ITaskService taskService = new TaskService(taskRepository);

    private final IUserRepository userRepository = new UserRepository();

    private final IUserService userService = new UserService(userRepository);

    private final IAuthService authService = new AuthService(userService);

    {
        registry(new ApplicationAboutCommand());
        registry(new ApplicationExitCommand());
        registry(new ApplicationHelpCommand());
        registry(new ApplicationVersionCommand());
        registry(new ArgumentListCommand());
        registry(new CommandListCommand());
        registry(new SystemInfoCommand());

        registry(new ProjectChangeStatusByIdCommand());
        registry(new ProjectChangeStatusByIndexCommand());
        registry(new ProjectClearCommand());
        registry(new ProjectChangeStatusByIdCommand());
        registry(new ProjectChangeStatusByIndexCommand());
        registry(new ProjectCreateCommand());
        registry(new ProjectListCommand());
        registry(new ProjectRemoveByIdCommand());
        registry(new ProjectRemoveByIndexCommand());
        registry(new ProjectShowByIdCommand());
        registry(new ProjectShowByIndexCommand());
        registry(new ProjectStartByIdCommand());
        registry(new ProjectStartByIndexCommand());
        registry(new ProjectUpdateByIdCommand());
        registry(new ProjectUpdateByIndexCommand());

        registry(new TaskBindToProjectCommand());
        registry(new TaskUnbindFromProjectCommand());
        registry(new TaskChangeStatusByIdCommand());
        registry(new TaskChangeStatusByIndexCommand());
        registry(new TaskClearCommand());
        registry(new TaskCompleteByIdCommand());
        registry(new TaskCompleteByIndexCommand());
        registry(new TaskCreateCommand());
        registry(new TaskListCommand());
        registry(new TaskListByProjectIdCommand());
        registry(new TaskRemoveByIdCommand());
        registry(new TaskRemoveByIndexCommand());
        registry(new TaskShowByIdCommand());
        registry(new TaskShowByIndexCommand());
        registry(new TaskStartByIdCommand());
        registry(new TaskStartByIndexCommand());
        registry(new TaskUpdateByIdCommand());
        registry(new TaskUpdateByIndexCommand());

        registry(new UserChangePasswordCommand());
        registry(new UserLoginCommand());
        registry(new UserLogoutCommand());
        registry(new UserRegistryCommand());
        registry(new UserUpdateProfileCommand());
        registry(new UserViewProfileCommand());
    }

    @Override
    public ICommandService getCommandService() {
        return commandService;
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @Override
    public IProjectTaskService getProjectTaskService() {
        return projectTaskService;
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public IAuthService getAuthService() {
        return authService;
    }

    private void processArgument(final String arg) throws
            AbstractSystemException,
            AbstractEntityException,
            AbstractFieldException,
            AbstractUserException {
        final AbstractCommand abstractCommand = commandService.getCommandByArgument(arg);
        if (abstractCommand == null) throw new ArgumentNotSupportedException(arg);
        abstractCommand.execute();
    }

    private boolean processArguments(final String[] args) throws
            AbstractSystemException,
            AbstractEntityException,
            AbstractFieldException,
            AbstractUserException {
        if (args == null || args.length < 1) return false;
        processArgument(args[0]);
        return true;
    }

    private void exit() {
        System.exit(0);
    }

    private void processCommand(final String command) throws
            AbstractSystemException,
            AbstractFieldException,
            AbstractEntityException,
            AbstractUserException {
        final AbstractCommand abstractCommand = commandService.getCommandByName(command);
        if (abstractCommand == null) throw new CommandNotSupportedException(command);
        authService.checkRoles(abstractCommand.getRoles());
        abstractCommand.execute();
    }

    private void registry(final AbstractCommand command) {
        command.setServiceLocator(this);
        commandService.add(command);
    }

    private void initDemoData() throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        final User test = userService.create("test", "test", "test@test.ru");
        final User user = userService.create("user", "user", "user@user.ru");
        final User admin = userService.create("admin", "admin", Role.ADMIN);

        projectService.add(test.getId(), new Project("TEST PROJECT", Status.IN_PROGRESS));
        projectService.add(test.getId(), new Project("DEMO PROJECT", Status.NOT_STARTED));
        projectService.add(test.getId(), new Project("ALPHA PROJECT", Status.IN_PROGRESS));
        projectService.add(test.getId(), new Project("BETA PROJECT", Status.COMPLETED));

        taskService.add(test.getId(), new Task("MEGA TASK"));
        taskService.add(test.getId(), new Task("BETA TASK"));
    }

    private void initLogger() {
        LOGGER_LIFECYCLE.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                LOGGER_LIFECYCLE.info("** TASK-MANAGER IS SHUTTING DOWN **");
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
                LOGGER_COMMANDS.info(command);
                processCommand(command);
                System.out.println("[OK]");
            } catch (final Exception e) {
                // LOGGER_LIFECYCLE.error(e.getMessage());
                System.err.println("[FAIL]");
            }
        }
    }

}
