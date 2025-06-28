package ru.forinnyy.tm.repository;


import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.model.Project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class ProjectRepositoryTest extends AbstractUserOwnedRepositoryTest<Project> {

    private ProjectRepository projectRepository;

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

    private void initProjectRepository() {
        projectRepository = getProjectRepository();
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        initProjectRepository();

        @NonNull final Project project = projectRepository.create(UUID1, STRING);
        Assert.assertNotNull(project);
        Assert.assertEquals(project, projectRepository.findOneById(project.getId()));

        Assert.assertThrows(NullPointerException.class, () -> projectRepository.create(UUID1, null));
        Assert.assertThrows(NullPointerException.class, () -> projectRepository.create(null, STRING));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        initProjectRepository();

        @NonNull final Project project = projectRepository.create(UUID1, STRING, STRING);
        Assert.assertNotNull(project);
        Assert.assertEquals(project, projectRepository.findOneById(project.getId()));

        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(UUID1, null, STRING));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(UUID1, STRING, null));
        Assert.assertThrows(NullPointerException.class, () ->
                projectRepository.create(null, STRING, STRING));
    }

    @Test
    public void testFindAllWithComparing() {
        initProjectRepository();

        @NonNull final Project projectC = createModel();
        projectC.setName("C");
        projectRepository.add(projectC);

        @NonNull final Project projectB = createModel();
        projectB.setName("B");
        projectRepository.add(projectB);

        @NonNull final Project projectA = createModel();
        projectA.setName("A");
        projectRepository.add(projectA);

        List<Project> sortedExpected = Arrays.asList(projectA, projectB, projectC);
        List<Project> sortedActual = projectRepository.findAll(Comparator.comparing(Project::getName));
        Assert.assertEquals(sortedExpected, sortedActual);

        Assert.assertThrows(NullPointerException.class, () -> repository.findAll(null));
    }

    @Test
    public void testFindAllWithComparator() {
        initProjectRepository();
        @NonNull final Project projectC = createModel();
        projectC.setName("C");
        projectRepository.add(UUID1, projectC);

        @NonNull final Project projectB = createModel();
        projectB.setName("B");
        projectRepository.add(UUID2, projectB);

        @NonNull final Project projectA = createModel();
        projectA.setName("A");
        projectRepository.add(UUID2, projectA);

        @NonNull final List<Project> sortedExpected = Arrays.asList(projectA, projectB);
        @NonNull final List<Project> sortedActual = projectRepository.findAll(UUID2, Comparator.comparing(Project::getName));
        Assert.assertEquals(sortedExpected, sortedActual);

        Assert.assertThrows(NullPointerException.class,
                () -> projectRepository.findAll(null, Comparator.comparing(Project::getName)));
    }

    @Test
    @SneakyThrows
    public void testFindAllWithSort() {
        projectRepository = getProjectRepository();
        @NonNull final Project projectC = createModel();
        projectC.setName("C");
        projectRepository.add(UUID1, projectC);

        @NonNull final Project projectB = createModel();
        projectB.setName("B");
        projectRepository.add(UUID2, projectB);

        @NonNull final Project projectA = createModel();
        projectA.setName("A");
        projectRepository.add(UUID2, projectA);

        @NonNull final List<Project> sortedExpected = Arrays.asList(projectA, projectB);
        @NonNull final List<Project> sortedActual = projectRepository.findAll(UUID2, Sort.BY_NAME);
        Assert.assertEquals(sortedExpected, sortedActual);

        Assert.assertThrows(NullPointerException.class,
                () -> projectRepository.findAll(null, Sort.BY_NAME));
        Assert.assertThrows(NullPointerException.class,
                () -> projectRepository.findAll(UUID2, (Sort) null));
    }

}
