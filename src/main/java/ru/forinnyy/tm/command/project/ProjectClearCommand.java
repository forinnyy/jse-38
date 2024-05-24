package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public final class ProjectClearCommand extends AbstractProjectCommand {

    private static final String NAME = "project-clear";

    private static final String DESCRIPTION = "Delete all projects.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractUserException, AbstractFieldException {
        System.out.println("[CLEAR PROJECTS]");
        final String userId = getUserId();
        getProjectService().clear(userId);
    }

}
