package ru.forinnyy.tm.client;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IProjectEndpointClient;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

@NoArgsConstructor
public final class ProjectEndpointClient extends AbstractEndpointClient implements IProjectEndpointClient {

    @NonNull
    @Override
    @SneakyThrows
    public ProjectChangeStatusByIdResponse changeProjectStatusById(@NonNull ProjectChangeStatusByIdRequest request) {
        return call(request, ProjectChangeStatusByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectChangeStatusByIndexResponse changeProjectStatusByIndex(@NonNull ProjectChangeStatusByIndexRequest request) {
        return call(request, ProjectChangeStatusByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectClearResponse clearProject(@NonNull ProjectClearRequest request) {
        return call(request, ProjectClearResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectCreateResponse createProject(@NonNull ProjectCreateRequest request) {
        return call(request, ProjectCreateResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectGetByIdResponse getProjectById(@NonNull ProjectGetByIdRequest request) {
        return call(request, ProjectGetByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectGetByIndexResponse getProjectByIndex(@NonNull ProjectGetByIndexRequest request) {
        return call(request, ProjectGetByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectListResponse listProject(@NonNull ProjectListRequest request) {
        return call(request, ProjectListResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectRemoveByIdResponse removeProjectById(@NonNull ProjectRemoveByIdRequest request) {
        return call(request, ProjectRemoveByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectRemoveByIndexResponse removeProjectByIndex(@NonNull ProjectRemoveByIndexRequest request) {
        return call(request, ProjectRemoveByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectUpdateByIdResponse updateProjectById(@NonNull ProjectUpdateByIdRequest request) {
        return call(request, ProjectUpdateByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectUpdateByIndexResponse updateProjectByIndex(@NonNull ProjectUpdateByIndexRequest request) {
        return call(request, ProjectUpdateByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectCompleteByIdResponse completeProjectById(@NonNull ProjectCompleteByIdRequest request) {
        return call(request, ProjectCompleteByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectCompleteByIndexResponse completeProjectByIndex(@NonNull ProjectCompleteByIndexRequest request) {
        return call(request, ProjectCompleteByIndexResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectStartByIdResponse startProjectById(@NonNull ProjectStartByIdRequest request) {
        return call(request, ProjectStartByIdResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectStartByIndexResponse startProjectByIndex(@NonNull ProjectStartByIndexRequest request) {
        return call(request, ProjectStartByIndexResponse.class);
    }

}
