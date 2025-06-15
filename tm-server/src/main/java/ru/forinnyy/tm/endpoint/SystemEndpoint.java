package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.endpoint.ISystemEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.dto.request.ApplicationAboutRequest;
import ru.forinnyy.tm.dto.request.ApplicationVersionRequest;
import ru.forinnyy.tm.dto.response.ApplicationAboutResponse;
import ru.forinnyy.tm.dto.response.ApplicationVersionResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.forinnyy.tm.api.endpoint.ISystemEndpoint")
public final class SystemEndpoint implements ISystemEndpoint {

    @NonNull
    private final IServiceLocator serviceLocator;

    public SystemEndpoint(@NonNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @NonNull
    private IPropertyService getPropertyService() {
        return serviceLocator.getPropertyService();
    }

    @NotNull
    @Override
    @WebMethod
    public ApplicationAboutResponse getAbout(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ApplicationAboutRequest request
    ) {
        @NonNull final ApplicationAboutResponse response = new ApplicationAboutResponse();
        response.setEmail(getPropertyService().getAuthorEmail());
        response.setName(getPropertyService().getAuthorName());
        response.setGitBranch(getPropertyService().getGitBranch());
        response.setGitCommitId(getPropertyService().getGitCommitId());
        response.setGitCommitMessage(getPropertyService().getGitCommitMessage());
        response.setGitCommitTime(getPropertyService().getGitCommitTime());
        response.setGitCommitterName(getPropertyService().getGitCommitterName());
        response.setGitCommitterEmail(getPropertyService().getGitCommitterEmail());
        return response;
    }

    @NotNull
    @Override
    @WebMethod
    public ApplicationVersionResponse getVersion(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ApplicationVersionRequest request
    ) {
        @NonNull final ApplicationVersionResponse response = new ApplicationVersionResponse();
        response.setVersion(getPropertyService().getApplicationVersion());
        return response;
    }

}
