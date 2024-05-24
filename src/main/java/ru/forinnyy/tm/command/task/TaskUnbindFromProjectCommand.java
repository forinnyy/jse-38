package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskUnbindFromProjectCommand extends AbstractTaskCommand {

    private static final String NAME = "task-unbind-from-project";

    private static final String DESCRIPTION = "Unbind task from project.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[UNBIND TASK FROM PROJECT]");
        System.out.println("ENTER PROJECT ID:");
        final String projectId = TerminalUtil.nextLine();
        System.out.println("ENTER TASK ID:");
        final String taskId = TerminalUtil.nextLine();
        final String userId = getUserId();
        getProjectTaskService().unbindTaskFromProject(userId, projectId, taskId);
    }

}
