package ru.forinnyy.tm.repository;


import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.model.Project;

public final class ProjectRepositoryTest extends AbstractRepositoryTest<Project> {

    @Override
    protected AbstractRepository<Project> createRepository() {
        return new ProjectRepository();
    }

    @Override
    protected Project createModel() {
        return new Project();
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        Project project = projectRepository.create(testUser.getId(), "Project");
        Assert.assertNotNull(project);
        Assert.assertEquals(project, projectRepository.findOneById(project.getId()));

        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(testUser.getId(), null));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(null, "Project"));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        Project project = projectRepository.create(testUser.getId(), "Project", "Description");
        Assert.assertNotNull(project);
        Assert.assertEquals(project, projectRepository.findOneById(project.getId()));

        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(testUser.getId(), null, "Description"));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(testUser.getId(), "Project", null));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(null, "Project", "Description"));
    }

}
