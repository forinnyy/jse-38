package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskShowByIdCommand extends AbstractTaskCommand {

    private static final String NAME = "task-show-by-id";

    private static final String DESCRIPTION = "Show task by id.";

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
        final Task task = getTaskService().findOneById(id);
        showTask(task);
    }

}
