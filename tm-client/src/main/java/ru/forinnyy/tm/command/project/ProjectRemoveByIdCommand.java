package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectRemoveByIdRequest;
import ru.forinnyy.tm.dto.request.TaskListByProjectIdRequest;
import ru.forinnyy.tm.dto.request.TaskRemoveByIdRequest;
import ru.forinnyy.tm.dto.response.TaskListByProjectIdResponse;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.List;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-remove-by-id";

    @NonNull
    private static final String DESCRIPTION = "Remove project by id.";

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
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NonNull final String id = TerminalUtil.nextLine();

        @NonNull final ProjectRemoveByIdRequest request = new ProjectRemoveByIdRequest();
        request.setId(id);
        getProjectEndpointClient().removeProjectById(request);
    }

}
