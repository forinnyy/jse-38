package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IProjectEndpoint;
import ru.forinnyy.tm.api.endpoint.ITaskEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.service.PropertyService;

@Category(SoapCategory.class)
public final class TaskEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NonNull
    private static final ITaskEndpoint taskEndpoint = ITaskEndpoint.newInstance(propertyService);

    @NonNull
    private static final IProjectEndpoint projectEndpoint = IProjectEndpoint.newInstance(propertyService);

    private static String adminToken;
    private static String userToken;

    @BeforeClass
    public static void init() {
        @NonNull final UserLoginRequest adminRequest = new UserLoginRequest("admin", "admin");
        adminToken = authEndpoint.login(adminRequest).getToken();
        @NonNull final UserLoginRequest userRequest = new UserLoginRequest("user", "user");
        userToken = authEndpoint.login(userRequest).getToken();

        @NonNull final TaskClearRequest adminClearTaskRequest = new TaskClearRequest(adminToken);
        adminClearTaskRequest.setToken(adminToken);
        taskEndpoint.clearTask(adminClearTaskRequest);
        @NonNull final TaskClearRequest userClearTaskRequest = new TaskClearRequest(userToken);
        userClearTaskRequest.setToken(userToken);
        taskEndpoint.clearTask(userClearTaskRequest);
    }

    @Before
    public void beforeMethod() {
        @NonNull TaskClearRequest userTaskRequest = new TaskClearRequest(userToken);
        userTaskRequest.setToken(userToken);
        taskEndpoint.clearTask(userTaskRequest);

        @NonNull TaskClearRequest adminTaskRequest = new TaskClearRequest(adminToken);
        adminTaskRequest.setToken(adminToken);
        taskEndpoint.clearTask(adminTaskRequest);

        @NonNull ProjectClearRequest userProjectRequest = new ProjectClearRequest(userToken);
        userProjectRequest.setToken(userToken);
        projectEndpoint.clearProject(userProjectRequest);

        @NonNull ProjectClearRequest adminProjectRequest = new ProjectClearRequest(adminToken);
        adminProjectRequest.setToken(adminToken);
        projectEndpoint.clearProject(adminProjectRequest);
    }

    @NonNull
    private Task createTask(@NonNull final String name, @NonNull final String description, final String token) {
        @NonNull final TaskCreateRequest request = new TaskCreateRequest(token);
        request.setName(name);
        request.setDescription(description);
        taskEndpoint.createTask(request);

        @NonNull TaskGetByIndexRequest requestTask = new TaskGetByIndexRequest(token);
        requestTask.setIndex(0);
        @NonNull final TaskGetByIndexResponse response = taskEndpoint.getTaskByIndex(requestTask);
        @NonNull final Task task = response.getTask();
        return task;
    }

    @NonNull
    private Project createProject(@NonNull final String name, @NonNull final String description, final String token) {
        @NonNull final ProjectCreateRequest request = new ProjectCreateRequest(token);
        request.setName(name);
        request.setDescription(description);
        projectEndpoint.createProject(request);

        @NonNull ProjectGetByIndexRequest requestProject = new ProjectGetByIndexRequest(token);
        requestProject.setIndex(0);
        @NonNull final ProjectGetByIndexResponse response = projectEndpoint.getProjectByIndex(requestProject);
        @NonNull final Project project = response.getProject();
        return project;
    }

    @Test
    public void testCreateTask() {
        Assert.assertThrows(Exception.class, () -> createTask("TEXT", "TEXT", null));
        @NonNull final Task task = createTask("UNIQUE", "UNIQUE", userToken);

        Assert.assertEquals("UNIQUE", task.getName());
        Assert.assertEquals("UNIQUE", task.getDescription());
    }

    @Test
    public void testClearTask() {
        @NonNull TaskClearRequest requestWithNullToken = new TaskClearRequest(null);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.clearTask(requestWithNullToken));

        createTask("UNIQUE", "UNIQUE", userToken);
        @NonNull TaskClearRequest request = new TaskClearRequest(userToken);
        request.setToken(userToken);
        taskEndpoint.clearTask(request);

        @NonNull final TaskListRequest taskListRequest = new TaskListRequest(userToken);
        taskListRequest.setSort(null);
        @NonNull final TaskListResponse response = taskEndpoint.listTask(taskListRequest);
        Assert.assertNull(response.getTasks());
    }

    @Test
    public void testGetTaskById() {
        @NonNull TaskGetByIdRequest requestWithNullToken = new TaskGetByIdRequest(null);
        requestWithNullToken.setId("test");
        Assert.assertThrows(Exception.class, () -> taskEndpoint.getTaskById(requestWithNullToken));

        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);

        @NonNull TaskGetByIdRequest request = new TaskGetByIdRequest(userToken);
        request.setId(createdTask.getId());
        @NonNull final TaskGetByIdResponse response = taskEndpoint.getTaskById(request);

        Assert.assertNotNull(response.getTask());
        Assert.assertEquals(createdTask.getId(), response.getTask().getId());
        Assert.assertEquals("Test Task", response.getTask().getName());
    }

    @Test
    public void testGetTaskByIndex() {
        @NonNull TaskGetByIndexRequest requestWithNullToken = new TaskGetByIndexRequest(null);
        requestWithNullToken.setIndex(0);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.getTaskByIndex(requestWithNullToken));

        @NonNull TaskGetByIndexRequest requestWithInvalidIndex = new TaskGetByIndexRequest(userToken);
        requestWithInvalidIndex.setIndex(-1);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.getTaskByIndex(requestWithInvalidIndex));

        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);

        @NonNull TaskGetByIndexRequest request = new TaskGetByIndexRequest(userToken);
        request.setIndex(0);
        @NonNull final TaskGetByIndexResponse response = taskEndpoint.getTaskByIndex(request);

        Assert.assertNotNull(response.getTask());
        Assert.assertEquals(createdTask.getId(), response.getTask().getId());
    }

    @Test
    public void testListTask() {
        @NonNull TaskListRequest requestWithNullToken = new TaskListRequest(null);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.listTask(requestWithNullToken));

        @NonNull TaskListRequest request = new TaskListRequest(userToken);
        request.setSort(null);
        @NonNull final TaskListResponse emptyResponse = taskEndpoint.listTask(request);
        Assert.assertNull(emptyResponse.getTasks());

        createTask("Task 1", "Description 1", userToken);
        createTask("Task 2", "Description 2", userToken);

        @NonNull final TaskListResponse response = taskEndpoint.listTask(request);
        Assert.assertNotNull(response.getTasks());
        Assert.assertEquals(2, response.getTasks().size());
    }

    @Test
    public void testListTaskByProjectId() {
        @NonNull TaskListByProjectIdRequest requestWithNullToken = new TaskListByProjectIdRequest(null);
        requestWithNullToken.setProjectId("test-project");
        Assert.assertThrows(Exception.class, () -> taskEndpoint.listTaskByProjectId(requestWithNullToken));

        @NonNull final Task task = createTask("Test Task", "Test Description", userToken);
        @NonNull final Project project = createProject("Project", "Project", userToken);

        @NonNull final TaskBindToProjectRequest requestBindTaskToProject = new TaskBindToProjectRequest(userToken);
        requestBindTaskToProject.setTaskId(task.getId());
        requestBindTaskToProject.setProjectId(project.getId());
        requestBindTaskToProject.setToken(userToken);
        taskEndpoint.bindTaskToProject(requestBindTaskToProject);

        @NonNull TaskListByProjectIdRequest request = new TaskListByProjectIdRequest(userToken);
        request.setProjectId(project.getId());
        @NonNull final TaskListByProjectIdResponse response = taskEndpoint.listTaskByProjectId(request);

        Assert.assertEquals(task.getId(), response.getTasks().get(0).getId());
    }

    @Test
    public void testRemoveTaskById() {
        @NonNull TaskRemoveByIdRequest requestWithNullToken = new TaskRemoveByIdRequest(null);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.removeTaskById(requestWithNullToken));

        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);

        @NonNull TaskRemoveByIdRequest request = new TaskRemoveByIdRequest(userToken);
        request.setId(createdTask.getId());
        taskEndpoint.removeTaskById(request);

        @NonNull TaskGetByIdRequest getRequest = new TaskGetByIdRequest(userToken);
        getRequest.setId(createdTask.getId());
        Assert.assertEquals(null, taskEndpoint.getTaskById(getRequest).getTask());
    }

    @Test
    public void testRemoveTaskByIndex() {
        @NonNull TaskRemoveByIndexRequest requestWithNullToken = new TaskRemoveByIndexRequest(null);
        requestWithNullToken.setIndex(0);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.removeTaskByIndex(requestWithNullToken));

        @NonNull TaskRemoveByIndexRequest requestWithInvalidIndex = new TaskRemoveByIndexRequest(userToken);
        requestWithInvalidIndex.setIndex(-1);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.removeTaskByIndex(requestWithInvalidIndex));

        createTask("Test Task", "Test Description", userToken);

        @NonNull TaskRemoveByIndexRequest request = new TaskRemoveByIndexRequest(userToken);
        request.setIndex(0);
        taskEndpoint.removeTaskByIndex(request);

        @NonNull TaskListRequest listRequest = new TaskListRequest(userToken);
        listRequest.setSort(null);
        @NonNull final TaskListResponse response = taskEndpoint.listTask(listRequest);
        Assert.assertNull(response.getTasks());
    }

    @Test
    public void testUpdateTaskById() {
        @NonNull TaskUpdateByIdRequest requestWithNullToken = new TaskUpdateByIdRequest(null);
        requestWithNullToken.setId("test");
        Assert.assertThrows(Exception.class, () -> taskEndpoint.updateTaskById(requestWithNullToken));

        @NonNull TaskUpdateByIdRequest requestWithInvalidId = new TaskUpdateByIdRequest(userToken);
        requestWithInvalidId.setId("invalid-id");
        requestWithInvalidId.setName("New Name");
        requestWithInvalidId.setDescription("New Description");
        Assert.assertThrows(Exception.class, () -> taskEndpoint.updateTaskById(requestWithInvalidId));

        @NonNull final Task createdTask = createTask("Old Name", "Old Description", userToken);

        @NonNull TaskUpdateByIdRequest request = new TaskUpdateByIdRequest(userToken);
        request.setId(createdTask.getId());
        request.setName("Updated Name");
        request.setDescription("Updated Description");
        taskEndpoint.updateTaskById(request);

        @NonNull TaskGetByIdRequest getRequest = new TaskGetByIdRequest(userToken);
        getRequest.setId(createdTask.getId());
        @NonNull final TaskGetByIdResponse response = taskEndpoint.getTaskById(getRequest);

        Assert.assertEquals("Updated Name", response.getTask().getName());
        Assert.assertEquals("Updated Description", response.getTask().getDescription());
    }

    @Test
    public void testUpdateTaskByIndex() {
        @NonNull TaskUpdateByIndexRequest requestWithNullToken = new TaskUpdateByIndexRequest(null);
        requestWithNullToken.setIndex(0);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.updateTaskByIndex(requestWithNullToken));

        @NonNull TaskUpdateByIndexRequest requestWithInvalidIndex = new TaskUpdateByIndexRequest(userToken);
        requestWithInvalidIndex.setIndex(-1);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.updateTaskByIndex(requestWithInvalidIndex));

        createTask("Old Name", "Old Description", userToken);

        @NonNull TaskUpdateByIndexRequest request = new TaskUpdateByIndexRequest(userToken);
        request.setIndex(0);
        request.setName("Updated Name");
        request.setDescription("Updated Description");
        taskEndpoint.updateTaskByIndex(request);

        @NonNull TaskGetByIndexRequest getRequest = new TaskGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final TaskGetByIndexResponse response = taskEndpoint.getTaskByIndex(getRequest);

        Assert.assertEquals("Updated Name", response.getTask().getName());
        Assert.assertEquals("Updated Description", response.getTask().getDescription());
    }

    @Test
    public void testChangeTaskStatusById() {
        @NonNull TaskChangeStatusByIdRequest requestWithNullToken = new TaskChangeStatusByIdRequest(null);
        requestWithNullToken.setId("test");
        requestWithNullToken.setStatus(Status.IN_PROGRESS);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.changeTaskStatusById(requestWithNullToken));

        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);

        @NonNull TaskChangeStatusByIdRequest request = new TaskChangeStatusByIdRequest(userToken);
        request.setId(createdTask.getId());
        request.setStatus(Status.IN_PROGRESS);
        taskEndpoint.changeTaskStatusById(request);

        @NonNull TaskGetByIdRequest getRequest = new TaskGetByIdRequest(userToken);
        getRequest.setId(createdTask.getId());
        @NonNull final TaskGetByIdResponse response = taskEndpoint.getTaskById(getRequest);

        Assert.assertEquals(Status.IN_PROGRESS, response.getTask().getStatus());
    }

    @Test
    public void testChangeTaskStatusByIndex() {
        @NonNull TaskChangeStatusByIndexRequest requestWithNullToken = new TaskChangeStatusByIndexRequest(null);
        requestWithNullToken.setIndex(0);
        requestWithNullToken.setStatus(Status.COMPLETED);
        Assert.assertThrows(Exception.class, () -> taskEndpoint.changeTaskStatusByIndex(requestWithNullToken));

        createTask("Test Task", "Test Description", userToken);

        @NonNull TaskChangeStatusByIndexRequest request = new TaskChangeStatusByIndexRequest(userToken);
        request.setIndex(0);
        request.setStatus(Status.COMPLETED);
        taskEndpoint.changeTaskStatusByIndex(request);

        @NonNull TaskGetByIndexRequest getRequest = new TaskGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final TaskGetByIndexResponse response = taskEndpoint.getTaskByIndex(getRequest);

        Assert.assertEquals(Status.COMPLETED, response.getTask().getStatus());
    }

    @Test
    public void testCompleteTaskById() {
        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);

        @NonNull TaskCompleteByIdRequest request = new TaskCompleteByIdRequest(userToken);
        request.setId(createdTask.getId());
        taskEndpoint.completeTaskById(request);

        @NonNull TaskGetByIdRequest getRequest = new TaskGetByIdRequest(userToken);
        getRequest.setId(createdTask.getId());
        @NonNull final TaskGetByIdResponse response = taskEndpoint.getTaskById(getRequest);

        Assert.assertEquals(Status.COMPLETED, response.getTask().getStatus());
    }

    @Test
    public void testCompleteTaskByIndex() {
        createTask("Test Task", "Test Description", userToken);

        @NonNull TaskCompleteByIndexRequest request = new TaskCompleteByIndexRequest(userToken);
        request.setIndex(0);
        taskEndpoint.completeTaskByIndex(request);

        @NonNull TaskGetByIndexRequest getRequest = new TaskGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final TaskGetByIndexResponse response = taskEndpoint.getTaskByIndex(getRequest);

        Assert.assertEquals(Status.COMPLETED, response.getTask().getStatus());
    }

    @Test
    public void testStartTaskById() {
        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);

        @NonNull TaskStartByIdRequest request = new TaskStartByIdRequest(userToken);
        request.setId(createdTask.getId());
        taskEndpoint.startTaskById(request);

        @NonNull TaskGetByIdRequest getRequest = new TaskGetByIdRequest(userToken);
        getRequest.setId(createdTask.getId());
        @NonNull final TaskGetByIdResponse response = taskEndpoint.getTaskById(getRequest);

        Assert.assertEquals(Status.IN_PROGRESS, response.getTask().getStatus());
    }

    @Test
    public void testStartTaskByIndex() {
        createTask("Test Task", "Test Description", userToken);

        @NonNull TaskStartByIndexRequest request = new TaskStartByIndexRequest(userToken);
        request.setIndex(0);
        taskEndpoint.startTaskByIndex(request);

        @NonNull TaskGetByIndexRequest getRequest = new TaskGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final TaskGetByIndexResponse response = taskEndpoint.getTaskByIndex(getRequest);

        Assert.assertEquals(Status.IN_PROGRESS, response.getTask().getStatus());
    }

    @Test
    public void testBindTaskToProject() {
        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);
        @NonNull final Project createdProject = createProject("Test Project", "Test Project Description", userToken);

        @NonNull TaskBindToProjectRequest request = new TaskBindToProjectRequest(userToken);
        request.setTaskId(createdTask.getId());
        request.setProjectId(createdProject.getId());
        request.setToken(userToken);
        taskEndpoint.bindTaskToProject(request);

        @NonNull TaskListByProjectIdRequest listRequest = new TaskListByProjectIdRequest(userToken);
        listRequest.setProjectId(createdProject.getId());
        @NonNull final TaskListByProjectIdResponse response = taskEndpoint.listTaskByProjectId(listRequest);

        Assert.assertNotNull(response.getTasks());
        Assert.assertEquals(1, response.getTasks().size());
        Assert.assertEquals(createdTask.getId(), response.getTasks().get(0).getId());
    }

    @Test
    public void testUnbindTaskFromProject() {
        @NonNull final Task createdTask = createTask("Test Task", "Test Description", userToken);
        @NonNull final Project createdProject = createProject("Test Project", "Test Project Description", userToken);

        @NonNull TaskBindToProjectRequest bindRequest = new TaskBindToProjectRequest(userToken);
        bindRequest.setTaskId(createdTask.getId());
        bindRequest.setProjectId(createdProject.getId());
        bindRequest.setToken(userToken);
        taskEndpoint.bindTaskToProject(bindRequest);

        @NonNull TaskListByProjectIdRequest checkRequest = new TaskListByProjectIdRequest(userToken);
        checkRequest.setProjectId(createdProject.getId());
        @NonNull final TaskListByProjectIdResponse checkResponse = taskEndpoint.listTaskByProjectId(checkRequest);
        Assert.assertEquals(1, checkResponse.getTasks().size());

        @NonNull TaskUnbindFromProjectRequest unbindRequest = new TaskUnbindFromProjectRequest(userToken);
        unbindRequest.setTaskId(createdTask.getId());
        unbindRequest.setProjectId(createdProject.getId());
        unbindRequest.setToken(userToken);
        taskEndpoint.unbindTaskFromProject(unbindRequest);

        @NonNull TaskListByProjectIdRequest listRequest = new TaskListByProjectIdRequest(userToken);
        listRequest.setProjectId(createdProject.getId());
        @NonNull final TaskListByProjectIdResponse response = taskEndpoint.listTaskByProjectId(listRequest);

        Assert.assertEquals(null, response.getTasks());
    }

    @Test
    public void testUserIsolation() {
        @NonNull final Task adminTask = createTask("Admin Task", "Admin Desc", adminToken);

        @NonNull TaskGetByIdRequest userRequest = new TaskGetByIdRequest(userToken);
        userRequest.setId(adminTask.getId());
        Assert.assertNotEquals(adminTask, taskEndpoint.getTaskById(userRequest));

        @NonNull final Task userTask = createTask("User Task", "User Desc", userToken);

        @NonNull TaskGetByIdRequest adminRequest = new TaskGetByIdRequest(adminToken);
        adminRequest.setId(userTask.getId());
        Assert.assertNotEquals(userTask, taskEndpoint.getTaskById(adminRequest));
    }

    @AfterClass
    public static void clear() {
        @NonNull TaskClearRequest userRequest = new TaskClearRequest(userToken);
        userRequest.setToken(userToken);
        taskEndpoint.clearTask(userRequest);

        @NonNull TaskClearRequest adminRequest = new TaskClearRequest(adminToken);
        adminRequest.setToken(adminToken);
        taskEndpoint.clearTask(adminRequest);
    }
    
}