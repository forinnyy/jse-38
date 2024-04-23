package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.model.Task;

public interface IProjectTaskService {

    Task bindTaskToProject(String projectId, String taskId);

    void removeProjectById(String projectId);

    Task unbindTaskFromProject(String projectId, String taskId);

}
