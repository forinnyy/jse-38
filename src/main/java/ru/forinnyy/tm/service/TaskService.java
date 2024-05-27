package ru.forinnyy.tm.service;

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

    @Override
    public List<Task> findAllByProjectId(final String userId, String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return repository.findAllByProjectId(userId, projectId);
    }

    @Override
    public Task create(final String userId, final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(userId, name, description);
    }

    @Override
    public Task create(final String userId, final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(userId, name);
    }

    @Override
    public Task updateById(final String userId, final String id, final String name, final String description)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Task task = findOneById(id);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateByIndex(final String userId, final Integer index, final String name, final String description)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Task task = findOneByIndex(index);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task changeTaskStatusById(final String userId, final String id, final Status status)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        final Task task = findOneById(id);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeTaskStatusByIndex(final String userId, final Integer index, final Status status)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (index >= repository.getSize()) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setStatus(status);
        return task;
    }

}
