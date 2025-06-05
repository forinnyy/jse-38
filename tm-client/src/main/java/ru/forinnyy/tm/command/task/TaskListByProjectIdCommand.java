package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.List;

public final class TaskListByProjectIdCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-list-by-project-id";

    @NonNull
    private static final String DESCRIPTION = "Shows list of tasks for project id";

    @NonNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("ENTER PROJECT ID:");
        @NonNull final String projectId = TerminalUtil.nextLine();
        @NonNull final String userId = getUserId();
        @NonNull final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        renderTasks(tasks);
    }

}
