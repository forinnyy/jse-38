package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.model.Task;
import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task>
        implements ITaskRepository {

    @Override
    public List<Task> findAllByProjectId(final String userId, final String projectId) {
        return findAll().stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> projectId.equals(m.getProjectId()))
                .collect(Collectors.toList());
    }

    @Override
    public Task create(final String userId, final String name) {
        final Task task = new Task();
        task.setName(name);
        return add(userId, task);
    }

    @Override
    public Task create(final String userId, final String name, final String description) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(userId, task);
    }

}
