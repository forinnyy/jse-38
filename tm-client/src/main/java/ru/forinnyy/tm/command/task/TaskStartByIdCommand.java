package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskChangeStatusByIdRequest;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskStartByIdCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-start-by-id";

    @NonNull
    private static final String DESCRIPTION = "Start task by id.";

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
        System.out.println("[START TASK BY ID]");
        System.out.println("ENTER ID:");
        @NonNull final String id = TerminalUtil.nextLine();

        @NonNull final TaskChangeStatusByIdRequest request = new TaskChangeStatusByIdRequest();
        request.setId(id);
        request.setStatus(Status.IN_PROGRESS);
        getTaskEndpointClient().changeTaskStatusById(request);
    }

}
