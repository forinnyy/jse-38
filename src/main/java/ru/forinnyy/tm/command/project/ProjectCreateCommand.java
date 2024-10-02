package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectCreateCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-create";

    @NonNull
    private static final String DESCRIPTION = "Create new project.";

    @NonNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractFieldException, AbstractUserException {
        System.out.println("[CREATE PROJECT]");
        System.out.println("ENTER NAME: ");
        @NonNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION: ");
        @NonNull final String description = TerminalUtil.nextLine();
        @NonNull final String userId = getUserId();
        getProjectService().create(userId, name, description);
    }

}
