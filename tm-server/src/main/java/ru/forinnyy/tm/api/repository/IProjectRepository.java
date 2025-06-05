package ru.forinnyy.tm.api.repository;

import lombok.NonNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;

public interface IProjectRepository extends IUserOwnedRepository<Project> {

    @NonNull
    Project create(@NonNull String userId, @NonNull String name) throws AbstractFieldException;

    @NonNull
    Project create(@NonNull String userId, @NonNull String name, @NonNull String description) throws AbstractFieldException;

}
