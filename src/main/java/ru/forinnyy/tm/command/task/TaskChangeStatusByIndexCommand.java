package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIndexCommand extends AbstractTaskCommand {

    private static final String NAME = "task-change-status-by-index";

    private static final String DESCRIPTION = "Change task status by index.";

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
        System.out.println("[CHANGE TASK STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        getTaskService().changeTaskStatusByIndex(index, status);
    }

}
