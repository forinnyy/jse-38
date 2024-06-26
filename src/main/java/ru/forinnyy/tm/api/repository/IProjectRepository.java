package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;

public interface IProjectRepository extends IUserOwnedRepository<Project> {

    @NotNull
    Project create(String userId, String name) throws AbstractFieldException;

    @NotNull
    Project create(String userId, String name, String description) throws AbstractFieldException;

}
