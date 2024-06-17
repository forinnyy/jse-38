package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public final class ProjectClearCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-clear";

    @NotNull
    private static final String DESCRIPTION = "Delete all projects.";

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractUserException, AbstractFieldException {
        System.out.println("[CLEAR PROJECTS]");
        @NotNull final String userId = getUserId();
        getProjectService().clear(userId);
    }

}
