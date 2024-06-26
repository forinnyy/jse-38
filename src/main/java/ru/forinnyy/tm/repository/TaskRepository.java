package ru.forinnyy.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.model.Task;
import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task>
        implements ITaskRepository {

    @NotNull
    @Override
    public List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        return findAll().stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> projectId.equals(m.getProjectId()))
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public Task create(@NotNull final String userId, @NotNull final String name) {
        @NotNull final Task task = new Task();
        task.setName(name);
        return add(userId, task);
    }

    @NotNull
    @Override
    public Task create(@NotNull final String userId, @NotNull final String name, @NotNull final String description) {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(userId, task);
    }

}
