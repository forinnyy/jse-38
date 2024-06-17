package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.List;

public final class TaskListByProjectIdCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-list-by-project-id";

    @NotNull
    private static final String DESCRIPTION = "Shows list of tasks for project id";

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
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("ENTER PROJECT ID:");
        @NotNull final String projectId = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        @NotNull final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        renderTasks(tasks);
    }

}
