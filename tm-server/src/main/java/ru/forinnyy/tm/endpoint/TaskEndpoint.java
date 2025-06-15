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
import ru.forinnyy.tm.model.Session;
import ru.forinnyy.tm.model.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.forinnyy.tm.api.endpoint.ITaskEndpoint")
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
    @WebMethod
    @SneakyThrows
    public TaskBindToProjectResponse bindTaskToProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskBindToProjectRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String taskId = request.getTaskId();
        final String projectId = request.getProjectId();
        getProjectTaskService().bindTaskToProject(userId, projectId, taskId);
        return new TaskBindToProjectResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskChangeStatusByIdResponse changeTaskStatusById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskChangeStatusByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        final Status status = request.getStatus();
        getTaskService().changeTaskStatusById(userId, id, status);
        return new TaskChangeStatusByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskChangeStatusByIndexResponse changeTaskStatusByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskChangeStatusByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Status status = request.getStatus();
        final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index, status);
        return new TaskChangeStatusByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskClearResponse clearTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskClearRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        getTaskService().clear(userId);
        return new TaskClearResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskCreateResponse createTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskCreateRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String name = request.getName();
        final String description = request.getDescription();
        getTaskService().create(userId, name, description);
        return new TaskCreateResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskGetByIdResponse getTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskGetByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        final Task task = getTaskService().findOneById(userId, id);
        return new TaskGetByIdResponse(task);
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskGetByIndexResponse getTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskGetByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        final Task task = getTaskService().findOneByIndex(userId, index);
        return new TaskGetByIndexResponse(task);
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskListByProjectIdResponse listTaskByProjectId(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskListByProjectIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String projectId = request.getProjectId();
        final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        return new TaskListByProjectIdResponse(tasks);
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskListResponse listTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskListRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Sort sort = request.getSort();
        final List<Task> tasks = getTaskService().findAll(userId, sort);
        return new TaskListResponse(tasks);
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskRemoveByIdResponse removeTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskRemoveByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        getTaskService().removeById(userId, id);
        return new TaskRemoveByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskRemoveByIndexResponse removeTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskRemoveByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        getTaskService().removeByIndex(userId, index);
        return new TaskRemoveByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskUnbindFromProjectResponse unbindTaskFromProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskUnbindFromProjectRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String taskId = request.getTaskId();
        final String projectId = request.getProjectId();
        getProjectTaskService().unbindTaskFromProject(userId, projectId, taskId);
        return new TaskUnbindFromProjectResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskUpdateByIdResponse updateTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskUpdateByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        final String name = request.getName();
        final String description = request.getDescription();
        getTaskService().updateById(userId, id, name, description);
        return new TaskUpdateByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskUpdateByIndexResponse updateTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskUpdateByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        final String name = request.getName();
        final String description = request.getDescription();
        getTaskService().updateByIndex(userId, index, name, description);
        return new TaskUpdateByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskCompleteByIdResponse completeTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskCompleteByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        getTaskService().changeTaskStatusById(userId, id, Status.COMPLETED);
        return new TaskCompleteByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskCompleteByIndexResponse completeTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskCompleteByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index, Status.COMPLETED);
        return new TaskCompleteByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskStartByIdResponse startTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskStartByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        getTaskService().changeTaskStatusById(userId, id, Status.IN_PROGRESS);
        return new TaskStartByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public TaskStartByIndexResponse startTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final TaskStartByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index, Status.IN_PROGRESS);
        return new TaskStartByIndexResponse();
    }

}
