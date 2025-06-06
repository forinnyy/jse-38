package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskGetByIndexRequest;
import ru.forinnyy.tm.dto.response.TaskGetByIndexResponse;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskShowByIndexCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-show-by-index";

    @NonNull
    private static final String DESCRIPTION = "Show task by index.";

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
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NonNull final Integer index = TerminalUtil.nextNumber() -1;

        @NonNull final TaskGetByIndexRequest request = new TaskGetByIndexRequest();
        request.setIndex(index);
        @NonNull final TaskGetByIndexResponse response = getTaskEndpointClient().getTaskByIndex(request);
        if (response.getTask() == null) throw new TaskNotFoundException();
        showTask(response.getTask());
    }

}
