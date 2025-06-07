package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectGetByIndexRequest;
import ru.forinnyy.tm.dto.request.ProjectRemoveByIndexRequest;
import ru.forinnyy.tm.dto.request.TaskListByProjectIdRequest;
import ru.forinnyy.tm.dto.request.TaskRemoveByIdRequest;
import ru.forinnyy.tm.dto.response.ProjectGetByIndexResponse;
import ru.forinnyy.tm.dto.response.TaskListByProjectIdResponse;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.List;

public final class ProjectRemoveByIndexCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-remove-by-index";

    @NonNull
    private static final String DESCRIPTION = "Remove project by index.";

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
        System.out.println("[REMOVE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NonNull final Integer index = TerminalUtil.nextNumber() -1;

        @NonNull final ProjectRemoveByIndexRequest request = new ProjectRemoveByIndexRequest();
        request.setIndex(index);
        getProjectEndpointClient().removeProjectByIndex(request);
    }

}
