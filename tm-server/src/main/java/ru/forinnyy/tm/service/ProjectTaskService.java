package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.ProjectIdEmptyException;
import ru.forinnyy.tm.exception.field.TaskIdEmptyException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.TaskRepository;

import java.sql.Connection;
import java.util.List;

public final class ProjectTaskService implements IProjectTaskService {

    @NonNull
    private final IConnectionService connectionService;

    public ProjectTaskService(@NonNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NonNull
    public Connection getConnection() {
        return connectionService.getConnection();
    }

    @NonNull
    public ITaskRepository getTaskRepository(@NonNull final Connection connection) {
        return new TaskRepository(connection);
    }

    @NonNull
    public IProjectRepository getProjectRepository(@NonNull final Connection connection) {
        return new ProjectRepository(connection);
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task bindTaskToProject(final String userId, final String projectId, final String taskId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IProjectRepository projectRepository = getProjectRepository(connection);
            @NonNull final ITaskRepository taskRepository = getTaskRepository(connection);

            final Project project = projectRepository.findOneById(userId, projectId);
            final Task task = taskRepository.findOneById(userId, taskId);

            if (task == null) throw new TaskNotFoundException();
            if (project == null) throw new ProjectNotFoundException();
            if (!project.getUserId().equals(userId)) throw new PermissionException();
            if (!task.getUserId().equals(userId)) throw new PermissionException();

            task.setProjectId(projectId);
            taskRepository.update(task);

            connection.commit();
            return task;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task unbindTaskFromProject(final String userId, final String projectId, final String taskId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IProjectRepository projectRepository = getProjectRepository(connection);
            @NonNull final ITaskRepository taskRepository = getTaskRepository(connection);

            if (!projectRepository.existsById(userId, projectId)) throw new ProjectNotFoundException();
            final Task task = taskRepository.findOneById(userId, taskId);

            if (task == null) throw new TaskNotFoundException();
            if (!task.getUserId().equals(userId)) throw new PermissionException();

            task.setProjectId(null);
            taskRepository.update(task);

            connection.commit();
            return task;
        }
    }

    @Override
    @SneakyThrows
    public void removeProjectById(final String userId, final String projectId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IProjectRepository projectRepository = getProjectRepository(connection);
            @NonNull final ITaskRepository taskRepository = getTaskRepository(connection);

            if (!projectRepository.existsById(userId, projectId)) throw new ProjectNotFoundException();
            @NonNull final Project project = projectRepository.findOneById(userId, projectId);
            if (!project.getUserId().equals(userId)) throw new PermissionException();

            taskRepository.removeAllByProjectId(userId, projectId);
            projectRepository.removeById(userId, projectId);

            connection.commit();
        }
    }

}
