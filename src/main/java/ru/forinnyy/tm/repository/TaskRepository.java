package ru.forinnyy.tm.repository;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.model.Task;
import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task>
        implements ITaskRepository {

    @NonNull
    @Override
    public List<Task> findAllByProjectId(@NonNull final String userId, @NonNull final String projectId) {
        return findAll().stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> projectId.equals(m.getProjectId()))
                .collect(Collectors.toList());
    }

    @NonNull
    @Override
    public Task create(@NonNull final String userId, @NonNull final String name) {
        @NonNull final Task task = new Task();
        task.setName(name);
        return add(userId, task);
    }

    @NonNull
    @Override
    public Task create(@NonNull final String userId, @NonNull final String name, @NonNull final String description) {
        @NonNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(userId, task);
    }

}
