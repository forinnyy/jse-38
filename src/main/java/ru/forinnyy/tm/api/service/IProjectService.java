package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;


public interface IProjectService extends IUserOwnedService<Project> {

    @NotNull
    Project create(@Nullable String userId, @Nullable String name) throws AbstractFieldException;

    @NotNull
    Project create(@Nullable String userId, @Nullable String name, @Nullable String description) throws AbstractFieldException;

    @NotNull
    Project updateById(@Nullable String userId, @Nullable String id, @Nullable String name, @Nullable String description) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Project updateByIndex(@Nullable String userId, @Nullable Integer index, @Nullable String name, @Nullable String description) throws AbstractFieldException, AbstractEntityException;

    @NotNull
    Project changeProjectStatusById(@Nullable String userId, @Nullable String id, @NotNull Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Project changeProjectStatusByIndex(@Nullable String userId, @Nullable Integer index, @NotNull Status status) throws AbstractFieldException, AbstractEntityException;

}
