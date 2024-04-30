package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.DescriptionEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.exception.field.NameEmptyException;
import ru.forinnyy.tm.exception.field.TaskIdEmptyException;
import ru.forinnyy.tm.model.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(final Task task) {
        if (task == null ) throw new TaskNotFoundException();
        return taskRepository.add(task);
    }

    @Override
    public int getSize() {
        return taskRepository.getSize();
    }

    @Override
    public void clear() {
        taskRepository.clear();
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAll(Comparator<Task> comparator) {
        if (comparator == null) return findAll();
        return taskRepository.findAll(comparator);
    }

    @Override
    public List<Task> findAll(Sort sort) {
        if (sort == null) return findAll();
        final Comparator<Task> comparator = sort.getComparator();
        return findAll(comparator);
    }

    @Override
    public List<Task> findAllByProjectId(String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Task create(final String name, final String description) {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return taskRepository.create(name, description);
    }

    @Override
    public Task create(String name) {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return taskRepository.create(name);
    }

    @Override
    public Task findOneById(String id) {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        return taskRepository.findOneById(id);
    }

    @Override
    public Task findOneByIndex(Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return taskRepository.findOneByIndex(index);
    }

    @Override
    public Task remove(Task task) {
        if (task == null) throw new TaskNotFoundException();
        return taskRepository.remove(task);
    }

    @Override
    public Task removeById(String id) {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        return taskRepository.removeById(id);
    }

    @Override
    public Task removeByIndex(Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return taskRepository.removeByIndex(index);
    }

    @Override
    public Task updateById(String id, String name, String description) {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Task task = findOneById(id);
        if (task == null) throw new TaskNotFoundException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateByIndex(Integer index, String name, String description) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Task task = findOneByIndex(index);
        if (task == null) throw new TaskNotFoundException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task changeTaskStatusById(String id, Status status) {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        final Task task = findOneById(id);
        if (task == null) throw new TaskNotFoundException();
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeTaskStatusByIndex(Integer index, Status status) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (index >= taskRepository.getSize()) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) throw new TaskNotFoundException();
        task.setStatus(status);
        return task;
    }

}
