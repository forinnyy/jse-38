package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;

public interface IProjectTaskService {

    Task bindTaskToProject(String projectId, String taskId) throws AbstractFieldException, AbstractEntityException;

    void removeProjectById(String projectId) throws AbstractFieldException, AbstractEntityException;

    Task unbindTaskFromProject(String projectId, String taskId) throws AbstractFieldException, AbstractEntityException;

}
