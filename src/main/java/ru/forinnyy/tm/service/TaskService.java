package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.Task;

import java.util.Collections;
import java.util.List;

public final class TaskService extends AbstractUserOwnedService<Task, ITaskRepository>
        implements ITaskService {

    public TaskService(ITaskRepository repository) {
        super(repository);
    }

    @NotNull
    @Override
    public List<Task> findAllByProjectId(@Nullable final String userId, @Nullable String projectId) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return repository.findAllByProjectId(userId, projectId);
    }

    @NotNull
    @Override
    public Task create(@Nullable final String userId, @Nullable final String name, @Nullable final String description) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(userId, name, description);
    }

    @NotNull
    @Override
    public Task create(@Nullable final String userId, @Nullable final String name) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(userId, name);
    }

    @NotNull
    @Override
    public Task updateById(@Nullable final String userId, @Nullable final String id, @Nullable final String name, @Nullable final String description)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Task task = findOneById(userId, id);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @NotNull
    @Override
    public Task updateByIndex(@Nullable final String userId, @Nullable final Integer index, @Nullable final String name, @Nullable final String description)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Task task = findOneByIndex(userId, index);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @NotNull
    @Override
    public Task changeTaskStatusById(@Nullable final String userId, @Nullable final String id, @NotNull final Status status)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        final Task task = findOneById(userId, id);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setStatus(status);
        return task;
    }

    @NotNull
    @Override
    public Task changeTaskStatusByIndex(@Nullable final String userId, @Nullable final Integer index, @NotNull final Status status)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (index >= repository.getSize()) throw new IndexIncorrectException();
        final Task task = findOneByIndex(userId, index);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setStatus(status);
        return task;
    }

}
