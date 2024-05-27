package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskService extends IUserOwnedService<Task> {

    Task updateById(String userId, String id, String name, String description) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    Task updateByIndex(String userId, Integer index, String name, String description) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    Task changeTaskStatusById(String userId, String id, Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    Task changeTaskStatusByIndex(String userId, Integer index, Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    List<Task> findAllByProjectId(String userId, String projectId);

    Task create(String userId, String name, String description) throws AbstractFieldException;

    Task create(String userId, String name) throws AbstractFieldException;

}
