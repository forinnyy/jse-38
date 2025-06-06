package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskCreateRequest;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskCreateCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-create";

    @NonNull
    private static final String DESCRIPTION = "Create new task.";

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
        System.out.println("[CREATE TASK]");
        System.out.println("[ENTER NAME]");
        @NonNull final String name = TerminalUtil.nextLine();
        System.out.println("[ENTER DESCRIPTION]");
        @NonNull final String description = TerminalUtil.nextLine();

        @NonNull final TaskCreateRequest request = new TaskCreateRequest();
        request.setName(name);
        request.setDescription(description);
        getTaskEndpointClient().createTask(request);
    }

}
