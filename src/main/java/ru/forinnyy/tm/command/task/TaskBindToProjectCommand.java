package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskBindToProjectCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-bind-to-project";

    @NotNull
    private static final String DESCRIPTION = "Bind task to project.";

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
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("ENTER PROJECT ID:");
        @NotNull final String projectId = TerminalUtil.nextLine();
        System.out.println("ENTER TASK ID:");
        @NotNull final String taskId = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getProjectTaskService().bindTaskToProject(userId, projectId, taskId);
    }

}
