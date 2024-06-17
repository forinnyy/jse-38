package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskCompleteByIndexCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-complete-by-index";

    @NotNull
    private static final String DESCRIPTION = "Complete task by index.";

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
        System.out.println("[COMPLETE TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final String userId = getUserId();
        getTaskService().changeTaskStatusByIndex(userId, index, Status.COMPLETED);
    }

}
