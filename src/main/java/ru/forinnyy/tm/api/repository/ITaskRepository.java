package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskRepository extends IUserOwnedRepository<Task> {

    List<Task> findAllByProjectId(String userId, String projectId);

    Task create(String userId, String name) throws AbstractFieldException;

    Task create(String userId, String name, String description) throws AbstractFieldException;

}
