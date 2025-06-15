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
import ru.forinnyy.tm.model.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.forinnyy.tm.api.endpoint.IProjectEndpoint")
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
    @WebMethod
    @SneakyThrows
    public ProjectChangeStatusByIdResponse changeProjectStatusById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectChangeStatusByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        final Status status = request.getStatus();
        getProjectService().changeProjectStatusById(userId, id, status);
        return new ProjectChangeStatusByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectChangeStatusByIndexResponse changeProjectStatusByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectChangeStatusByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        final Status status = request.getStatus();
        getProjectService().changeProjectStatusByIndex(userId, index, status);
        return new ProjectChangeStatusByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectClearResponse clearProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectClearRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final List<Project> projects = getProjectService().findAll(userId);
        for (Project project: projects) {
            getProjectTaskService().removeProjectById(userId, project.getId());
        }
        return new ProjectClearResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectCreateResponse createProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectCreateRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String name = request.getName();
        final String description = request.getDescription();
        getProjectService().create(userId, name, description);
        return new ProjectCreateResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectGetByIdResponse getProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectGetByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        final Project project = getProjectService().findOneById(userId, id);
        return new ProjectGetByIdResponse(project);
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectGetByIndexResponse getProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectGetByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        final Project project = getProjectService().findOneByIndex(userId, index);
        return new ProjectGetByIndexResponse(project);
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectListResponse listProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectListRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Sort sort = request.getSort();
        final List<Project> projects = getProjectService().findAll(userId, sort);
        return new ProjectListResponse(projects);
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectRemoveByIdResponse removeProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectRemoveByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        getProjectTaskService().removeProjectById(userId, id);
        return new ProjectRemoveByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectRemoveByIndexResponse removeProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectRemoveByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        final Project project = getProjectService().findOneByIndex(userId, index);
        getProjectTaskService().removeProjectById(userId, project.getId());
        return new ProjectRemoveByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectUpdateByIdResponse updateProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectUpdateByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        final String name = request.getName();
        final String description = request.getDescription();
        getProjectService().updateById(userId, id, name, description);
        return new ProjectUpdateByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectUpdateByIndexResponse updateProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectUpdateByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        final String name = request.getName();
        final String description = request.getDescription();
        getProjectService().updateByIndex(userId, index, name, description);
        return new ProjectUpdateByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectCompleteByIdResponse completeProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectCompleteByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        getProjectService().changeProjectStatusById(userId, id, Status.COMPLETED);
        return new ProjectCompleteByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectCompleteByIndexResponse completeProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectCompleteByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        getProjectService().changeProjectStatusByIndex(userId, index, Status.COMPLETED);
        return new ProjectCompleteByIndexResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectStartByIdResponse startProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectStartByIdRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String id = request.getId();
        getProjectService().changeProjectStatusById(userId, id, Status.IN_PROGRESS);
        return new ProjectStartByIdResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public ProjectStartByIndexResponse startProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final ProjectStartByIndexRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final Integer index = request.getIndex();
        getProjectService().changeProjectStatusByIndex(userId, index, Status.IN_PROGRESS);
        return new ProjectStartByIndexResponse();
    }

}
