package ru.forinnyy.tm;

import lombok.SneakyThrows;
import org.junit.Before;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Session;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.SessionRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.repository.UserRepository;

public abstract class AbstractTest {

    protected static String UUID1 = "00000000-0000-0000-0000-00000000001";
    protected static String UUID2 = "00000000-0000-0000-0000-00000000002";
    protected static String STRING = "STRING";
    protected static String EMPTY_STRING = "";
    protected User testUser;
    protected User adminUser;
    protected Session testUserSession;
    protected Session adminSession;

    protected Project project_one;
    protected Project project_two;
    protected Task task_one;
    protected Task task_two;
    protected Task task_three;
    protected Task task_four;

    protected IProjectRepository projectRepository;
    protected ITaskRepository taskRepository;
    protected IUserRepository userRepository;
    protected ISessionRepository sessionRepository;

    @Before
    @SneakyThrows
    public void initData() {
        testUser = new User();
        testUser.setId("00000000-0000-0000-0000-0000000-TEST");
        testUser.setLogin("test");
        testUser.setRole(Role.USUAL);
        testUser.setEmail("test@test.ru");

        adminUser = new User();
        adminUser.setId("00000000-0000-0000-0000-000000-ADMIN");
        adminUser.setLogin("admin");
        adminUser.setRole(Role.ADMIN);


        testUserSession = new Session();
        testUserSession.setId("00000000-0000-0000-000000-TEST-SESSION");
        testUserSession.setUserId(testUser.getId());
        testUserSession.setRole(testUser.getRole());

        adminSession = new Session();
        adminSession.setId("00000000-0000-0000-00000-ADMIN-SESSION");
        adminSession.setUserId(adminUser.getId());
        adminSession.setRole(adminUser.getRole());

        projectRepository = new ProjectRepository();
        taskRepository = new TaskRepository();
        userRepository = new UserRepository();
        sessionRepository = new SessionRepository();

        project_one = new Project();
        project_one.setName("PROJECT ONE");
        project_one.setDescription("Description");
        project_one.setId("00000000-0000-0000-0000-000-PROJECT-1");
        project_one.setUserId(adminUser.getId());

        project_two = new Project();
        project_two.setName("PROJECT TWO");
        project_two.setDescription("Description");
        project_two.setId("00000000-0000-0000-0000-000-PROJECT-2");
        project_two.setUserId(testUser.getId());

        task_one = new Task();
        task_one.setName("TASK ONE");
        task_one.setDescription("DESCRIPTION");
        task_one.setId("00000000-0000-0000-0000-000000-TASK-1");
        task_one.setUserId(adminUser.getId());
        task_one.setProjectId(project_one.getId());

        task_two = new Task();
        task_two.setName("TASK TWO");
        task_two.setDescription("DESCRIPTION");
        task_two.setId("00000000-0000-0000-0000-000000-TASK-2");
        task_two.setUserId(testUser.getId());
        task_two.setProjectId(project_two.getId());

        task_three = new Task();
        task_three.setName("TASK THREE");
        task_three.setDescription("DESCRIPTION");
        task_three.setId("00000000-0000-0000-0000-000000-TASK-3");
        task_three.setUserId(testUser.getId());
        task_three.setProjectId(project_two.getId());

        task_four = new Task();
        task_four.setName("TASK FOUR");
        task_four.setDescription("DESCRIPTION");
        task_four.setId("00000000-0000-0000-0000-000000-TASK-4");
        task_four.setUserId(testUser.getId());
        task_four.setProjectId(project_one.getId());

        projectRepository.add(project_one);
        projectRepository.add(project_two);

        taskRepository.add(task_one);
        taskRepository.add(task_two);
        taskRepository.add(task_three);
        taskRepository.add(task_four);

        userRepository.add(adminUser);
        userRepository.add(testUser);

        sessionRepository.add(adminSession);
        sessionRepository.add(testUserSession);
    }

}
