package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.model.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskRepository {

    List<Task> findAll();

    List<Task> findAll(Comparator<Task> comparator);

    List<Task> findAllByProjectId(String projectId);

    Task add(Task task);

    void clear();

    int getSize();

    Task create(String name);

    Task create(String name, String description);

    Task findOneById(String id);

    Task findOneByIndex(Integer index);

    Task remove(Task project);

    Task removeById(String id);

    Task removeByIndex(Integer index);

}
