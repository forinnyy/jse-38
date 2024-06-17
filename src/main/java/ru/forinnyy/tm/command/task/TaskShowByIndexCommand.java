package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskShowByIndexCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-show-by-index";

    @NotNull
    private static final String DESCRIPTION = "Show task by index.";

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
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() -1;
        @NotNull final String userId = getUserId();
        @Nullable final Task task = getTaskService().findOneByIndex(userId, index);
        if (task == null) throw new TaskNotFoundException();
        showTask(task);
    }

}
