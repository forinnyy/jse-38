package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskService extends ITaskRepository {

    Task updateById(String id, String name, String description);

    Task updateByIndex(Integer index, String name, String description);

    Task changeTaskStatusById(String id, Status status);

    Task changeTaskStatusByIndex(Integer index, Status status);

    List<Task> findAll(Sort sort);

}
