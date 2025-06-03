package ru.forinnyy.tm.client;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.ITaskEndpoint;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

@NoArgsConstructor
public final class TaskEndpointClient extends AbstractEndpointClient implements ITaskEndpoint {

    public TaskEndpointClient(@NonNull AbstractEndpointClient client) {
        super(client);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskBindToProjectResponse bindTaskToProject(@NonNull TaskBindToProjectRequest request) {
        return call(request, TaskBindToProjectResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskChangeStatusByIdResponse changeTaskStatusById(@NonNull TaskChangeStatusByIdRequest request) {
        return call(request, TaskChangeStatusByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskChangeStatusByIndexResponse changeTaskStatusByIndex(@NonNull TaskChangeStatusByIndexRequest request) {
        return call(request, TaskChangeStatusByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskClearResponse clearTask(@NonNull TaskClearRequest request) {
        return call(request, TaskClearResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskCreateResponse createTask(@NonNull TaskCreateRequest request) {
        return call(request, TaskCreateResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskGetByIdResponse getTaskById(@NonNull TaskGetByIdRequest request) {
        return call(request, TaskGetByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskGetByIndexResponse getTaskByIndex(@NonNull TaskGetByIndexRequest request) {
        return call(request, TaskGetByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskListByProjectIdResponse listTaskByProjectId(@NonNull TaskListByProjectIdRequest request) {
        return call(request, TaskListByProjectIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskListResponse listTask(@NonNull TaskListRequest request) {
        return call(request, TaskListResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskRemoveByIdResponse removeTaskById(@NonNull TaskRemoveByIdRequest request) {
        return call(request, TaskRemoveByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskRemoveByIndexResponse removeTaskByIndex(@NonNull TaskRemoveByIndexRequest request) {
        return call(request, TaskRemoveByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUnbindFromProjectResponse unbindTaskFromProject(@NonNull TaskUnbindFromProjectRequest request) {
        return call(request, TaskUnbindFromProjectResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUpdateByIdResponse updateTaskById(@NonNull TaskUpdateByIdRequest request) {
        return call(request, TaskUpdateByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskUpdateByIndexResponse updateTaskByIndex(@NonNull TaskUpdateByIndexRequest request) {
        return call(request, TaskUpdateByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskCompleteByIdResponse completeTaskById(@NonNull TaskCompleteByIdRequest request) {
        return call(request, TaskCompleteByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskCompleteByIndexResponse completeTaskByIndex(@NonNull TaskCompleteByIndexRequest request) {
        return call(request, TaskCompleteByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskStartByIdResponse startTaskById(@NonNull TaskStartByIdRequest request) {
        return call(request, TaskStartByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public TaskStartByIndexResponse startTaskByIndex(@NonNull TaskStartByIndexRequest request) {
        return call(request, TaskStartByIndexResponse.class);
    }

    @SneakyThrows
    public static void main(String[] args) {
        @NonNull final AuthEndpointClient authEndpointClient = new AuthEndpointClient();
        authEndpointClient.connect();
        System.out.println(authEndpointClient.login(new UserLoginRequest("test", "test")));
        System.out.println(authEndpointClient.profile(new UserProfileRequest()).getUser().getRole());

        @NonNull final TaskEndpointClient taskEndpointClient = new TaskEndpointClient(authEndpointClient);
        System.out.println(taskEndpointClient.createTask(new TaskCreateRequest("HELLO", "HOLA")));
        System.out.println(taskEndpointClient.listTask(new TaskListRequest()).getTasks());

        System.out.println(authEndpointClient.logout(new UserLogoutRequest()));
        authEndpointClient.disconnect();
    }

}
