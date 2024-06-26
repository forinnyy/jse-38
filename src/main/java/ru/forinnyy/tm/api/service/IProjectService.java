package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;


public interface IProjectService extends IUserOwnedService<Project> {

    @NotNull
    Project create(String userId, String name) throws AbstractFieldException;

    @NotNull
    Project create(String userId, String name, String description) throws AbstractFieldException;

    @NotNull
    Project updateById(String userId, String id, String name, String description) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Project updateByIndex(String userId, Integer index, String name, String description) throws AbstractFieldException, AbstractEntityException;

    @NotNull
    Project changeProjectStatusById(String userId, String id, Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Project changeProjectStatusByIndex(String userId, Integer index, Status status) throws AbstractFieldException, AbstractEntityException;

}
