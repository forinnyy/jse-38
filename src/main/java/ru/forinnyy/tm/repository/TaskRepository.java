package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.ITaskRepository;
import ru.forinnyy.tm.model.Task;

import java.util.ArrayList;
import java.util.List;

public final class TaskRepository implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public Task add(final Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public void clear() {
        tasks.clear();
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

}
