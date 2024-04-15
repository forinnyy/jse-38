package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.ITaskRepository;
import ru.forinnyy.tm.api.ITaskService;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(final Task task) {
        if (task == null ) return null;
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
    public Task create(final String name, final String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        return taskRepository.create(name, description);
    }

    @Override
    public Task create(String name) {
        if (name == null || name.isEmpty()) return null;
        return taskRepository.create(name);
    }

    @Override
    public Task findOneById(String id) {
        if (id == null || id.isEmpty()) return null;
        return taskRepository.findOneById(id);
    }

    @Override
    public Task findOneByIndex(Integer index) {
        if (index == null || index < 0) return null;
        return taskRepository.findOneByIndex(index);
    }

    @Override
    public Task remove(Task project) {
        if (project == null) return null;
        return taskRepository.remove(project);
    }

    @Override
    public Task removeById(String id) {
        if (id == null || id.isEmpty()) return null;
        return taskRepository.removeById(id);
    }

    @Override
    public Task removeByIndex(Integer index) {
        if (index == null || index < 0) return null;
        return taskRepository.removeByIndex(index);
    }

    @Override
    public Task updateById(String id, String name, String description) {
        if (id == null || id.isEmpty()) return null;
        if (name == null || name.isEmpty()) return null;
        final Task task = findOneById(id);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateByIndex(Integer index, String name, String description) {
        if (index == null || index < 0) return null;
        if (name == null || name.isEmpty()) return null;
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task changeTaskStatusById(String id, Status status) {
        if (id == null || id.isEmpty()) return null;
        final Task task = findOneById(id);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeTaskStatusByIndex(Integer index, Status status) {
        if (index == null || index < 0) return null;
        if (index >= taskRepository.getSize()) return null;
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

}
