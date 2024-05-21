package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    List<Task> findAllByProjectId(String projectId);

    Task create(String name) throws AbstractFieldException;

    Task create(String name, String description) throws AbstractFieldException;

}
