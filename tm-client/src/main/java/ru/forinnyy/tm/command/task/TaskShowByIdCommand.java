package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskGetByIdRequest;
import ru.forinnyy.tm.dto.response.TaskGetByIdResponse;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskShowByIdCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-show-by-id";

    @NonNull
    private static final String DESCRIPTION = "Show task by id.";

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
        System.out.println("[SHOW TASK BY ID]");
        System.out.println("ENTER ID:");
        @NonNull final String id = TerminalUtil.nextLine();
        
        @NonNull final TaskGetByIdRequest request = new TaskGetByIdRequest();
        request.setId(id);
        @NonNull final TaskGetByIdResponse response = getTaskEndpointClient().getTaskById(request);
        if (response.getTask() == null) throw new TaskNotFoundException();
        showTask(response.getTask());
    }

}
