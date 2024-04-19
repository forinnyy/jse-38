package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.ITaskRepository;
import ru.forinnyy.tm.model.Task;
import java.util.ArrayList;
import java.util.List;

public final class TaskRepository implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public List<Task> findAllByProjectId(String projectId) {
        final List<Task> result = new ArrayList<>();
        for (final Task task: tasks) {
            if (task.getProjectId() == null) continue;
            if (task.getProjectId().equals(projectId)) result.add(task);
        }
        return result;
    }

    @Override
    public Task add(final Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public int getSize() {
        return tasks.size();
    }

    @Override
    public void clear() {
        tasks.clear();
    }

    @Override
    public Task create(String name) {
        final Task task = new Task();
        task.setName(name);
        return add(task);
    }

    @Override
    public Task create(String name, String description) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(task);
    }

    @Override
    public Task findOneById(String id) {
        for (final Task task: tasks) {
            if (id.equals(task.getId())) return task;
        }
        return null;
    }

    @Override
    public Task findOneByIndex(Integer index) {
        return tasks.get(index);
    }

    @Override
    public Task remove(Task task) {
        tasks.remove(task);
        return task;
    }

    @Override
    public Task removeById(String id) {
        final Task task = findOneById(id);
        if (task == null) return null;
        return remove(task);
    }

    @Override
    public Task removeByIndex(Integer index) {
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        return remove(task);
    }

}
