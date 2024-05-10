package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskBindToProjectCommand extends AbstractTaskCommand {

    private static final String NAME = "task-bind-to-project";

    private static final String DESCRIPTION = "Bind task to project.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("ENTER PROJECT ID:");
        final String projectId = TerminalUtil.nextLine();
        System.out.println("ENTER TASK ID:");
        final String taskId = TerminalUtil.nextLine();
        getProjectTaskService().bindTaskToProject(projectId, taskId);
    }

}
