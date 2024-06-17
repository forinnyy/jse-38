package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskShowByIdCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-show-by-id";

    @NotNull
    private static final String DESCRIPTION = "Show task by id.";

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
        System.out.println("[SHOW TASK BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        @Nullable final Task task = getTaskService().findOneById(userId, id);
        if (task == null) throw new TaskNotFoundException();
        showTask(task);
    }

}
