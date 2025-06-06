package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;

public abstract class AbstractProjectCommand extends AbstractCommand {

    @Override
    public String getArgument() {
        return null;
    }

    protected void renderProject(final Project project) {
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
