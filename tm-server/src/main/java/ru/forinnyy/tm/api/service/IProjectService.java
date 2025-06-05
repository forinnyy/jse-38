package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;


public interface IProjectService extends IUserOwnedService<Project> {

    @NonNull
    Project create(String userId, String name) throws AbstractFieldException;

    @NonNull
    Project create(String userId, String name, String description) throws AbstractFieldException;

    @NonNull
    Project updateById(String userId, String id, String name, String description) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NonNull
    Project updateByIndex(String userId, Integer index, String name, String description) throws AbstractFieldException, AbstractEntityException;

    @NonNull
    Project changeProjectStatusById(String userId, String id, @NonNull Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NonNull
    Project changeProjectStatusByIndex(String userId, Integer index, @NonNull Status status) throws AbstractFieldException, AbstractEntityException;

}
