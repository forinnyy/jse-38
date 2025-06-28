package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.model.User;

import java.util.List;

public final class TaskRepositoryTest extends AbstractUserOwnedRepositoryTest<Task> {

    private TaskRepository taskRepository;

    @Override
    protected AbstractUserOwnedRepository<Task> createRepository() {
        return new TaskRepository();
    }

    @Override
    protected Task createModel() {
        return new Task();
    }

    private TaskRepository getTaskRepository() {
        return (TaskRepository) createRepository();
    }

    private void initTaskRepository() {
        taskRepository = getTaskRepository();
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        initTaskRepository();
        @NonNull final Task task = taskRepository.create(UUID1, STRING);
        Assert.assertNotNull(task);
        Assert.assertEquals(task, taskRepository.findOneById(task.getId()));

        Assert.assertThrows(NullPointerException.class, () -> taskRepository.create(UUID1, null));
        Assert.assertThrows(NullPointerException.class, () -> taskRepository.create(null, STRING));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        initTaskRepository();
        @NonNull final Task task = taskRepository.create(UUID1, STRING, STRING);
        Assert.assertNotNull(task);
        Assert.assertEquals(task, taskRepository.findOneById(task.getId()));

        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.create(UUID1, null, STRING));
        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.create(UUID1, STRING, null));
        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.create(null, STRING, STRING));
    }

    @Test
    @SneakyThrows
    public void testFindAllByProjectId() {
        initTaskRepository();

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

        taskRepository.add(taskOne);
        taskRepository.add(taskTwo);

        @NonNull final List<Task> tasks = taskRepository.findAllByProjectId(UUID1, UUID2);

        Assert.assertNotNull(tasks);
        Assert.assertEquals(2, tasks.size());
        for (@NonNull final Task task : tasks) {
            Assert.assertEquals(user.getId(), task.getUserId());
            Assert.assertEquals(project.getId(), task.getProjectId());
        }

        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.findAllByProjectId(null, UUID1));
        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.findAllByProjectId(UUID1, null));
        Assert.assertNotNull(tasks);
    }

}
