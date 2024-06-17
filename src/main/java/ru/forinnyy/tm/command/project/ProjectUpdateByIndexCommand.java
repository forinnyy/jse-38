package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectUpdateByIndexCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-update-by-index";

    @NotNull
    private static final String DESCRIPTION = "Update project by index.";

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
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() -1;
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getProjectService().updateByIndex(userId, index, name, description);
    }

}
