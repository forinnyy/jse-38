package ru.forinnyy.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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


@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private final static Logger LOGGER_LIFECYCLE = LoggerFactory.getLogger("LIFECYCLE");

    @NotNull
    private final static Logger LOGGER_COMMANDS = LoggerFactory.getLogger("COMMANDS");

    @NotNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @Getter
    @NotNull
    private final ICommandService commandService = new CommandService(commandRepository);

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository);

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @Getter
    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @Getter
    @NotNull
    private final IUserService userService = new UserService(userRepository, projectRepository, taskRepository);

    @Getter
    @NotNull
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
        registry(new ProjectCompleteByIdCommand());
        registry(new ProjectCompleteByIndexCommand());

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
        registry(new UserLockCommand());
        registry(new UserUnlockCommand());
        registry(new UserRemoveCommand());
    }

    private void processArgument(@Nullable final String arg) throws
            AbstractSystemException,
            AbstractEntityException,
            AbstractFieldException,
            AbstractUserException {
        @Nullable final AbstractCommand abstractCommand = commandService.getCommandByArgument(arg);
        if (abstractCommand == null) throw new ArgumentNotSupportedException(arg);
        abstractCommand.execute();
    }

    private boolean processArguments(@Nullable final String[] args) throws
            AbstractSystemException,
            AbstractEntityException,
            AbstractFieldException,
            AbstractUserException {
        if (args == null || args.length < 1) return false;
        processArgument(args[0]);
        return true;
    }

    private void processCommand(@Nullable final String command) throws
            AbstractSystemException,
            AbstractFieldException,
            AbstractEntityException,
            AbstractUserException {
        @Nullable final AbstractCommand abstractCommand = commandService.getCommandByName(command);
        if (abstractCommand == null) throw new CommandNotSupportedException(command);
        authService.checkRoles(abstractCommand.getRoles());
        abstractCommand.execute();
    }

    private void registry(@NotNull final AbstractCommand command) {
        command.setServiceLocator(this);
        commandService.add(command);
    }

    private void initDemoData() throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        @NotNull final User test = userService.create("test", "test", "test@test.ru");
        @NotNull final User user = userService.create("user", "user", "user@user.ru");
        @NotNull final User admin = userService.create("admin", "admin", Role.ADMIN);

        projectService.add(user.getId(), new Project("USER PROJECT", Status.IN_PROGRESS));
        projectService.add(admin.getId(), new Project("ADMIN PROJECT", Status.NOT_STARTED));

        projectService.add(test.getId(), new Project("TEST PROJECT", Status.IN_PROGRESS));
        projectService.add(test.getId(), new Project("DEMO PROJECT", Status.NOT_STARTED));
        projectService.add(test.getId(), new Project("ALPHA PROJECT", Status.IN_PROGRESS));
        projectService.add(test.getId(), new Project("BETA PROJECT", Status.COMPLETED));

        taskService.add(test.getId(), new Task("MEGA TASK"));
        taskService.add(test.getId(), new Task("BETA TASK"));
    }

    private void initLogger() {
        LOGGER_LIFECYCLE.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> LOGGER_LIFECYCLE.info("** TASK-MANAGER IS SHUTTING DOWN **"))
        );
    }

    public void run(final String[] args) throws AbstractException {
        if (processArguments(args)) System.exit(0);

        initDemoData();
        initLogger();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("ENTER COMMAND:");
                @NotNull final String command = TerminalUtil.nextLine();
                LOGGER_COMMANDS.info(command);
                processCommand(command);
                System.out.println("[OK]");
            } catch (final Exception e) {
                LOGGER_LIFECYCLE.error(e.getMessage());
                System.err.println("[FAIL]");
            }
        }
    }

}
