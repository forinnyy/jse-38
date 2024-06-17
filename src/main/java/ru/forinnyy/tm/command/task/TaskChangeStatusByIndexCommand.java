package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIndexCommand extends AbstractTaskCommand {

    private static final String NAME = "task-change-status-by-index";

    private static final String DESCRIPTION = "Change task status by index.";

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
        System.out.println("[CHANGE TASK STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusValue = TerminalUtil.nextLine();
        @Nullable final Status status = Status.toStatus(statusValue);
        @NotNull final String userId = getUserId();
        getTaskService().changeTaskStatusByIndex(userId, index, status);
    }

}
