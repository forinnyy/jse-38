package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Task;

public interface ITaskService extends ITaskRepository {

    Task create(String name, String description);

}
