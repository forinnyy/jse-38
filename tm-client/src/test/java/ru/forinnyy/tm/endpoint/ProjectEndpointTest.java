package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IDomainEndpoint;
import ru.forinnyy.tm.api.endpoint.IProjectEndpoint;
import ru.forinnyy.tm.api.endpoint.IUserEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.ProjectCreateResponse;
import ru.forinnyy.tm.dto.response.ProjectGetByIdResponse;
import ru.forinnyy.tm.dto.response.ProjectGetByIndexResponse;
import ru.forinnyy.tm.dto.response.ProjectListResponse;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.service.PropertyService;


@Ignore
@Category(SoapCategory.class)
public final class ProjectEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NonNull
    private static final IUserEndpoint userEndpoint = IUserEndpoint.newInstance(propertyService);

    @NonNull
    private static final IProjectEndpoint projectEndpoint = IProjectEndpoint.newInstance(propertyService);

    private static String adminToken;

    private static String userToken;

    private static String project_id_one;

    private static int project_index_one;

    private static String project_id_two;

    private static int project_index_two;

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
        requestWithNullToken.setToken(null);
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

}
