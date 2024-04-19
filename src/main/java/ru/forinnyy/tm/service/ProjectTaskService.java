package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.IProjectRepository;
import ru.forinnyy.tm.api.IProjectTaskService;
import ru.forinnyy.tm.api.ITaskRepository;
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
    public Task bindTaskToProject(String projectId, String taskId) {
        if (projectId == null || projectId.isEmpty()) return null;
        if (taskId == null || taskId.isEmpty()) return null;
        if (!projectRepository.existsById(projectId)) return null;
        final Task task = taskRepository.findOneById(taskId);
        if (task == null) return null;
        task.setProjectId(projectId);
        return task;
    }

    @Override
    public void removeProjectById(String projectId) {
        if (projectId == null || projectId.isEmpty()) return;
        if (!projectRepository.existsById(projectId)) return;
        final List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        for (final Task task: tasks) taskRepository.removeById(task.getId());
        projectRepository.removeById(projectId);
    }

    @Override
    public Task unbindTaskFromProject(String projectId, String taskId) {
        if (projectId == null || projectId.isEmpty()) return null;
        if (taskId == null || taskId.isEmpty()) return null;
        if (!projectRepository.existsById(projectId)) return null;
        final Task task = taskRepository.findOneById(taskId);
        if (task == null) return null;
        task.setProjectId(null);
        return task;
    }

}
