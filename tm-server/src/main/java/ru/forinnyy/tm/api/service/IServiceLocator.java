package ru.forinnyy.tm.api.service;

import lombok.NonNull;

public interface IServiceLocator {

    @NonNull
    IAuthService getAuthService();

    @NonNull
    IProjectService getProjectService();

    @NonNull
    IProjectTaskService getProjectTaskService();

    @NonNull
    ITaskService getTaskService();

    @NonNull
    IUserService getUserService();

    @NonNull
    IPropertyService getPropertyService();

    @NonNull
    IDomainService getDomainService();

}
