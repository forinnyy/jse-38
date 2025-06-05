package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskBindToProjectCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-bind-to-project";

    @NonNull
    private static final String DESCRIPTION = "Bind task to project.";

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
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("ENTER PROJECT ID:");
        @NonNull final String projectId = TerminalUtil.nextLine();
        System.out.println("ENTER TASK ID:");
        @NonNull final String taskId = TerminalUtil.nextLine();
        @NonNull final String userId = getUserId();
        getProjectTaskService().bindTaskToProject(userId, projectId, taskId);
    }

}
