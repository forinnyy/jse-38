package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskService extends IUserOwnedService<Task> {

    @NotNull
    Task updateById(String userId, String id, String name, String description) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Task updateByIndex(String userId, Integer index, String name, String description) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Task changeTaskStatusById(String userId, String id, Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Task changeTaskStatusByIndex(String userId, Integer index, Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    List<Task> findAllByProjectId(String userId, String projectId) throws AbstractFieldException;

    @NotNull
    Task create(String userId, String name, String description) throws AbstractFieldException;

    @NotNull
    Task create(String userId, String name) throws AbstractFieldException;

}
