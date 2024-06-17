package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectStartByIndexCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-start-by-index";

    @NotNull
    private static final String DESCRIPTION = "Start project by index.";

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
        System.out.println("[START PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final String userId = getUserId();
        getProjectService().changeProjectStatusByIndex(userId, index, Status.IN_PROGRESS);
    }

}
