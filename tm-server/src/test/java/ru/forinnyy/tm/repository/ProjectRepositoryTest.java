package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ProjectRepositoryTest {

    private static final int NUMBER_OF_ENTRIES = 10;

    private static final String USER_ID_1 = UUID.randomUUID().toString();

    private static final String USER_ID_2 = UUID.randomUUID().toString();

    @NonNull
    private List<Project> projectList;

    @NonNull
    private IProjectRepository projectRepository;

    @Before
    @SneakyThrows
    public void initRepository() {
        projectList = new ArrayList<>();
        projectRepository = new ProjectRepository();
        for (int i = 1; i < NUMBER_OF_ENTRIES; i++) {
            @NonNull final Project project = new Project();
            project.setName("Project's name " + i);
            project.setDescription("Project's description " + i);
            if (i <= 5) project.setUserId(USER_ID_1);
            else project.setUserId(USER_ID_2);
            projectRepository.add(project);
            projectList.add(project);
        }
    }

    @Test
    @SneakyThrows
    public void testAddProject() {
        int expectedNumberOfEntries = NUMBER_OF_ENTRIES + 1;
        @NonNull final Project newProject = new Project();
        projectRepository.add(newProject);
        Assert.assertEquals(expectedNumberOfEntries, NUMBER_OF_ENTRIES);
    }

    @Test
    @SneakyThrows
    public void testAddProjectWithData() {
        int expectedNumberOfEntries = NUMBER_OF_ENTRIES + 1;
        @NonNull final String userId = UUID.randomUUID().toString();
        @NonNull final String name = "Project's name";
        @NonNull final String description = "Project's description";
        @NonNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        projectRepository.add(userId, project);
        @NonNull final Project checkProject = projectRepository.findOneById(project.getId());
        Assert.assertNotNull(checkProject);
        Assert.assertEquals(checkProject.getUserId(), userId);
        Assert.assertEquals(checkProject.getName(), name);
        Assert.assertEquals(checkProject.getDescription(), description);
        Assert.assertEquals(expectedNumberOfEntries, NUMBER_OF_ENTRIES);
    }

}
