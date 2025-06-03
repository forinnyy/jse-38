package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

public interface ITaskEndpoint {

    @NonNull
    TaskBindToProjectResponse bindTaskToProject(@NonNull TaskBindToProjectRequest request);

    @NonNull
    TaskChangeStatusByIdResponse changeTaskStatusById(@NonNull TaskChangeStatusByIdRequest request);

    @NonNull
    TaskChangeStatusByIndexResponse changeTaskStatusByIndex(@NonNull TaskChangeStatusByIndexRequest request);

    @NonNull
    TaskClearResponse clearTask(@NonNull TaskClearRequest request);

    @NonNull
    TaskCreateResponse createTask(@NonNull TaskCreateRequest request);

    @NonNull
    TaskGetByIdResponse getTaskById(@NonNull TaskGetByIdRequest request);

    @NonNull
    TaskGetByIndexResponse getTaskByIndex(@NonNull TaskGetByIndexRequest request);

    @NonNull
    TaskListByProjectIdResponse listTaskByProjectId(@NonNull TaskListByProjectIdRequest request);

    @NonNull
    TaskListResponse listTask(@NonNull TaskListRequest request);

    @NonNull
    TaskRemoveByIdResponse removeTaskById(@NonNull TaskRemoveByIdRequest request);

    @NonNull
    TaskRemoveByIndexResponse removeTaskByIndex(@NonNull TaskRemoveByIndexRequest request);

    @NonNull
    TaskUnbindFromProjectResponse unbindTaskFromProject(@NonNull TaskUnbindFromProjectRequest request);

    @NonNull
    TaskUpdateByIdResponse updateTaskById(@NonNull TaskUpdateByIdRequest request);

    @NonNull
    TaskUpdateByIndexResponse updateTaskByIndex(@NonNull TaskUpdateByIndexRequest request);

    @NonNull
    TaskCompleteByIdResponse completeTaskById(@NonNull TaskCompleteByIdRequest request);

    @NonNull
    TaskCompleteByIndexResponse completeTaskByIndex(@NonNull TaskCompleteByIndexRequest request);

    @NonNull
    TaskStartByIdResponse startTaskById(@NonNull TaskStartByIdRequest request);

    @NonNull
    TaskStartByIndexResponse startTaskByIndex(@NonNull TaskStartByIndexRequest request);

}
