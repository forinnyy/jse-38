package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskUpdateByIndexCommand extends AbstractTaskCommand {

    private static final String NAME = "task-update-by-index";

    private static final String DESCRIPTION = "Update task by index.";

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
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        getTaskService().updateByIndex(index, name, description);
    }

}
