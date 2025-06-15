package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.*;

public interface IServiceLocator {

    @NonNull
    IPropertyService getPropertyService();

    @NonNull
    ICommandService getCommandService();

    @NonNull
    ISystemEndpoint getSystemEndpoint();

    @NonNull
    IDomainEndpoint getDomainEndpoint();

    @NonNull
    IProjectEndpoint getProjectEndpoint();

    @NonNull
    ITaskEndpoint getTaskEndpoint();

    @NonNull
    IAuthEndpoint getAuthEndpoint();

    @NonNull
    IUserEndpoint getUserEndpoint();

    @NonNull
    ITokenService getTokenService();

}
