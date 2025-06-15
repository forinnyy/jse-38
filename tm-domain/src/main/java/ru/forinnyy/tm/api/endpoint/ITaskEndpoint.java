package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface ITaskEndpoint extends IEndpoint {

    @NonNull
    String NAME = "TaskEndpoint";

    @NonNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static ITaskEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static ITaskEndpoint newInstance(@NonNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, ITaskEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static ITaskEndpoint newInstance(@NonNull final String host, @NonNull final String port) {
        return IEndpoint.newInstanse(host, port, NAME, SPACE, PART, ITaskEndpoint.class);
    }

    @NonNull
    @WebMethod
    TaskBindToProjectResponse bindTaskToProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskBindToProjectRequest request
    );

    @NonNull
    @WebMethod
    TaskChangeStatusByIdResponse changeTaskStatusById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskChangeStatusByIdRequest request
    );

    @NonNull
    @WebMethod
    TaskChangeStatusByIndexResponse changeTaskStatusByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskChangeStatusByIndexRequest request
    );

    @NonNull
    @WebMethod
    TaskClearResponse clearTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskClearRequest request
    );

    @NonNull
    @WebMethod
    TaskCreateResponse createTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskCreateRequest request
    );

    @NonNull
    @WebMethod
    TaskGetByIdResponse getTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskGetByIdRequest request
    );

    @NonNull
    @WebMethod
    TaskGetByIndexResponse getTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskGetByIndexRequest request
    );

    @NonNull
    @WebMethod
    TaskListByProjectIdResponse listTaskByProjectId(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskListByProjectIdRequest request
    );

    @NonNull
    @WebMethod
    TaskListResponse listTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskListRequest request
    );

    @NonNull
    @WebMethod
    TaskRemoveByIdResponse removeTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskRemoveByIdRequest request
    );

    @NonNull
    @WebMethod
    TaskRemoveByIndexResponse removeTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskRemoveByIndexRequest request
    );

    @NonNull
    @WebMethod
    TaskUnbindFromProjectResponse unbindTaskFromProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskUnbindFromProjectRequest request
    );

    @NonNull
    @WebMethod
    TaskUpdateByIdResponse updateTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskUpdateByIdRequest request
    );

    @NonNull
    @WebMethod
    TaskUpdateByIndexResponse updateTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskUpdateByIndexRequest request
    );

    @NonNull
    @WebMethod
    TaskCompleteByIdResponse completeTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskCompleteByIdRequest request
    );

    @NonNull
    @WebMethod
    TaskCompleteByIndexResponse completeTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskCompleteByIndexRequest request
    );

    @NonNull
    @WebMethod
    TaskStartByIdResponse startTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskStartByIdRequest request
    );

    @NonNull
    @WebMethod
    TaskStartByIndexResponse startTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull TaskStartByIndexRequest request
    );

}
