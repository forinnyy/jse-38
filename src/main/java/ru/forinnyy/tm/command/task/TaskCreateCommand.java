package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskCreateCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-create";

    @NotNull
    private static final String DESCRIPTION = "Create new task.";

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
        System.out.println("[CREATE TASK]");
        System.out.println("[ENTER NAME]");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("[ENTER DESCRIPTION]");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getTaskService().create(userId, name, description);
    }

}
