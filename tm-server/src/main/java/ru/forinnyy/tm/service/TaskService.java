package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.repository.TaskRepository;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public final class TaskService extends AbstractUserOwnedService<Task, ITaskRepository>
        implements ITaskService {

    public TaskService(@NonNull IConnectionService connectionService) {
        super(connectionService);
    }

    @NonNull
    public ITaskRepository getRepository(@NonNull final Connection connection) {
        return new TaskRepository(connection);
    }

    @Override
    @SneakyThrows
    public void initTable() {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);
            repository.initTable();
            connection.commit();
        }
    }


    @NonNull
    @Override
    @SneakyThrows
    public List<Task> findAllByProjectId(final String userId, String projectId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);
            return repository.findAllByProjectId(userId, projectId);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task create(final String userId, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);
            @NonNull final Task task = repository.create(userId, name, description);
            connection.commit();
            return task;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task create(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);
            @NonNull final Task task = repository.create(userId, name);
            connection.commit();
            return task;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task updateById(final String userId, final String id, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);

            final Task task = repository.findOneById(userId, id);
            if (task == null) throw new TaskNotFoundException();
            if (!task.getUserId().equals(userId)) throw new PermissionException();

            task.setName(name);
            task.setDescription(description);
            repository.update(task);

            connection.commit();
            return task;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task updateByIndex(final String userId, final Integer index, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);

            if (index == null || index < 0 || index >= repository.getSize(userId)) {
                throw new IndexIncorrectException();
            }

            final Task task = repository.findOneByIndex(userId, index);
            if (task == null) throw new TaskNotFoundException();
            if (!task.getUserId().equals(userId)) throw new PermissionException();

            task.setName(name);
            task.setDescription(description);
            repository.update(task);

            connection.commit();
            return task;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task changeTaskStatusById(final String userId, final String id, @NonNull final Status status) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);

            final Task task = repository.findOneById(userId, id);
            if (task == null) throw new TaskNotFoundException();
            if (!task.getUserId().equals(userId)) throw new PermissionException();

            task.setStatus(status);
            repository.update(task);

            connection.commit();
            return task;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task changeTaskStatusByIndex(final String userId, final Integer index, @NonNull final Status status) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository repository = getRepository(connection);

            if (index == null || index < 0 || index >= repository.getSize(userId)) {
                throw new IndexIncorrectException();
            }

            final Task task = repository.findOneByIndex(userId, index);
            if (task == null) throw new TaskNotFoundException();
            if (!task.getUserId().equals(userId)) throw new PermissionException();

            task.setStatus(status);
            repository.update(task);

            connection.commit();
            return task;
        }
    }

}
