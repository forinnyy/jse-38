package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectCreateCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-create";

    @NotNull
    private static final String DESCRIPTION = "Create new project.";

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
    public void execute() throws AbstractFieldException, AbstractUserException {
        System.out.println("[CREATE PROJECT]");
        System.out.println("ENTER NAME: ");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION: ");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getProjectService().create(userId, name, description);
    }

}
