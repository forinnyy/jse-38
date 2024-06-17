package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;

public final class ProjectChangeStatusByIdCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-change-status-by-id";

    @NotNull
    private static final String DESCRIPTION = "Change project status by id.";

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
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusValue = TerminalUtil.nextLine();
        @Nullable final Status status = Status.toStatus(statusValue);
        @NotNull final String userId = getUserId();
        getProjectService().changeProjectStatusById(userId, id, status);
    }

}
