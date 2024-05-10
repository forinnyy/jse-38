package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIdCommand extends AbstractTaskCommand {

    private static final String NAME = "task-change-status-by-id";

    private static final String DESCRIPTION = "Change task status by id.";

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
        System.out.println("[CHANGE TASK STATUS BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        getTaskService().changeTaskStatusById(id, status);
    }

}
