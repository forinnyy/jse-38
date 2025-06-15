package ru.forinnyy.tm.command.task;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.TaskListRequest;
import ru.forinnyy.tm.dto.response.TaskListResponse;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.Collections;

public final class TaskListCommand extends AbstractTaskCommand {

    @NonNull
    private static final String NAME = "task-list";

    @NonNull
    private static final String DESCRIPTION = "Show task list.";

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
        System.out.println("[TASK LIST]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        @NonNull final String sortType = TerminalUtil.nextLine();
        final Sort sort = Sort.toSort(sortType);

        @NonNull final TaskListRequest request = new TaskListRequest(getToken());
        request.setSort(sort);
        @NonNull final TaskListResponse response = getTaskEndpointClient().listTask(request);
        if (response.getTasks() == null) response.setTasks(Collections.emptyList());
        renderTasks(response.getTasks());
    }

}
