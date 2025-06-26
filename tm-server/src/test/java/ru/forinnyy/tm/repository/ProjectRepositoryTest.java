package ru.forinnyy.tm.repository;


import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.model.Project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class ProjectRepositoryTest extends AbstractUserOwnedRepositoryTest<Project> {

    @Override
    protected AbstractUserOwnedRepository<Project> createRepository() {
        return new ProjectRepository();
    }

    @Override
    protected Project createModel() {
        return new Project();
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        Project project = projectRepository.create("1", "Project");
        Assert.assertNotNull(project);
        Assert.assertEquals(project, projectRepository.findOneById(project.getId()));

        Assert.assertThrows(NullPointerException.class, () -> projectRepository.create("1", null));
        Assert.assertThrows(NullPointerException.class, () -> projectRepository.create(null, "Project"));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        Project project = projectRepository.create("1", "Project", "Description");
        Assert.assertNotNull(project);
        Assert.assertEquals(project, projectRepository.findOneById(project.getId()));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create("1", null, "Description"));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create("1", "Project", null));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(null, "Project", "Description"));
    }

    @Test
    public void testFindAllWithComparing() {
        Project projectC = createModel();
        projectC.setName("C");
        repository.add(projectC);

        Project projectB = createModel();
        projectB.setName("B");
        repository.add(projectB);

        Project projectA = createModel();
        projectA.setName("A");
        repository.add(projectA);

        List<Project> sortedExpected = Arrays.asList(projectA, projectB, projectC);
        List<Project> sortedActual = repository.findAll(Comparator.comparing(Project::getName));
        Assert.assertEquals(sortedExpected, sortedActual);
        Assert.assertThrows(NullPointerException.class, () -> repository.findAll(null));
    }

    @Test
    public void testFindAllWithComparator() {
        Project projectC = createModel();
        projectC.setName("C");
        getUserOwnedRepository().add("1", projectC);

        Project projectB = createModel();
        projectB.setName("B");
        getUserOwnedRepository().add("2", projectB);

        Project projectA = createModel();
        projectA.setName("A");
        getUserOwnedRepository().add("2", projectA);

        List<Project> sortedExpected = Arrays.asList(projectA, projectB);
        List<Project> sortedActual = getUserOwnedRepository().findAll("2", Comparator.comparing(Project::getName));
        Assert.assertEquals(sortedExpected, sortedActual);
        Assert.assertThrows(
                NullPointerException.class,
                () -> getUserOwnedRepository().findAll(null, Comparator.comparing(Project::getName))
        );
    }

    @Test
    @SneakyThrows
    public void testFindAllWithSort() {
        Project projectC = createModel();
        projectC.setName("C");
        getUserOwnedRepository().add("1", projectC);

        Project projectB = createModel();
        projectB.setName("B");
        getUserOwnedRepository().add("2", projectB);

        Project projectA = createModel();
        projectA.setName("A");
        getUserOwnedRepository().add("2", projectA);

        List<Project> sortedExpected = Arrays.asList(projectA, projectB);
        List<Project> sortedActual = getUserOwnedRepository().findAll("2", Sort.BY_NAME);
        Assert.assertEquals(sortedExpected, sortedActual);
        Assert.assertThrows(
                NullPointerException.class,
                () -> getUserOwnedRepository().findAll(null, Sort.BY_NAME)
        );
        Assert.assertThrows(
                NullPointerException.class,
                () -> getUserOwnedRepository().findAll("2", (Sort) null)
        );
    }

}
