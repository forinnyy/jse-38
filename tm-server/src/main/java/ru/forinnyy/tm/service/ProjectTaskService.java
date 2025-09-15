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
    protected final IConnectionService connectionService;

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
        @NonNull final Connection connection = getConnection();
        @NonNull final IProjectRepository projectRepository = getProjectRepository(connection);
        @NonNull final ITaskRepository taskRepository = getTaskRepository(connection);

        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        final Project project = projectRepository.findOneById(userId, projectId);
        final Task task = taskRepository.findOneById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        if (project == null) throw new ProjectNotFoundException();
        if (!project.getUserId().equals(userId)) throw new PermissionException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setProjectId(projectId);
        try {
            taskRepository.update(task);
            connection.commit();
            return task;
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task unbindTaskFromProject(final String userId, final String projectId, final String taskId) {
        @NonNull final Connection connection = getConnection();
        @NonNull final IProjectRepository projectRepository = getProjectRepository(connection);
        @NonNull final ITaskRepository taskRepository = getTaskRepository(connection);

        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectRepository.existsById(userId, projectId)) throw new ProjectNotFoundException();
        final Task task = taskRepository.findOneById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        if (!task.getUserId().equals(userId)) throw new PermissionException();
        task.setProjectId(null);
        try {
            taskRepository.update(task);
            connection.commit();
            return task;
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeProjectById(final String userId, final String projectId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();

        @NonNull final Connection connection = getConnection();
        @NonNull final IProjectRepository projectRepository = getProjectRepository(connection);
        @NonNull final ITaskRepository taskRepository = getTaskRepository(connection);

        if (!projectRepository.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @NonNull final Project project = projectRepository.findOneById(userId, projectId);
        if (!project.getUserId().equals(userId)) throw new PermissionException();
        @NonNull final List<Task> tasks = taskRepository.findAllByProjectId(userId, projectId);

        try {
            for (@NonNull final Task task: tasks) taskRepository.removeById(userId, task.getId());
            projectRepository.removeById(projectId);
            connection.commit();
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
