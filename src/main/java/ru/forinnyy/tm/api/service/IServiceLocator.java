package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;

public interface IServiceLocator {

    @NotNull
    ICommandService getCommandService();

    @NotNull
    IProjectService getProjectService();

    @NotNull
    IProjectTaskService getProjectTaskService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    IAuthService getAuthService();

}
