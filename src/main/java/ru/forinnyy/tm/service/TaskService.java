package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.ITaskRepository;
import ru.forinnyy.tm.api.ITaskService;
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
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(task);
    }

}
