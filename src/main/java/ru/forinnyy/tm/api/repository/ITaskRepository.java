package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskRepository extends IUserOwnedRepository<Task> {

    @NotNull
    List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    @NotNull
    Task create(@NotNull String userId, @NotNull String name) throws AbstractFieldException;

    @NotNull
    Task create(@NotNull String userId, @NotNull String name, @NotNull String description) throws AbstractFieldException;

}
