package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskUnbindFromProjectRequest;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskUnbindFromProjectCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-unbind-from-project";

    @NonNull
    private static final String DESCRIPTION = "Unbind task from project.";

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
        System.out.println("[UNBIND TASK FROM PROJECT]");
        System.out.println("ENTER PROJECT ID:");
        @NonNull final String projectId = TerminalUtil.nextLine();
        System.out.println("ENTER TASK ID:");
        @NonNull final String taskId = TerminalUtil.nextLine();

        @NonNull final TaskUnbindFromProjectRequest request = new TaskUnbindFromProjectRequest();
        request.setProjectId(projectId);
        request.setTaskId(taskId);
        getTaskEndpointClient().unbindTaskFromProject(request);
    }

}
