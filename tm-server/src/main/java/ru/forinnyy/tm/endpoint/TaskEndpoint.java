package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.ITaskEndpoint;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public final class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

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
    public TaskBindToProjectResponse bindTaskToProject(@NonNull final TaskBindToProjectRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String taskId = request.getTaskId();
        final String projectId = request.getProjectId();
        getProjectTaskService().bindTaskToProject(userId, projectId, taskId);
        return new TaskBindToProjectResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskChangeStatusByIdResponse changeTaskStatusById(@NonNull final TaskChangeStatusByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        final Status status = request.getStatus();
        getTaskService().changeTaskStatusById(userId, id, status);
        return new TaskChangeStatusByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskChangeStatusByIndexResponse changeTaskStatusByIndex(
            @NonNull final TaskChangeStatusByIndexRequest request
    ) {
        check(request);
        final String userId = request.getUserId();
        final Status status = request.getStatus();
        final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index, status);
        return new TaskChangeStatusByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskClearResponse clearTask(@NonNull final TaskClearRequest request) {
        check(request);
        final String userId = request.getUserId();
        getTaskService().clear(userId);
        return new TaskClearResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskCreateResponse createTask(@NonNull final TaskCreateRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String name = request.getName();
        final String description = request.getDescription();
        getTaskService().create(userId, name, description);
        return new TaskCreateResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskGetByIdResponse getTaskById(@NonNull final TaskGetByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        final Task task = getTaskService().findOneById(userId, id);
        return new TaskGetByIdResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskGetByIndexResponse getTaskByIndex(@NonNull final TaskGetByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        final Task task = getTaskService().findOneByIndex(userId, index);
        return new TaskGetByIndexResponse(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskListByProjectIdResponse listTaskByProjectId(@NonNull final TaskListByProjectIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String projectId = request.getProjectId();
        final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        return new TaskListByProjectIdResponse(tasks);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskListResponse listTask(@NonNull final TaskListRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Sort sort = request.getSort();
        final List<Task> tasks = getTaskService().findAll(userId, sort);
        return new TaskListResponse(tasks);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskRemoveByIdResponse removeTaskById(@NonNull final TaskRemoveByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        getTaskService().removeById(userId, id);
        return new TaskRemoveByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskRemoveByIndexResponse removeTaskByIndex(@NonNull final TaskRemoveByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        getTaskService().removeByIndex(userId, index);
        return new TaskRemoveByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUnbindFromProjectResponse unbindTaskFromProject(@NonNull final TaskUnbindFromProjectRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String taskId = request.getTaskId();
        final String projectId = request.getProjectId();
        getProjectTaskService().unbindTaskFromProject(userId, projectId, taskId);
        return new TaskUnbindFromProjectResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUpdateByIdResponse updateTaskById(@NonNull final TaskUpdateByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        final String name = request.getName();
        final String description = request.getDescription();
        getTaskService().updateById(userId, id, name, description);
        return new TaskUpdateByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUpdateByIndexResponse updateTaskByIndex(@NonNull final TaskUpdateByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        final String name = request.getName();
        final String description = request.getDescription();
        getTaskService().updateByIndex(userId, index, name, description);
        return new TaskUpdateByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskCompleteByIdResponse completeTaskById(@NonNull final TaskCompleteByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        getTaskService().changeTaskStatusById(userId, id, Status.COMPLETED);
        return new TaskCompleteByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskCompleteByIndexResponse completeTaskByIndex(@NonNull final TaskCompleteByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index, Status.COMPLETED);
        return new TaskCompleteByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskStartByIdResponse startTaskById(@NonNull final TaskStartByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        getTaskService().changeTaskStatusById(userId, id, Status.IN_PROGRESS);
        return new TaskStartByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskStartByIndexResponse startTaskByIndex(@NonNull final TaskStartByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index, Status.IN_PROGRESS);
        return new TaskStartByIndexResponse();
    }

}
