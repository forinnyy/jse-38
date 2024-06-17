package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskRemoveByIdCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-remove-by-id";

    @NotNull
    private static final String DESCRIPTION = "Remove task by id.";

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
        System.out.println("[REMOVE TASK BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getTaskService().removeById(userId, id);
    }

}
