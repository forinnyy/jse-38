package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public final class TaskListCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-list";

    @NotNull
    private static final String DESCRIPTION = "Show task list.";

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
        System.out.println("[TASK LIST]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        @NotNull final String sortType = TerminalUtil.nextLine();
        @Nullable final Sort sort = Sort.toSort(sortType);
        @NotNull final String userId = getUserId();
        @NotNull final List<Task> tasks = getTaskService().findAll(userId, sort);
        renderTasks(tasks);
    }

}
