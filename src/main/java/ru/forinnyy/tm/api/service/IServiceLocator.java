package ru.forinnyy.tm.api.service;

import lombok.NonNull;

public interface IServiceLocator {

    @NonNull
    ICommandService getCommandService();

    @NonNull
    IProjectService getProjectService();

    @NonNull
    IProjectTaskService getProjectTaskService();

    @NonNull
    ITaskService getTaskService();

    @NonNull
    IUserService getUserService();

    @NonNull
    IAuthService getAuthService();

    @NonNull
    IPropertyService getPropertyService();

    @NonNull
    IDomainService getDomainService();

}
