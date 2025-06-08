package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IProjectEndpoint;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;

import java.util.List;

public final class ProjectEndpoint extends AbstractEndpoint implements IProjectEndpoint { //

    public ProjectEndpoint(IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NonNull
    private IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @NonNull
    private IProjectTaskService getProjectTaskService() {
        return getServiceLocator().getProjectTaskService();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectChangeStatusByIdResponse changeProjectStatusById(
            @NonNull final ProjectChangeStatusByIdRequest request
    ) {
        check(request);
        final String id = request.getId();
        final String userId = request.getUserId();
        final Status status = request.getStatus();
        getProjectService().changeProjectStatusById(userId, id, status);
        return new ProjectChangeStatusByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectChangeStatusByIndexResponse changeProjectStatusByIndex(
            @NonNull final ProjectChangeStatusByIndexRequest request
    ) {
        check(request);
        final Integer index = request.getIndex();
        final String userId = request.getUserId();
        final Status status = request.getStatus();
        getProjectService().changeProjectStatusByIndex(userId, index, status);
        return new ProjectChangeStatusByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectClearResponse clearProject(@NonNull final ProjectClearRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        final List<Project> projects = getProjectService().findAll(userId);
        for (Project project: projects) {
            getProjectTaskService().removeProjectById(userId, project.getId());
        }
        return new ProjectClearResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectCreateResponse createProject(@NonNull final ProjectCreateRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String name = request.getName();
        final String description = request.getDescription();
        getProjectService().create(userId, name, description);
        return new ProjectCreateResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectGetByIdResponse getProjectById(@NonNull final ProjectGetByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        final Project project = getProjectService().findOneById(userId, id);
        return new ProjectGetByIdResponse(project);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectGetByIndexResponse getProjectByIndex(@NonNull final ProjectGetByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        final Project project = getProjectService().findOneByIndex(userId, index);
        return new ProjectGetByIndexResponse(project);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectListResponse listProject(@NonNull final ProjectListRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Sort sort = request.getSort();
        final List<Project> projects = getProjectService().findAll(userId, sort);
        return new ProjectListResponse(projects);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectRemoveByIdResponse removeProjectById(@NonNull final ProjectRemoveByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        getProjectTaskService().removeProjectById(userId, id);
        return new ProjectRemoveByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectRemoveByIndexResponse removeProjectByIndex(@NonNull final ProjectRemoveByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        final Project project = getProjectService().findOneByIndex(userId, index);
        getProjectTaskService().removeProjectById(userId, project.getId());
        return new ProjectRemoveByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectUpdateByIdResponse updateProjectById(@NonNull final ProjectUpdateByIdRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String id = request.getId();
        final String name = request.getName();
        final String description = request.getDescription();
        getProjectService().updateById(userId, id, name, description);
        return new ProjectUpdateByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectUpdateByIndexResponse updateProjectByIndex(@NonNull final ProjectUpdateByIndexRequest request) {
        check(request);
        final String userId = request.getUserId();
        final Integer index = request.getIndex();
        final String name = request.getName();
        final String description = request.getDescription();
        getProjectService().updateByIndex(userId, index, name, description);
        return new ProjectUpdateByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectCompleteByIdResponse completeProjectById(@NonNull final ProjectCompleteByIdRequest request) {
        check(request);
        final String id = request.getId();
        final String userId = request.getUserId();
        getProjectService().changeProjectStatusById(userId, id, Status.COMPLETED);
        return new ProjectCompleteByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectCompleteByIndexResponse completeProjectByIndex(
            @NonNull final ProjectCompleteByIndexRequest request
    ) {
        check(request);
        final Integer index = request.getIndex();
        final String userId = request.getUserId();
        getProjectService().changeProjectStatusByIndex(userId, index, Status.COMPLETED);
        return new ProjectCompleteByIndexResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectStartByIdResponse startProjectById(@NonNull final ProjectStartByIdRequest request) {
        check(request);
        final String id = request.getId();
        final String userId = request.getUserId();
        getProjectService().changeProjectStatusById(userId, id, Status.IN_PROGRESS);
        return new ProjectStartByIdResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public ProjectStartByIndexResponse startProjectByIndex(@NonNull final ProjectStartByIndexRequest request) {
        check(request);
        final Integer index = request.getIndex();
        final String userId = request.getUserId();
        getProjectService().changeProjectStatusByIndex(userId, index, Status.IN_PROGRESS);
        return new ProjectStartByIndexResponse();
    }

}
