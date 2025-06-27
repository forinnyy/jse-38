package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.repository.ProjectRepository;

public class ProjectServiceTest extends AbstractUserOwnedServiceTest<Project, IProjectRepository> {

    @Override
    protected IProjectRepository createRepository() {
        return new ProjectRepository();
    }

    @Override
    protected AbstractUserOwnedService<Project, IProjectRepository> createService() {
        return new ProjectService(repository);
    }

    @Override
    protected Project createModel() {
        @NonNull final Project project = new Project();
        project.setUserId(UUID1);
        return project;
    }

    protected ProjectService getProjectService() {
        return (ProjectService) createService();
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        @NonNull final Project project = getProjectService().create(UUID1, STRING);
        Assert.assertEquals(repository.findOneById(project.getId()), project);

        Assert.assertThrows(UserIdEmptyException.class, () -> getProjectService().create(null, STRING));
        Assert.assertThrows(UserIdEmptyException.class, () -> getProjectService().create(EMPTY_STRING, STRING));

        Assert.assertThrows(NameEmptyException.class, () -> getProjectService().create(UUID1, null));
        Assert.assertThrows(NameEmptyException.class, () -> getProjectService().create(UUID1, EMPTY_STRING));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        @NonNull final Project project = getProjectService().create(UUID1, STRING, STRING);
        Assert.assertEquals(repository.findOneById(project.getId()), project);

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().create(null, STRING, STRING));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().create(EMPTY_STRING, STRING, STRING));

        Assert.assertThrows(NameEmptyException.class,
                () -> getProjectService().create(UUID1, null, STRING));
        Assert.assertThrows(NameEmptyException.class,
                () -> getProjectService().create(UUID1, EMPTY_STRING, STRING));

        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getProjectService().create(UUID1, STRING, null));
        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getProjectService().create(UUID1, STRING, EMPTY_STRING));
    }

    @Test
    @SneakyThrows
    public void testUpdateById() {
        @NonNull final Project project = createModel();
        repository.add(project);
        getProjectService().updateById(UUID1, project.getId(), STRING, STRING);
        Assert.assertEquals(STRING, getProjectService().findOneById(UUID1, project.getId()).getName());
        Assert.assertEquals(STRING, getProjectService().findOneById(UUID1, project.getId()).getDescription());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().updateById(null, STRING, STRING, STRING));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().updateById(EMPTY_STRING, STRING, STRING, STRING));

        Assert.assertThrows(ProjectIdEmptyException.class,
                () -> getProjectService().updateById(UUID1, null, STRING, STRING));
        Assert.assertThrows(ProjectIdEmptyException.class,
                () -> getProjectService().updateById(UUID1, EMPTY_STRING, STRING, STRING));

        Assert.assertThrows(NameEmptyException.class,
                () -> getProjectService().updateById(UUID1, STRING, null, STRING));
        Assert.assertThrows(NameEmptyException.class,
                () -> getProjectService().updateById(UUID1, STRING, EMPTY_STRING, STRING));

        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getProjectService().updateById(UUID1, STRING, STRING, null));
        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getProjectService().updateById(UUID1, STRING, STRING, EMPTY_STRING));

        Assert.assertThrows(ProjectNotFoundException.class,
                () -> getProjectService().updateById(UUID2, project.getId(), STRING, STRING));
        Assert.assertThrows(ProjectNotFoundException.class,
                () -> getProjectService().updateById(UUID1, STRING, STRING, STRING));
    }

    @Test
    @SneakyThrows
    public void testUpdateByIndex() {
        @NonNull final Project project = createModel();
        repository.add(project);
        getProjectService().updateByIndex(UUID1, 0, STRING, STRING);
        Assert.assertEquals(STRING, repository.findOneById(UUID1, project.getId()).getName());
        Assert.assertEquals(STRING, repository.findOneById(UUID1, project.getId()).getDescription());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().updateByIndex(null, 0, STRING, STRING));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().updateByIndex(EMPTY_STRING, 0, STRING, STRING));

        Assert.assertThrows(IndexIncorrectException.class,
                () -> getProjectService().updateByIndex(UUID1, null, STRING, STRING));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getProjectService().updateByIndex(UUID1, -1, STRING, STRING));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getProjectService().updateByIndex(UUID1, 2, STRING, STRING));

        Assert.assertThrows(NameEmptyException.class,
                () -> getProjectService().updateByIndex(UUID1, 0, null, STRING));
        Assert.assertThrows(NameEmptyException.class,
                () -> getProjectService().updateByIndex(UUID1, 0, EMPTY_STRING, STRING));

        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getProjectService().updateByIndex(UUID1, 0, STRING, null));
        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getProjectService().updateByIndex(UUID1, 0, STRING, EMPTY_STRING));

        Assert.assertThrows(ProjectNotFoundException.class,
                () -> getProjectService().updateByIndex(UUID2, 0, STRING, STRING));
    }

    @Test
    @SneakyThrows
    public void testChangeProjectStatusById() {
        @NonNull final Project project = createModel();
        repository.add(project);
        Assert.assertEquals(Status.NOT_STARTED, service.findOneById(project.getId()).getStatus());

        getProjectService().changeProjectStatusById(UUID1, project.getId(), Status.IN_PROGRESS);
        Assert.assertEquals(Status.IN_PROGRESS, service.findOneById(project.getId()).getStatus());

        getProjectService().changeProjectStatusById(UUID1, project.getId(), Status.COMPLETED);
        Assert.assertEquals(Status.COMPLETED, getProjectService().findOneById(project.getId()).getStatus());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().changeProjectStatusById(null, STRING, Status.IN_PROGRESS));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().changeProjectStatusById(EMPTY_STRING, STRING, Status.IN_PROGRESS));

        Assert.assertThrows(ProjectIdEmptyException.class,
                () -> getProjectService().changeProjectStatusById(UUID1, null, Status.IN_PROGRESS));
        Assert.assertThrows(ProjectIdEmptyException.class,
                () -> getProjectService().changeProjectStatusById(UUID1, EMPTY_STRING, Status.IN_PROGRESS));

        Assert.assertThrows(ProjectNotFoundException.class,
                () -> getProjectService().changeProjectStatusById(UUID2, project.getId(), Status.IN_PROGRESS));
        Assert.assertThrows(ProjectNotFoundException.class,
                () -> getProjectService().changeProjectStatusById(UUID1, STRING, Status.IN_PROGRESS));

        Assert.assertThrows(NullPointerException.class,
                () -> getProjectService().changeProjectStatusById(UUID1, project.getId(), null));
    }

    @Test
    @SneakyThrows
    public void testChangeProjectStatusByIndex() {
        @NonNull final Project project = createModel();
        repository.add(project);
        Assert.assertEquals(Status.NOT_STARTED, service.findOneById(project.getId()).getStatus());

        getProjectService().changeProjectStatusByIndex(UUID1, 0, Status.IN_PROGRESS);
        Assert.assertEquals(Status.IN_PROGRESS, service.findOneById(project.getId()).getStatus());

        getProjectService().changeProjectStatusByIndex(UUID1, 0, Status.COMPLETED);
        Assert.assertEquals(Status.COMPLETED, getProjectService().findOneById(project.getId()).getStatus());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().changeProjectStatusByIndex(null, 0, Status.IN_PROGRESS));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getProjectService().changeProjectStatusByIndex("", 0, Status.IN_PROGRESS));

        Assert.assertThrows(IndexIncorrectException.class,
                () -> getProjectService().changeProjectStatusByIndex(UUID1, null, Status.IN_PROGRESS));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getProjectService().changeProjectStatusByIndex(UUID1, -1, Status.IN_PROGRESS));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getProjectService().changeProjectStatusByIndex(UUID1, 2, Status.IN_PROGRESS));

        Assert.assertThrows(ProjectNotFoundException.class,
                () -> getProjectService().changeProjectStatusByIndex(UUID2, 0, Status.IN_PROGRESS));

        Assert.assertThrows(NullPointerException.class,
                () -> getProjectService().changeProjectStatusByIndex(UUID1, 0, null));
    }

}
