package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IProjectEndpoint extends IEndpoint {

    @NonNull
    String NAME = "ProjectEndpoint";

    @NonNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static IProjectEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IProjectEndpoint newInstance(@NonNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, IProjectEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IProjectEndpoint newInstance(@NonNull final String host, @NonNull final String port) {
        return IEndpoint.newInstanse(host, port, NAME, SPACE, PART, IProjectEndpoint.class);
    }

    @NonNull
    @WebMethod
    ProjectChangeStatusByIdResponse changeProjectStatusById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectChangeStatusByIdRequest request
    );

    @NonNull
    @WebMethod
    ProjectChangeStatusByIndexResponse changeProjectStatusByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectChangeStatusByIndexRequest request
    );

    @NonNull
    @WebMethod
    ProjectClearResponse clearProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectClearRequest request
    );

    @NonNull
    @WebMethod
    ProjectCreateResponse createProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectCreateRequest request
    );

    @NonNull
    @WebMethod
    ProjectGetByIdResponse getProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectGetByIdRequest request
    );

    @NonNull
    @WebMethod
    ProjectGetByIndexResponse getProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectGetByIndexRequest request
    );

    @NonNull
    @WebMethod
    ProjectListResponse listProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectListRequest request
    );

    @NonNull
    @WebMethod
    ProjectRemoveByIdResponse removeProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectRemoveByIdRequest request
    );

    @NonNull
    @WebMethod
    ProjectRemoveByIndexResponse removeProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectRemoveByIndexRequest request
    );

    @NonNull
    @WebMethod
    ProjectUpdateByIdResponse updateProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectUpdateByIdRequest request
    );

    @NonNull
    @WebMethod
    ProjectUpdateByIndexResponse updateProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectUpdateByIndexRequest request
    );

    @NonNull
    @WebMethod
    ProjectCompleteByIdResponse completeProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectCompleteByIdRequest request
    );

    @NonNull
    @WebMethod
    ProjectCompleteByIndexResponse completeProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectCompleteByIndexRequest request
    );

    @NonNull
    @WebMethod
    ProjectStartByIdResponse startProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectStartByIdRequest request
    );

    @NonNull
    @WebMethod
    ProjectStartByIndexResponse startProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ProjectStartByIndexRequest request
    );

}
