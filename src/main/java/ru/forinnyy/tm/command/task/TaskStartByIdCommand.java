package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskStartByIdCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-start-by-id";

    @NotNull
    private static final String DESCRIPTION = "Start task by id.";

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
        System.out.println("[START TASK BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getTaskService().changeTaskStatusById(userId, id, Status.IN_PROGRESS);
    }

}
