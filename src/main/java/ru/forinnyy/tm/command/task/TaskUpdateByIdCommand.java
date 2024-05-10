package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskUpdateByIdCommand extends AbstractTaskCommand {

    private static final String NAME = "task-update-by-id";

    private static final String DESCRIPTION = "Update task by id.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[SHOW TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        getTaskService().updateById(id, name, description);
    }

}
