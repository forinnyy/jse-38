package ru.forinnyy.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.forinnyy.tm.api.endpoint.*;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.*;
import ru.forinnyy.tm.dto.request.*;
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
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.repository.UserRepository;
import ru.forinnyy.tm.service.*;
import ru.forinnyy.tm.util.SystemUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


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

    @Getter
    @NonNull
    private final IUserService userService = new UserService(propertyService, userRepository, projectRepository, taskRepository);

    @NonNull
    private final ISystemEndpoint systemEndpoint = new SystemEndpoint(this);

    @Getter
    @NonNull
    private final IAuthService authService = new AuthService(propertyService, userService);

    @NonNull
    private final Backup backup = new Backup(this);

    @NonNull
    private final Server server = new Server(this);

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

    {
        server.registry(ApplicationAboutRequest.class, systemEndpoint::getAbout);
        server.registry(ApplicationVersionRequest.class, systemEndpoint::getVersion);

        server.registry(DataBackupLoadRequest.class, domainEndpoint::loadDataBackup);
        server.registry(DataBackupSaveRequest.class, domainEndpoint::saveDataBackup);
        server.registry(DataBase64LoadRequest.class, domainEndpoint::loadDataBase64);
        server.registry(DataBase64SaveRequest.class, domainEndpoint::saveDataBase64);
        server.registry(DataBinaryLoadRequest.class, domainEndpoint::loadDataBinary);
        server.registry(DataBinarySaveRequest.class, domainEndpoint::saveDataBinary);
        server.registry(DataJsonLoadFasterXmlRequest.class, domainEndpoint::loadDataJsonFasterXml);
        server.registry(DataJsonLoadJaxBRequest.class, domainEndpoint::loadDataJsonJaxB);
        server.registry(DataJsonSaveFasterXmlRequest.class, domainEndpoint::saveDataJsonFasterXml);
        server.registry(DataJsonSaveJaxBRequest.class, domainEndpoint::saveDataJsonJaxB);
        server.registry(DataXmlLoadFasterXmlRequest.class, domainEndpoint::loadDataXmlFasterXml);
        server.registry(DataXmlLoadJaxBRequest.class, domainEndpoint::loadDataXmlJaxB);
        server.registry(DataXmlSaveFasterXmlRequest.class, domainEndpoint::saveDataXmlFasterXml);
        server.registry(DataXmlSaveJaxBRequest.class, domainEndpoint::saveDataXmlJaxB);
        server.registry(DataYamlLoadFasterXmlRequest.class, domainEndpoint::loadDataYamlFasterXml);
        server.registry(DataYamlSaveFasterXmlRequest.class, domainEndpoint::saveDataYamlFasterXml);

        server.registry(ProjectChangeStatusByIdRequest.class, projectEndpoint::changeProjectStatusById);
        server.registry(ProjectChangeStatusByIndexRequest.class, projectEndpoint::changeProjectStatusByIndex);
        server.registry(ProjectClearRequest.class, projectEndpoint::clearProject);
        server.registry(ProjectCreateRequest.class, projectEndpoint::createProject);
        server.registry(ProjectGetByIdRequest.class, projectEndpoint::getProjectById);
        server.registry(ProjectGetByIndexRequest.class, projectEndpoint::getProjectByIndex);
        server.registry(ProjectListRequest.class, projectEndpoint::listProject);
        server.registry(ProjectRemoveByIdRequest.class, projectEndpoint::removeProjectById);
        server.registry(ProjectRemoveByIndexRequest.class, projectEndpoint::removeProjectByIndex);
        server.registry(ProjectUpdateByIdRequest.class, projectEndpoint::updateProjectById);
        server.registry(ProjectUpdateByIndexRequest.class, projectEndpoint::updateProjectByIndex);
        server.registry(ProjectCompleteByIdRequest.class, projectEndpoint::completeProjectById);
        server.registry(ProjectCompleteByIndexRequest.class, projectEndpoint::completeProjectByIndex);
        server.registry(ProjectStartByIdRequest.class, projectEndpoint::startProjectById);
        server.registry(ProjectStartByIndexRequest.class, projectEndpoint::startProjectByIndex);

        server.registry(TaskBindToProjectRequest.class, taskEndpoint::bindTaskToProject);
        server.registry(TaskChangeStatusByIdRequest.class, taskEndpoint::changeTaskStatusById);
        server.registry(TaskChangeStatusByIndexRequest.class, taskEndpoint::changeTaskStatusByIndex);
        server.registry(TaskClearRequest.class, taskEndpoint::clearTask);
        server.registry(TaskCreateRequest.class, taskEndpoint::createTask);
        server.registry(TaskGetByIdRequest.class, taskEndpoint::getTaskById);
        server.registry(TaskGetByIndexRequest.class, taskEndpoint::getTaskByIndex);
        server.registry(TaskListByProjectIdRequest.class, taskEndpoint::listTaskByProjectId);
        server.registry(TaskListRequest.class, taskEndpoint::listTask);
        server.registry(TaskRemoveByIdRequest.class, taskEndpoint::removeTaskById);
        server.registry(TaskRemoveByIndexRequest.class, taskEndpoint::removeTaskByIndex);
        server.registry(TaskUnbindFromProjectRequest.class, taskEndpoint::unbindTaskFromProject);
        server.registry(TaskUpdateByIdRequest.class, taskEndpoint::updateTaskById);
        server.registry(TaskUpdateByIndexRequest.class, taskEndpoint::updateTaskByIndex);
        server.registry(TaskCompleteByIdRequest.class, taskEndpoint::completeTaskById);
        server.registry(TaskCompleteByIndexRequest.class, taskEndpoint::completeTaskByIndex);
        server.registry(TaskStartByIdRequest.class, taskEndpoint::startTaskById);
        server.registry(TaskStartByIndexRequest.class, taskEndpoint::startTaskByIndex);

        server.registry(UserLockRequest.class, userEndpoint::lockUser);
        server.registry(UserUnlockRequest.class, userEndpoint::unlockUser);
        server.registry(UserRemoveRequest.class, userEndpoint::removeUser);
        server.registry(UserRegistryRequest.class, userEndpoint::registryUser);
        server.registry(UserChangePasswordRequest.class, userEndpoint::changeUserPassword);
        server.registry(UserUpdateProfileRequest.class, userEndpoint::updateUserProfile);
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
        initPID();
        initDemoData();
        LOGGER_LIFECYCLE.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread(this::prepareShutdown));
        initBackup();
        server.start();
    }

    @SneakyThrows
    private void prepareShutdown() {
        LOGGER_LIFECYCLE.info("** TASK-MANAGER IS SHUTTING DOWN **");
        backup.stop();
        server.stop();
    }

}
