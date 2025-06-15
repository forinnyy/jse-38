package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskClearRequest;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public final class TaskClearCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-clear";

    @NonNull
    private static final String DESCRIPTION = "Clear all tasks.";

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
        System.out.println("[CLEAR TASKS]");

        @NonNull final TaskClearRequest request = new TaskClearRequest(getToken());
        getTaskEndpointClient().clearTask(request);
    }

}
