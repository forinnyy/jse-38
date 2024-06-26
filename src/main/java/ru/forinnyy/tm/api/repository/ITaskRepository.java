package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskRepository extends IUserOwnedRepository<Task> {

    @NotNull
    List<Task> findAllByProjectId(String userId, String projectId);

    @NotNull
    Task create(String userId, String name) throws AbstractFieldException;

    @NotNull
    Task create(String userId, String name, String description) throws AbstractFieldException;

}
