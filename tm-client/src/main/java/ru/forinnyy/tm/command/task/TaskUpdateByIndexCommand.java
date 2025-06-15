package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskUpdateByIndexRequest;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskUpdateByIndexCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-update-by-index";

    @NonNull
    private static final String DESCRIPTION = "Update task by index.";

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
        System.out.println("ENTER NAME:");
        @NonNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NonNull final String description = TerminalUtil.nextLine();

        @NonNull final TaskUpdateByIndexRequest request = new TaskUpdateByIndexRequest(getToken());
        request.setIndex(index);
        request.setName(name);
        request.setDescription(description);
        getTaskEndpointClient().updateTaskByIndex(request);
    }

}
