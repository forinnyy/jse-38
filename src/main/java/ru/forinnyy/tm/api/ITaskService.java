package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Task;

public interface ITaskService extends ITaskRepository {

    Task create(final String name, final String description);

}
