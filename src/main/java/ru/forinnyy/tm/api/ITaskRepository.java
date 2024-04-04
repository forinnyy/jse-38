package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskRepository {

    Task add(Task task);

    void clear();

    List<Task> findAll();

}
