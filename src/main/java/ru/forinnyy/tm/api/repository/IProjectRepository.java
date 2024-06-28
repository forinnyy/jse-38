package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;

public interface IProjectRepository extends IUserOwnedRepository<Project> {

    @NotNull
    Project create(@NotNull String userId, @NotNull String name) throws AbstractFieldException;

    @NotNull
    Project create(@NotNull String userId, @NotNull String name, @NotNull String description) throws AbstractFieldException;

}
