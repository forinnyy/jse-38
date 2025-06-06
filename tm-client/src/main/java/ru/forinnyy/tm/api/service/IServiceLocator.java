package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.*;

public interface IServiceLocator {

    @NonNull
    IPropertyService getPropertyService();

    @NonNull
    ICommandService getCommandService();

    @NonNull
    IEndpointClient getConnectionEndpointClient();

    @NonNull
    ISystemEndpointClient getSystemEndpointClient();

    @NonNull
    IDomainEndpointClient getDomainEndpointClient();

    @NonNull
    IProjectEndpointClient getProjectEndpointClient();

    @NonNull
    ITaskEndpointClient getTaskEndpointClient();

    @NonNull
    IAuthEndpointClient getAuthEndpointClient();

    @NonNull
    IUserEndpointClient getUserEndpointClient();

}
