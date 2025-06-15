package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskRemoveByIndexRequest;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskRemoveByIndexCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-remove-by-index";

    @NonNull
    private static final String DESCRIPTION = "Remove task by index.";

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
        System.out.println("[REMOVE TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NonNull final Integer index = TerminalUtil.nextNumber() -1;

        @NonNull final TaskRemoveByIndexRequest request = new TaskRemoveByIndexRequest(getToken());
        request.setIndex(index);
        getTaskEndpointClient().removeTaskByIndex(request);
    }

}
