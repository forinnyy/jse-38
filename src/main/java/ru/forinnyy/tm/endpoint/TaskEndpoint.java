package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.ITaskEndpoint;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

    public TaskEndpoint(@NonNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NonNull
    private IProjectTaskService getProjectTaskService() {
        return getServiceLocator().getProjectTaskService();
    }

    @NonNull
    private ITaskService getTaskService() {
        return getServiceLocator().getTaskService();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskBindToProjectResponse bindTaskToProject(@NonNull TaskBindToProjectRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String taskId = request.getTaskId();
        @NonNull final String projectId = request.getProjectId();
        @NonNull final Task task = getProjectTaskService().bindTaskToProject(userId, projectId, taskId);
        return new TaskBindToProjectResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskChangeStatusByIdResponse changeTaskStatusById(@NonNull TaskChangeStatusByIdRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String id = request.getId();
        @NonNull final Status status = request.getStatus();
        @NonNull final Task task = getTaskService().changeTaskStatusById(userId, id, status);
        return new TaskChangeStatusByIdResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskChangeStatusByIndexResponse changeTaskStatusByIndex(@NonNull TaskChangeStatusByIndexRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final Status status = request.getStatus();
        @NonNull final Integer index = request.getIndex();
        @NonNull final Task task = getTaskService().changeTaskStatusByIndex(userId, index, status);
        return new TaskChangeStatusByIndexResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskClearResponse clearTask(@NonNull TaskClearRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        getTaskService().clear(userId);
        return new TaskClearResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskCreateResponse createTask(@NonNull TaskCreateRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String name = request.getName();
        @NonNull final String description = request.getDescription();
        @NonNull final Task task = getTaskService().create(userId, name, description);
        return new TaskCreateResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskGetByIdResponse getTaskById(@NonNull TaskGetByIdRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String id = request.getId();
        @NonNull final Task task = getTaskService().findOneById(userId, id);
        return new TaskGetByIdResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskGetByIndexResponse getTaskByIndex(@NonNull TaskGetByIndexRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final Integer index = request.getIndex();
        @NonNull final Task task = getTaskService().findOneByIndex(userId, index);
        return new TaskGetByIndexResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskListByProjectIdResponse listTaskByProjectId(@NonNull TaskListByProjectIdRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String projectId = request.getProjectId();
        @NonNull final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        return new TaskListByProjectIdResponse(tasks);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskListResponse listTask(@NonNull TaskListRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final List<Task> tasks = getTaskService().findAll(userId);
        return new TaskListResponse(tasks);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskRemoveByIdResponse removeTaskById(@NonNull TaskRemoveByIdRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String id = request.getId();
        @NonNull final Task task = getTaskService().removeById(userId, id);
        return new TaskRemoveByIdResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskRemoveByIndexResponse removeTaskByIndex(@NonNull TaskRemoveByIndexRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final Integer index = request.getIndex();
        @NonNull final Task task = getTaskService().removeByIndex(userId, index);
        return new TaskRemoveByIndexResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUnbindFromProjectResponse unbindTaskFromProject(@NonNull TaskUnbindFromProjectRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String taskId = request.getTaskId();
        @NonNull final String projectId = request.getProjectId();
        @NonNull final Task task = getProjectTaskService().unbindTaskFromProject(userId, projectId, taskId);
        return new TaskUnbindFromProjectResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUpdateByIdResponse updateTaskById(@NonNull TaskUpdateByIdRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String id = request.getId();
        @NonNull final String name = request.getName();
        @NonNull final String description = request.getDescription();
        @NonNull final Task task = getTaskService().updateById(userId, id, name, description);
        return new TaskUpdateByIdResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUpdateByIndexResponse updateTaskByIndex(@NonNull TaskUpdateByIndexRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final Integer index = request.getIndex();
        @NonNull final String name = request.getName();
        @NonNull final String description = request.getDescription();
        @NonNull final Task task = getTaskService().updateByIndex(userId, index, name, description);
        return new TaskUpdateByIndexResponse(task);
    }

}
