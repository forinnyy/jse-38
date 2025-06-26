package ru.forinnyy.tm.repository;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public final class TaskRepositoryTest extends AbstractUserOwnedRepositoryTest<Task> {

    @Override
    protected AbstractUserOwnedRepository<Task> createRepository() {
        return new TaskRepository();
    }

    @Override
    protected Task createModel() {
        return new Task();
    }

    @Test
    @SneakyThrows
    public void testCreateWithName() {
        Task task = taskRepository.create("1", "Task");
        Assert.assertNotNull(task);
        Assert.assertEquals(task, taskRepository.findOneById(task.getId()));

        Assert.assertThrows(NullPointerException.class, () -> taskRepository.create("1", null));
        Assert.assertThrows(NullPointerException.class, () -> taskRepository.create(null, "Task"));
    }

    @Test
    @SneakyThrows
    public void testCreateWithNameAndDescription() {
        Task task = taskRepository.create("1", "Task", "Description");
        Assert.assertNotNull(task);
        Assert.assertEquals(task, taskRepository.findOneById(task.getId()));

        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.create("1", null, "Description"));
        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.create("1", "Task", null));
        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.create(null, "Task", "Description"));
    }

    @Test
    @SneakyThrows
    public void testFindAllByProjectId() {
        List<Task> filteredTasks = taskRepository.findAllByProjectId(testUser.getId(), project_two.getId());
        List<Task> allTasks = taskRepository.findAll();

        Assert.assertNotNull(filteredTasks);
        Assert.assertEquals(2, filteredTasks.size());
        Assert.assertNotEquals(filteredTasks, allTasks);
        for (Task task : filteredTasks) {
            Assert.assertEquals(testUser.getId(), task.getUserId());
            Assert.assertEquals(project_two.getId(), task.getProjectId());
        }
        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.findAllByProjectId(null, "ID"));
        Assert.assertThrows(NullPointerException.class, () ->
                taskRepository.findAllByProjectId("ID", null));
        Assert.assertNotNull(filteredTasks);
    }

}
