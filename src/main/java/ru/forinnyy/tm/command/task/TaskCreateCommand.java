package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskCreateCommand extends AbstractTaskCommand {

    private static final String NAME = "task-create";

    private static final String DESCRIPTION = "Create new task.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[CREATE TASK]");
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.nextLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.nextLine();
        final String userId = getUserId();
        getTaskService().create(userId, name, description);
    }

}
