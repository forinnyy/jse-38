package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskShowByIndexCommand extends AbstractTaskCommand {

    private static final String NAME = "task-show-by-index";

    private static final String DESCRIPTION = "Show task by index.";

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
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Task task = getTaskService().findOneByIndex(index);
        showTask(task);
    }

}
