package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskListByProjectIdRequest;
import ru.forinnyy.tm.dto.response.TaskListByProjectIdResponse;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Collections;

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

        @NonNull final TaskListByProjectIdRequest request = new TaskListByProjectIdRequest();
        request.setProjectId(projectId);
        @NonNull final TaskListByProjectIdResponse response = getTaskEndpointClient().listTaskByProjectId(request);
        if (response.getTasks() == null) response.setTasks(Collections.emptyList());
        renderTasks(response.getTasks());
    }

}
