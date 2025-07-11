package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.marker.UnitCategory;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.repository.TaskRepository;

import java.util.Collections;
import java.util.List;


@Category(UnitCategory.class)
public final class TaskServiceTest extends AbstractUserOwnedServiceTest<Task, ITaskRepository> {

    @Override
    protected ITaskRepository createRepository() {
        return new TaskRepository();
    }

    @Override
    protected AbstractService<Task, ITaskRepository> createService() {
        return new TaskService(repository);
    }

    @Override
    protected Task createModel() {
        @NonNull final Task task = new Task();
        task.setUserId(UUID1);
        return task;
    }

    protected TaskService getTaskService() {
        return (TaskService) createService();
    }

    @Test
    @SneakyThrows
    public void testFindAllByProjectId() {
        @NonNull final User user = new User();
        user.setId(UUID1);

        @NonNull final Project project = new Project();
        project.setUserId(UUID1);
        project.setId(UUID2);

        @NonNull final Task taskOne = new Task();
        taskOne.setUserId(UUID1);
        taskOne.setProjectId(UUID2);

        @NonNull final Task taskTwo = new Task();
        taskTwo.setUserId(UUID1);
        taskTwo.setProjectId(UUID2);

        getTaskService().add(UUID1, taskOne);
        getTaskService().add(UUID1, taskTwo);

        @NonNull final List<Task> tasks = getTaskService().findAllByProjectId(UUID1, UUID2);

        Assert.assertEquals(2, tasks.size());
        for (@NonNull final Task task : tasks) {
            Assert.assertEquals(user.getId(), task.getUserId());
            Assert.assertEquals(project.getId(), task.getProjectId());
        }
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().findAllByProjectId(null, UUID2));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().findAllByProjectId("", UUID2));

        Assert.assertEquals(Collections.emptyList(), getTaskService().findAllByProjectId(UUID1, null));
        Assert.assertEquals(Collections.emptyList(), getTaskService().findAllByProjectId(UUID1, ""));
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        @NonNull final Task task = getTaskService().create(UUID1, STRING);
        Assert.assertEquals(repository.findOneById(task.getId()), task);

        Assert.assertThrows(UserIdEmptyException.class, () -> getTaskService().create(null, STRING));
        Assert.assertThrows(UserIdEmptyException.class, () -> getTaskService().create(EMPTY_STRING, STRING));

        Assert.assertThrows(NameEmptyException.class, () -> getTaskService().create(UUID1, null));
        Assert.assertThrows(NameEmptyException.class, () -> getTaskService().create(UUID1, EMPTY_STRING));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        @NonNull final Task task = getTaskService().create(UUID1, STRING, STRING);
        Assert.assertEquals(repository.findOneById(task.getId()), task);

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().create(null, STRING, STRING));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().create(EMPTY_STRING, STRING, STRING));

        Assert.assertThrows(NameEmptyException.class,
                () -> getTaskService().create(UUID1, null, STRING));
        Assert.assertThrows(NameEmptyException.class,
                () -> getTaskService().create(UUID1, EMPTY_STRING, STRING));

        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getTaskService().create(UUID1, STRING, null));
        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getTaskService().create(UUID1, STRING, EMPTY_STRING));
    }

    @Test
    @SneakyThrows
    public void testUpdateById() {
        @NonNull final Task task = createModel();
        repository.add(task);
        getTaskService().updateById(UUID1, task.getId(), STRING, STRING);
        Assert.assertEquals(STRING, getTaskService().findOneById(UUID1, task.getId()).getName());
        Assert.assertEquals(STRING, getTaskService().findOneById(UUID1, task.getId()).getDescription());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().updateById(null, STRING, STRING, STRING));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().updateById(EMPTY_STRING, STRING, STRING, STRING));

        Assert.assertThrows(TaskIdEmptyException.class,
                () -> getTaskService().updateById(UUID1, null, STRING, STRING));
        Assert.assertThrows(TaskIdEmptyException.class,
                () -> getTaskService().updateById(UUID1, EMPTY_STRING, STRING, STRING));

        Assert.assertThrows(NameEmptyException.class,
                () -> getTaskService().updateById(UUID1, STRING, null, STRING));
        Assert.assertThrows(NameEmptyException.class,
                () -> getTaskService().updateById(UUID1, STRING, EMPTY_STRING, STRING));

        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getTaskService().updateById(UUID1, STRING, STRING, null));
        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getTaskService().updateById(UUID1, STRING, STRING, EMPTY_STRING));

        Assert.assertThrows(TaskNotFoundException.class,
                () -> getTaskService().updateById(UUID2, STRING, STRING, STRING));
        Assert.assertThrows(TaskNotFoundException.class,
                () -> getTaskService().updateById(UUID1, STRING, STRING, STRING));

        // TODO PermissionException.class
    }

    @Test
    @SneakyThrows
    public void testUpdateByIndex() {
        @NonNull final Task task = createModel();
        repository.add(task);
        getTaskService().updateByIndex(UUID1, 0, STRING, STRING);
        Assert.assertEquals(STRING, repository.findOneById(UUID1, task.getId()).getName());
        Assert.assertEquals(STRING, repository.findOneById(UUID1, task.getId()).getDescription());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().updateByIndex(null, 0, STRING, STRING));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().updateByIndex(EMPTY_STRING, 0, STRING, STRING));

        Assert.assertThrows(IndexIncorrectException.class,
                () -> getTaskService().updateByIndex(UUID1, null, STRING, STRING));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getTaskService().updateByIndex(UUID1, -1, STRING, STRING));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getTaskService().updateByIndex(UUID1, 2, STRING, STRING));

        Assert.assertThrows(NameEmptyException.class,
                () -> getTaskService().updateByIndex(UUID1, 0, null, STRING));
        Assert.assertThrows(NameEmptyException.class,
                () -> getTaskService().updateByIndex(UUID1, 0, EMPTY_STRING, STRING));

        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getTaskService().updateByIndex(UUID1, 0, STRING, null));
        Assert.assertThrows(DescriptionEmptyException.class,
                () -> getTaskService().updateByIndex(UUID1, 0, STRING, EMPTY_STRING));

        Assert.assertThrows(TaskNotFoundException.class,
                () -> getTaskService().updateByIndex(UUID2, 0, STRING, STRING));

        // TODO PermissionException.class
    }

    @Test
    @SneakyThrows
    public void testChangeTaskStatusById() {
        @NonNull final Task task = createModel();
        repository.add(task);
        Assert.assertEquals(Status.NOT_STARTED, service.findOneById(task.getId()).getStatus());

        getTaskService().changeTaskStatusById(UUID1, task.getId(), Status.IN_PROGRESS);
        Assert.assertEquals(Status.IN_PROGRESS, service.findOneById(task.getId()).getStatus());

        getTaskService().changeTaskStatusById(UUID1, task.getId(), Status.COMPLETED);
        Assert.assertEquals(Status.COMPLETED, getTaskService().findOneById(task.getId()).getStatus());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().changeTaskStatusById(null, STRING, Status.IN_PROGRESS));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().changeTaskStatusById(EMPTY_STRING, STRING, Status.IN_PROGRESS));

        Assert.assertThrows(TaskIdEmptyException.class,
                () -> getTaskService().changeTaskStatusById(UUID1, null, Status.IN_PROGRESS));
        Assert.assertThrows(TaskIdEmptyException.class,
                () -> getTaskService().changeTaskStatusById(UUID1, EMPTY_STRING, Status.IN_PROGRESS));

        Assert.assertThrows(TaskNotFoundException.class,
                () -> getTaskService().changeTaskStatusById(UUID2, task.getId(), Status.IN_PROGRESS));
        Assert.assertThrows(TaskNotFoundException.class,
                () -> getTaskService().changeTaskStatusById(UUID1, STRING, Status.IN_PROGRESS));

        Assert.assertThrows(NullPointerException.class,
                () -> getTaskService().changeTaskStatusById(UUID1, task.getId(), null));

        // TODO PermissionException.class
    }

    @Test
    @SneakyThrows
    public void testChangeTaskStatusByIndex() {
        @NonNull final Task task = createModel();
        repository.add(task);
        Assert.assertEquals(Status.NOT_STARTED, service.findOneById(task.getId()).getStatus());

        getTaskService().changeTaskStatusByIndex(UUID1, 0, Status.IN_PROGRESS);
        Assert.assertEquals(Status.IN_PROGRESS, service.findOneById(task.getId()).getStatus());

        getTaskService().changeTaskStatusByIndex(UUID1, 0, Status.COMPLETED);
        Assert.assertEquals(Status.COMPLETED, getTaskService().findOneById(task.getId()).getStatus());

        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().changeTaskStatusByIndex(null, 0, Status.IN_PROGRESS));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> getTaskService().changeTaskStatusByIndex("", 0, Status.IN_PROGRESS));

        Assert.assertThrows(IndexIncorrectException.class,
                () -> getTaskService().changeTaskStatusByIndex(UUID1, null, Status.IN_PROGRESS));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getTaskService().changeTaskStatusByIndex(UUID1, -1, Status.IN_PROGRESS));
        Assert.assertThrows(IndexIncorrectException.class,
                () -> getTaskService().changeTaskStatusByIndex(UUID1, 2, Status.IN_PROGRESS));

        Assert.assertThrows(TaskNotFoundException.class,
                () -> getTaskService().changeTaskStatusByIndex(UUID2, 0, Status.IN_PROGRESS));

        Assert.assertThrows(NullPointerException.class,
                () -> getTaskService().changeTaskStatusByIndex(UUID1, 0, null));

        // TODO PermissionException.class
    }

}
