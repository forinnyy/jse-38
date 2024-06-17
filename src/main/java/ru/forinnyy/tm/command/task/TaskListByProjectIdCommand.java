package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.List;

public final class TaskListByProjectIdCommand extends AbstractTaskCommand {

    private static final String NAME = "task-list-by-project-id";

    private static final String DESCRIPTION = "Shows list of tasks for project id";

    @Override
    public @NotNull String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("ENTER PROJECT ID:");
        final String projectId = TerminalUtil.nextLine();
        final String userId = getUserId();
        final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        renderTasks(tasks);
    }

}
