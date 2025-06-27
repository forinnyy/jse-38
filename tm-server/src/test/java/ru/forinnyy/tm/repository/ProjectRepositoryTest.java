package ru.forinnyy.tm.repository;


import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.model.Project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class ProjectRepositoryTest extends AbstractUserOwnedRepositoryTest<Project> {

    @Override
    protected AbstractUserOwnedRepository<Project> createRepository() {
        return new ProjectRepository();
    }

    @Override
    protected Project createModel() {
        @NonNull final Project project = new Project();
        project.setUserId(UUID1);
        return project;
    }

    public ProjectRepository getProjectRepository() {
        return (ProjectRepository) createRepository();
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        @NonNull final Project project = getProjectRepository().create(UUID1, STRING);
        repository.add(project);
        Assert.assertNotNull(project);
        Assert.assertEquals(project, repository.findOneById(project.getId()));

        Assert.assertThrows(NullPointerException.class, () -> getProjectRepository().create(UUID1, null));
        Assert.assertThrows(NullPointerException.class, () -> getProjectRepository().create(null, STRING));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        @NonNull final Project project = getProjectRepository().create(UUID1, STRING, STRING);
        repository.add(project);
        Assert.assertNotNull(project);
        Assert.assertEquals(project, repository.findOneById(project.getId()));
        Assert.assertThrows(NullPointerException.class, () ->
                getProjectRepository().create(UUID1, null, STRING));
        Assert.assertThrows(NullPointerException.class, () ->
                getProjectRepository().create(UUID1, STRING, null));
        Assert.assertThrows(NullPointerException.class, () ->
                getProjectRepository().create(null, STRING, STRING));
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
        getUserOwnedRepository().add(UUID1, projectC);

        Project projectB = createModel();
        projectB.setName("B");
        getUserOwnedRepository().add(UUID2, projectB);

        Project projectA = createModel();
        projectA.setName("A");
        getUserOwnedRepository().add(UUID2, projectA);

        List<Project> sortedExpected = Arrays.asList(projectA, projectB);
        List<Project> sortedActual = getUserOwnedRepository().findAll(UUID2, Comparator.comparing(Project::getName));
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
        getUserOwnedRepository().add(UUID1, projectC);

        Project projectB = createModel();
        projectB.setName("B");
        getUserOwnedRepository().add(UUID2, projectB);

        Project projectA = createModel();
        projectA.setName("A");
        getUserOwnedRepository().add(UUID2, projectA);

        List<Project> sortedExpected = Arrays.asList(projectA, projectB);
        List<Project> sortedActual = getUserOwnedRepository().findAll(UUID2, Sort.BY_NAME);
        Assert.assertEquals(sortedExpected, sortedActual);
        Assert.assertThrows(
                NullPointerException.class,
                () -> getUserOwnedRepository().findAll(null, Sort.BY_NAME)
        );
        Assert.assertThrows(
                NullPointerException.class,
                () -> getUserOwnedRepository().findAll(UUID2, (Sort) null)
        );
    }

}
