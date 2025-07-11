package ru.forinnyy.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.forinnyy.tm.api.endpoint.*;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.*;
import ru.forinnyy.tm.endpoint.*;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.SessionRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.repository.UserRepository;
import ru.forinnyy.tm.service.*;
import ru.forinnyy.tm.util.SystemUtil;

import javax.xml.ws.Endpoint;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NonNull
    private static final Logger LOGGER_LIFECYCLE = LoggerFactory.getLogger("LIFECYCLE");

    @NonNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @Getter
    @NonNull
    private final IProjectService projectService = new ProjectService(projectRepository);

    @NonNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @Getter
    @NonNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    @Getter
    @NonNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @NonNull
    private final IUserRepository userRepository = new UserRepository();

    @Getter
    @NonNull
    private final IPropertyService propertyService = new PropertyService();

    @NonNull
    private final ISessionRepository sessionRepository = new SessionRepository();

    @NonNull
    private final ISessionService sessionService = new SessionService(sessionRepository);

    @Getter
    @NonNull
    private final IUserService userService = new UserService(propertyService, userRepository, projectRepository, taskRepository);

    @NonNull
    private final ISystemEndpoint systemEndpoint = new SystemEndpoint(this);

    @Getter
    @NonNull
    private final IAuthService authService = new AuthService(propertyService, userService, sessionService);

    @NonNull
    private final Backup backup = new Backup(this);

    @Getter
    @NonNull
    private final IDomainService domainService = new DomainService(this);

    @NonNull
    private final IDomainEndpoint domainEndpoint = new DomainEndpoint(this);

    @NonNull
    private final ITaskEndpoint taskEndpoint = new TaskEndpoint(this);

    @NonNull
    private final IProjectEndpoint projectEndpoint = new ProjectEndpoint(this);

    @NonNull
    private final IUserEndpoint userEndpoint = new UserEndpoint(this);

    @NonNull
    private final IAuthEndpoint authEndpoint = new AuthEndpoint(this);


    private void registry(@NonNull final Object endpoint) {
        @NonNull final String host = getPropertyService().getHost();
        @NonNull final String port = getPropertyService().getPort();
        @NonNull final String name = endpoint.getClass().getSimpleName();
        @NonNull final String url = "http://" + host + ":" + port + "/" + name + "?wsdl";
        Endpoint.publish(url, endpoint);
        System.out.println(url);
    }

    private void initBackup() {
        backup.start();
    }

    @SneakyThrows
    private void initPID() {
        @NonNull final String filename = "task-manager.pid";
        @NonNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(filename), pid.getBytes());
        @NonNull final File file = new File(filename);
        file.deleteOnExit();
    }

    private void initDemoData() throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        @NonNull final User test = userService.create("test", "test", "test@test.ru");
        @NonNull final User user = userService.create("user", "user", "user@user.ru");
        @NonNull final User admin = userService.create("admin", "admin", Role.ADMIN);

        projectService.add(user.getId(), new Project("USER PROJECT", Status.IN_PROGRESS));
        projectService.add(admin.getId(), new Project("ADMIN PROJECT", Status.NOT_STARTED));

        projectService.add(test.getId(), new Project("TEST PROJECT", Status.IN_PROGRESS));
        projectService.add(test.getId(), new Project("DEMO PROJECT", Status.NOT_STARTED));
        projectService.add(test.getId(), new Project("ALPHA PROJECT", Status.IN_PROGRESS));
        projectService.add(test.getId(), new Project("BETA PROJECT", Status.COMPLETED));

        taskService.add(test.getId(), new Task("MEGA TASK"));
        taskService.add(test.getId(), new Task("BETA TASK"));
    }

    @SneakyThrows
    public void start() {
        registry(authEndpoint);
        registry(systemEndpoint);
        registry(userEndpoint);
        registry(domainEndpoint);
        registry(projectEndpoint);
        registry(taskEndpoint);

        initPID();
        initDemoData();
        LOGGER_LIFECYCLE.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread(this::prepareShutdown));
        initBackup();
    }

    @SneakyThrows
    private void prepareShutdown() {
        LOGGER_LIFECYCLE.info("** TASK-MANAGER IS SHUTTING DOWN **");
        backup.stop();
    }

}
