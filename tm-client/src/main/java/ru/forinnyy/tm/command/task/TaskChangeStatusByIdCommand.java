package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskChangeStatusByIdRequest;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIdCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-change-status-by-id";

    @NonNull
    private static final String DESCRIPTION = "Change task status by id.";

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
        System.out.println("[CHANGE TASK STATUS BY ID]");
        System.out.println("ENTER ID:");
        @NonNull final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS");
        System.out.println(Arrays.toString(Status.values()));
        @NonNull final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);

        @NonNull final TaskChangeStatusByIdRequest request = new TaskChangeStatusByIdRequest(getToken());
        request.setId(id);
        request.setStatus(status);
        getTaskEndpointClient().changeTaskStatusById(request);
    }

}
