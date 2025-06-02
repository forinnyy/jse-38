package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.ITaskEndpoint;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

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
    public TaskBindToProjectResponse bindTaskToProject(@NonNull TaskBindToProjectRequest request) {
        check(request);

        return new TaskBindToProjectResponse();
    }

    @NonNull
    @Override
    public TaskChangeStatusByIdResponse changeTaskStatusById(@NonNull TaskChangeStatusByIdRequest request) {
        check(request);

        return new TaskChangeStatusByIdResponse();
    }

    @NonNull
    @Override
    public TaskChangeStatusByIndexResponse changeTaskStatusByIndex(@NonNull TaskChangeStatusByIndexRequest request) {
        check(request);

        return new TaskChangeStatusByIndexResponse();
    }

    @NonNull
    @Override
    public TaskClearResponse clearTask(@NonNull TaskClearRequest request) {
        check(request);

        return new TaskClearResponse();
    }

    @NonNull
    @Override
    public TaskCreateResponse createTask(@NonNull TaskCreateRequest request) {
        check(request);

        return new TaskCreateResponse();
    }

    @NonNull
    @Override
    public TaskGetByIdResponse getTaskById(@NonNull TaskGetByIdRequest request) {
        check(request);

        return new TaskGetByIdResponse();
    }

    @NonNull
    @Override
    public TaskGetByIndexResponse getTaskByIndex(@NonNull TaskGetByIndexRequest request) {
        check(request);

        return new TaskGetByIndexResponse();
    }

    @NonNull
    @Override
    public TaskListByProjectIdResponse listTaskByProjectId(@NonNull TaskListByProjectIdRequest request) {
        check(request);

        return new TaskListByProjectIdResponse();
    }

    @NonNull
    @Override
    public TaskListResponse listTask(@NonNull TaskListRequest request) {
        check(request);

        return new TaskListResponse();
    }

    @NonNull
    @Override
    public TaskRemoveByIdResponse removeTaskById(@NonNull TaskRemoveByIdRequest request) {
        check(request);

        return new TaskRemoveByIdResponse();
    }

    @NonNull
    @Override
    public TaskRemoveByIndexResponse removeTaskByIndex(@NonNull TaskRemoveByIndexRequest request) {
        check(request);

        return new TaskRemoveByIndexResponse();
    }

    @NonNull
    @Override
    public TaskUnbindFromProjectResponse unbindTaskFromProject(@NonNull TaskUnbindFromProjectRequest request) {
        check(request);

        return new TaskUnbindFromProjectResponse();
    }

    @NonNull
    @Override
    public TaskUpdateByIdResponse updateTaskById(@NonNull TaskUpdateByIdRequest request) {
        check(request);

        return new TaskUpdateByIdResponse();
    }

    @NonNull
    @Override
    public TaskUpdateByIndexResponse updateTaskByIndex(@NonNull TaskUpdateByIndexRequest request) {
        check(request);

        return new TaskUpdateByIndexResponse();
    }

}
