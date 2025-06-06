package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

public interface IProjectEndpointClient extends IEndpointClient {

    @NonNull
    ProjectChangeStatusByIdResponse changeProjectStatusById(@NonNull ProjectChangeStatusByIdRequest request);

    @NonNull
    ProjectChangeStatusByIndexResponse changeProjectStatusByIndex(@NonNull ProjectChangeStatusByIndexRequest request);

    @NonNull
    ProjectClearResponse clearProject(@NonNull ProjectClearRequest request);

    @NonNull
    ProjectCreateResponse createProject(@NonNull ProjectCreateRequest request);

    @NonNull
    ProjectGetByIdResponse getProjectById(@NonNull ProjectGetByIdRequest request);

    @NonNull
    ProjectGetByIndexResponse getProjectByIndex(@NonNull ProjectGetByIndexRequest request);

    @NonNull
    ProjectListResponse listProject(@NonNull ProjectListRequest request);

    @NonNull
    ProjectRemoveByIdResponse removeProjectById(@NonNull ProjectRemoveByIdRequest request);

    @NonNull
    ProjectRemoveByIndexResponse removeProjectByIndex(@NonNull ProjectRemoveByIndexRequest request);

    @NonNull
    ProjectUpdateByIdResponse updateProjectById(@NonNull ProjectUpdateByIdRequest request);

    @NonNull
    ProjectUpdateByIndexResponse updateProjectByIndex(@NonNull ProjectUpdateByIndexRequest request);

    @NonNull
    ProjectCompleteByIdResponse completeProjectById(@NonNull ProjectCompleteByIdRequest request);

    @NonNull
    ProjectCompleteByIndexResponse completeProjectByIndex(@NonNull ProjectCompleteByIndexRequest request);

    @NonNull
    ProjectStartByIdResponse startProjectById(@NonNull ProjectStartByIdRequest request);

    @NonNull
    ProjectStartByIndexResponse startProjectByIndex(@NonNull ProjectStartByIndexRequest request);

}
