package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public final class TaskClearCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-clear";

    @NotNull
    private static final String DESCRIPTION = "Clear all tasks.";

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
        System.out.println("[CLEAR TASKS]");
        @NotNull final String userId = getUserId();
        getTaskService().clear(userId);
    }

}
