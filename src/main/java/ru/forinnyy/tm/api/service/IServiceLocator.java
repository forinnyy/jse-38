package ru.forinnyy.tm.api.service;

public interface IServiceLocator {

    ICommandService getCommandService();

    IProjectService getProjectService();

    IProjectTaskService getProjectTaskService();

    ITaskService getTaskService();

}
