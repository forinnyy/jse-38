package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.IProjectEndpoint;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

public class ProjectEndpoint extends AbstractEndpoint implements IProjectEndpoint { //

    public ProjectEndpoint(IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NonNull
    private IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @NonNull
    @Override
    public ProjectChangeStatusByIdResponse changeProjectStatusById(@NonNull ProjectChangeStatusByIdRequest request) {
        return new ProjectChangeStatusByIdResponse();
    }

    @NonNull
    @Override
    public ProjectChangeStatusByIndexResponse changeProjectStatusByIndex(@NonNull ProjectChangeStatusByIndexRequest request) {
        return new ProjectChangeStatusByIndexResponse();
    }

    @NonNull
    @Override
    public ProjectClearResponse clearProject(@NonNull ProjectClearRequest request) {
        return new ProjectClearResponse();
    }

    @NonNull
    @Override
    public ProjectCreateResponse createProject(@NonNull ProjectCreateRequest request) {
        return new ProjectCreateResponse();
    }

    @NonNull
    @Override
    public ProjectGetByIdResponse getProjectById(@NonNull ProjectGetByIdRequest request) {
        return new ProjectGetByIdResponse();
    }

    @NonNull
    @Override
    public ProjectGetByIndexResponse getProjectByIndex(@NonNull ProjectGetByIndexRequest request) {
        return new ProjectGetByIndexResponse();
    }

    @NonNull
    @Override
    public ProjectListResponse listProject(@NonNull ProjectListRequest request) {
        return new ProjectListResponse();
    }

    @NonNull
    @Override
    public ProjectRemoveByIdResponse removeProjectById(@NonNull ProjectRemoveByIDRequest request) {
        return new ProjectRemoveByIdResponse();
    }

    @NonNull
    @Override
    public ProjectRemoveByIndexResponse removeProjectByIndex(@NonNull ProjectRemoveByIndexRequest request) {
        return new ProjectRemoveByIndexResponse();
    }

    @NonNull
    @Override
    public ProjectUpdateByIdResponse updateProjectById(@NonNull ProjectUpdateByIdRequest request) {
        return new ProjectUpdateByIdResponse();
    }

    @NonNull
    @Override
    public ProjectUpdateByIndexResponse updateProjectByIndex(@NonNull ProjectUpdateByIndexRequest request) {
        return new ProjectUpdateByIndexResponse();
    }

}
