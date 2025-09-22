package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IProjectEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.ProjectGetByIdResponse;
import ru.forinnyy.tm.dto.response.ProjectGetByIndexResponse;
import ru.forinnyy.tm.dto.response.ProjectListResponse;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.service.PropertyService;


@Category(SoapCategory.class)
public final class ProjectEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

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

        @NonNull final ProjectClearRequest adminClearProjectRequest = new ProjectClearRequest(adminToken);
        adminClearProjectRequest.setToken(adminToken);
        projectEndpoint.clearProject(adminClearProjectRequest);
        @NonNull final ProjectClearRequest userClearProjectRequest = new ProjectClearRequest(userToken);
        userClearProjectRequest.setToken(userToken);
        projectEndpoint.clearProject(userClearProjectRequest);
    }

    @Before
    public void beforeMethod() {
        @NonNull ProjectClearRequest userRequest = new ProjectClearRequest(userToken);
        userRequest.setToken(userToken);
        projectEndpoint.clearProject(userRequest);

        @NonNull ProjectClearRequest adminRequest = new ProjectClearRequest(adminToken);
        adminRequest.setToken(adminToken);
        projectEndpoint.clearProject(adminRequest);
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
    public void testCreateProject() {
        Assert.assertThrows(Exception.class, () -> createProject("TEXT", "TEXT", null));
        @NonNull final Project project = createProject("UNIQUE", "UNIQUE", userToken);

        Assert.assertEquals(project.getName(), "UNIQUE");
        Assert.assertEquals(project.getDescription(), "UNIQUE");
    }

    @Test
    public void testClearProject() {
        @NonNull ProjectClearRequest requestWithNullToken = new ProjectClearRequest(null);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.clearProject(requestWithNullToken));

        createProject("UNIQUE", "UNIQUE", userToken);
        @NonNull ProjectClearRequest request = new ProjectClearRequest(userToken);
        request.setToken(userToken);
        projectEndpoint.clearProject(request);

        @NonNull final ProjectListRequest projectListRequest = new ProjectListRequest(userToken);
        projectListRequest.setSort(null);
        @NonNull final ProjectListResponse response = projectEndpoint.listProject(projectListRequest);
        Assert.assertNull(response.getProjects());
    }

    @Test
    public void testGetProjectById() {
        @NonNull ProjectGetByIdRequest requestWithNullToken = new ProjectGetByIdRequest(null);
        requestWithNullToken.setId("test");
        Assert.assertThrows(Exception.class, () -> projectEndpoint.getProjectById(requestWithNullToken));

        @NonNull final Project createdProject = createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectGetByIdRequest request = new ProjectGetByIdRequest(userToken);
        request.setId(createdProject.getId());
        @NonNull final ProjectGetByIdResponse response = projectEndpoint.getProjectById(request);

        Assert.assertNotNull(response.getProject());
        Assert.assertEquals(createdProject.getId(), response.getProject().getId());
        Assert.assertEquals("Test Project", response.getProject().getName());
    }

    @Test
    public void testGetProjectByIndex() {
        @NonNull ProjectGetByIndexRequest requestWithNullToken = new ProjectGetByIndexRequest(null);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.getProjectByIndex(requestWithNullToken));

        @NonNull ProjectGetByIndexRequest requestWithInvalidIndex = new ProjectGetByIndexRequest(userToken);
        requestWithInvalidIndex.setIndex(-1);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.getProjectByIndex(requestWithInvalidIndex));

        @NonNull final Project createdProject = createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectGetByIndexRequest request = new ProjectGetByIndexRequest(userToken);
        request.setIndex(0);
        @NonNull final ProjectGetByIndexResponse response = projectEndpoint.getProjectByIndex(request);

        Assert.assertNotNull(response.getProject());
        Assert.assertEquals(createdProject.getId(), response.getProject().getId());
    }

    @Test
    public void testListProject() {
        @NonNull ProjectListRequest requestWithNullToken = new ProjectListRequest(null);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.listProject(requestWithNullToken));

        @NonNull ProjectListRequest request = new ProjectListRequest(userToken);
        request.setSort(null);
        @NonNull final ProjectListResponse emptyResponse = projectEndpoint.listProject(request);
        Assert.assertNull(emptyResponse.getProjects());

        createProject("Project 1", "Description 1", userToken);
        createProject("Project 2", "Description 2", userToken);

        @NonNull final ProjectListResponse response = projectEndpoint.listProject(request);
        Assert.assertNotNull(response.getProjects());
        Assert.assertEquals(2, response.getProjects().size());
    }

    @Test
    public void testRemoveProjectById() {
        @NonNull ProjectRemoveByIdRequest requestWithNullToken = new ProjectRemoveByIdRequest(null);
        requestWithNullToken.setId("test");
        Assert.assertThrows(Exception.class, () -> projectEndpoint.removeProjectById(requestWithNullToken));

        @NonNull ProjectRemoveByIdRequest requestWithInvalidId = new ProjectRemoveByIdRequest(userToken);
        requestWithInvalidId.setId("invalid-id");
        Assert.assertThrows(Exception.class, () -> projectEndpoint.removeProjectById(requestWithInvalidId));

        @NonNull final Project createdProject = createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectRemoveByIdRequest request = new ProjectRemoveByIdRequest(userToken);
        request.setId(createdProject.getId());
        projectEndpoint.removeProjectById(request);

        @NonNull ProjectGetByIdRequest getRequest = new ProjectGetByIdRequest(userToken);
        getRequest.setId(createdProject.getId());
        Assert.assertNotEquals(createdProject, projectEndpoint.getProjectById(getRequest));
    }

    @Test
    public void testRemoveProjectByIndex() {
        @NonNull ProjectRemoveByIndexRequest requestWithNullToken = new ProjectRemoveByIndexRequest(null);
        requestWithNullToken.setIndex(0);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.removeProjectByIndex(requestWithNullToken));

        @NonNull ProjectRemoveByIndexRequest requestWithInvalidIndex = new ProjectRemoveByIndexRequest(userToken);
        requestWithInvalidIndex.setIndex(-1);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.removeProjectByIndex(requestWithInvalidIndex));

        createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectRemoveByIndexRequest request = new ProjectRemoveByIndexRequest(userToken);
        request.setIndex(0);
        projectEndpoint.removeProjectByIndex(request);

        @NonNull ProjectListRequest listRequest = new ProjectListRequest(userToken);
        listRequest.setSort(null);
        @NonNull final ProjectListResponse response = projectEndpoint.listProject(listRequest);
        Assert.assertNull(response.getProjects());
    }

    @Test
    public void testUpdateProjectById() {
        @NonNull ProjectUpdateByIdRequest requestWithNullToken = new ProjectUpdateByIdRequest(null);
        requestWithNullToken.setId("test");
        Assert.assertThrows(Exception.class, () -> projectEndpoint.updateProjectById(requestWithNullToken));

        @NonNull ProjectUpdateByIdRequest requestWithInvalidId = new ProjectUpdateByIdRequest(userToken);
        requestWithInvalidId.setId("invalid-id");
        requestWithInvalidId.setName("New Name");
        requestWithInvalidId.setDescription("New Description");
        Assert.assertThrows(Exception.class, () -> projectEndpoint.updateProjectById(requestWithInvalidId));

        @NonNull final Project createdProject = createProject("Old Name", "Old Description", userToken);

        @NonNull ProjectUpdateByIdRequest request = new ProjectUpdateByIdRequest(userToken);
        request.setId(createdProject.getId());
        request.setName("Updated Name");
        request.setDescription("Updated Description");
        projectEndpoint.updateProjectById(request);

        @NonNull ProjectGetByIdRequest getRequest = new ProjectGetByIdRequest(userToken);
        getRequest.setId(createdProject.getId());
        @NonNull final ProjectGetByIdResponse response = projectEndpoint.getProjectById(getRequest);

        Assert.assertEquals("Updated Name", response.getProject().getName());
        Assert.assertEquals("Updated Description", response.getProject().getDescription());
    }

    @Test
    public void testUpdateProjectByIndex() {
        @NonNull ProjectUpdateByIndexRequest requestWithNullToken = new ProjectUpdateByIndexRequest(null);
        requestWithNullToken.setIndex(0);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.updateProjectByIndex(requestWithNullToken));

        @NonNull ProjectUpdateByIndexRequest requestWithInvalidIndex = new ProjectUpdateByIndexRequest(userToken);
        requestWithInvalidIndex.setIndex(-1);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.updateProjectByIndex(requestWithInvalidIndex));

        createProject("Old Name", "Old Description", userToken);

        @NonNull ProjectUpdateByIndexRequest request = new ProjectUpdateByIndexRequest(userToken);
        request.setIndex(0);
        request.setName("Updated Name");
        request.setDescription("Updated Description");
        projectEndpoint.updateProjectByIndex(request);

        @NonNull ProjectGetByIndexRequest getRequest = new ProjectGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final ProjectGetByIndexResponse response = projectEndpoint.getProjectByIndex(getRequest);

        Assert.assertEquals("Updated Name", response.getProject().getName());
        Assert.assertEquals("Updated Description", response.getProject().getDescription());
    }

    @Test
    public void testChangeProjectStatusById() {
        @NonNull ProjectChangeStatusByIdRequest requestWithNullToken = new ProjectChangeStatusByIdRequest(null);
        requestWithNullToken.setId("test");
        requestWithNullToken.setStatus(Status.IN_PROGRESS);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.changeProjectStatusById(requestWithNullToken));

        @NonNull final Project createdProject = createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectChangeStatusByIdRequest request = new ProjectChangeStatusByIdRequest(userToken);
        request.setId(createdProject.getId());
        request.setStatus(Status.IN_PROGRESS);
        projectEndpoint.changeProjectStatusById(request);

        @NonNull ProjectGetByIdRequest getRequest = new ProjectGetByIdRequest(userToken);
        getRequest.setId(createdProject.getId());
        @NonNull final ProjectGetByIdResponse response = projectEndpoint.getProjectById(getRequest);

        Assert.assertEquals(Status.IN_PROGRESS, response.getProject().getStatus());
    }

    @Test
    public void testChangeProjectStatusByIndex() {
        @NonNull ProjectChangeStatusByIndexRequest requestWithNullToken = new ProjectChangeStatusByIndexRequest(null);
        requestWithNullToken.setIndex(0);
        requestWithNullToken.setStatus(Status.COMPLETED);
        Assert.assertThrows(Exception.class, () -> projectEndpoint.changeProjectStatusByIndex(requestWithNullToken));

        createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectChangeStatusByIndexRequest request = new ProjectChangeStatusByIndexRequest(userToken);
        request.setIndex(0);
        request.setStatus(Status.COMPLETED);
        projectEndpoint.changeProjectStatusByIndex(request);

        @NonNull ProjectGetByIndexRequest getRequest = new ProjectGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final ProjectGetByIndexResponse response = projectEndpoint.getProjectByIndex(getRequest);

        Assert.assertEquals(Status.COMPLETED, response.getProject().getStatus());
    }

    @Test
    public void testCompleteProjectById() {
        @NonNull final Project createdProject = createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectCompleteByIdRequest request = new ProjectCompleteByIdRequest(userToken);
        request.setId(createdProject.getId());
        projectEndpoint.completeProjectById(request);

        @NonNull ProjectGetByIdRequest getRequest = new ProjectGetByIdRequest(userToken);
        getRequest.setId(createdProject.getId());
        @NonNull final ProjectGetByIdResponse response = projectEndpoint.getProjectById(getRequest);

        Assert.assertEquals(Status.COMPLETED, response.getProject().getStatus());
    }

    @Test
    public void testCompleteProjectByIndex() {
        createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectCompleteByIndexRequest request = new ProjectCompleteByIndexRequest(userToken);
        request.setIndex(0);
        projectEndpoint.completeProjectByIndex(request);

        @NonNull ProjectGetByIndexRequest getRequest = new ProjectGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final ProjectGetByIndexResponse response = projectEndpoint.getProjectByIndex(getRequest);

        Assert.assertEquals(Status.COMPLETED, response.getProject().getStatus());
    }

    @Test
    public void testStartProjectById() {
        @NonNull final Project createdProject = createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectStartByIdRequest request = new ProjectStartByIdRequest(userToken);
        request.setId(createdProject.getId());
        projectEndpoint.startProjectById(request);

        @NonNull ProjectGetByIdRequest getRequest = new ProjectGetByIdRequest(userToken);
        getRequest.setId(createdProject.getId());
        @NonNull final ProjectGetByIdResponse response = projectEndpoint.getProjectById(getRequest);

        Assert.assertEquals(Status.IN_PROGRESS, response.getProject().getStatus());
    }

    @Test
    public void testStartProjectByIndex() {
        createProject("Test Project", "Test Description", userToken);

        @NonNull ProjectStartByIndexRequest request = new ProjectStartByIndexRequest(userToken);
        request.setIndex(0);
        projectEndpoint.startProjectByIndex(request);

        @NonNull ProjectGetByIndexRequest getRequest = new ProjectGetByIndexRequest(userToken);
        getRequest.setIndex(0);
        @NonNull final ProjectGetByIndexResponse response = projectEndpoint.getProjectByIndex(getRequest);

        Assert.assertEquals(Status.IN_PROGRESS, response.getProject().getStatus());
    }

    @Test
    public void testUserIsolation() {
        @NonNull final Project adminProject = createProject("Admin Project", "Admin Desc", adminToken);

        @NonNull ProjectGetByIdRequest userRequest = new ProjectGetByIdRequest(userToken);
        userRequest.setId(adminProject.getId());
        Assert.assertNotEquals(adminProject, projectEndpoint.getProjectById(userRequest));

        @NonNull final Project userProject = createProject("User Project", "User Desc", userToken);

        @NonNull ProjectGetByIdRequest adminRequest = new ProjectGetByIdRequest(adminToken);
        adminRequest.setId(userProject.getId());

        Assert.assertNotEquals(userProject, projectEndpoint.getProjectById(adminRequest));
    }

    @AfterClass
    public static void clear() {
        @NonNull ProjectClearRequest userRequest = new ProjectClearRequest(userToken);
        userRequest.setToken(userToken);
        projectEndpoint.clearProject(userRequest);

        @NonNull ProjectClearRequest adminRequest = new ProjectClearRequest(adminToken);
        adminRequest.setToken(adminToken);
        projectEndpoint.clearProject(adminRequest);
    }

}
