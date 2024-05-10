package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskStartByIndexCommand extends AbstractTaskCommand {

    private static final String NAME = "task-start-by-index";

    private static final String DESCRIPTION = "Start task by index.";

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
        System.out.println("[START TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        getTaskService().changeTaskStatusByIndex(index, Status.IN_PROGRESS);
    }

}
