package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskStartByIdCommand extends AbstractTaskCommand {

    private static final String NAME = "task-start-by-id";

    private static final String DESCRIPTION = "Start task by id.";

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
        System.out.println("[START TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        getTaskService().changeTaskStatusById(id, Status.IN_PROGRESS);
    }

}
