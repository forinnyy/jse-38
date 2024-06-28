package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;

public interface IProjectTaskService {

    @NotNull
    Task bindTaskToProject(@Nullable String userId, @Nullable String projectId, @Nullable String taskId) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    void removeProjectById(@Nullable String userId, @Nullable String projectId) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @NotNull
    Task unbindTaskFromProject(@Nullable String userId, @Nullable String projectId, @Nullable String taskId) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

}
