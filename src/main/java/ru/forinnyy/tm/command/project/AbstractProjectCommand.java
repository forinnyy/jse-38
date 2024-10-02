package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;

public abstract class AbstractProjectCommand extends AbstractCommand {

    @NonNull
    protected IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @NonNull
    protected IProjectTaskService getProjectTaskService() {
        return getServiceLocator().getProjectTaskService();
    }

    @Override
    public String getArgument() {
        return null;
    }

    protected void showProject(final Project project) {
        if (project == null) return;
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("STATUS: " + Status.toName(project.getStatus()));
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
