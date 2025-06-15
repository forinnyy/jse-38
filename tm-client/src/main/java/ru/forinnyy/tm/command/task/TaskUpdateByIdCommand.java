package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskUpdateByIdRequest;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskUpdateByIdCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-update-by-id";

    @NonNull
    private static final String DESCRIPTION = "Update task by id.";

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
        System.out.println("ENTER NAME:");
        @NonNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NonNull final String description = TerminalUtil.nextLine();

        @NonNull final TaskUpdateByIdRequest request = new TaskUpdateByIdRequest(getToken());
        request.setId(id);
        request.setName(name);
        request.setDescription(description);
        getTaskEndpointClient().updateTaskById(request);
    }

}
