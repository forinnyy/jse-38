package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Task;

public interface ITaskService extends ITaskRepository {

    Task updateById(String id, String name, String description);

    Task updateByIndex(Integer index, String name, String description);

}
