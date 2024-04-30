package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.ProjectIdEmptyException;
import ru.forinnyy.tm.exception.field.TaskIdEmptyException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public class ProjectTaskService implements IProjectTaskService {

    private final IProjectRepository projectRepository;

    private final ITaskRepository taskRepository;

    public ProjectTaskService(IProjectRepository projectRepository, ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task bindTaskToProject(String projectId, String taskId) throws AbstractFieldException, AbstractEntityException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();
        final Task task = taskRepository.findOneById(taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(projectId);
        return task;
    }

    @Override
    public void removeProjectById(String projectId) throws AbstractFieldException, AbstractEntityException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();
        final List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        for (final Task task: tasks) taskRepository.removeById(task.getId());
        projectRepository.removeById(projectId);
    }

    @Override
    public Task unbindTaskFromProject(String projectId, String taskId) throws AbstractFieldException, AbstractEntityException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();
        final Task task = taskRepository.findOneById(taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(null);
        return task;
    }

}
