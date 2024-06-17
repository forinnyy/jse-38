package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIdCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-change-status-by-id";

    @NotNull
    private static final String DESCRIPTION = "Change task status by id.";

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
        System.out.println("[CHANGE TASK STATUS BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusValue = TerminalUtil.nextLine();
        @Nullable final Status status = Status.toStatus(statusValue);
        @NotNull final String userId = getUserId();
        getTaskService().changeTaskStatusById(userId, id, status);
    }

}
