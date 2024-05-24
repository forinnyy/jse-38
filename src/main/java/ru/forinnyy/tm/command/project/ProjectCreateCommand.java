package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectCreateCommand extends AbstractProjectCommand {

    private static final String NAME = "project-create";

    private static final String DESCRIPTION = "Create new project.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractFieldException, AbstractUserException {
        System.out.println("[CREATE PROJECT]");
        System.out.println("ENTER NAME: ");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION: ");
        final String description = TerminalUtil.nextLine();
        final String userId = getUserId();
        getProjectService().create(userId, name, description);
    }

}
