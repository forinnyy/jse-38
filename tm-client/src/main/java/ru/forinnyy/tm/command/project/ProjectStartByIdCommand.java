package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectChangeStatusByIdRequest;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectStartByIdCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-start-by-id";

    @NonNull
    private static final String DESCRIPTION = "Start project by id.";

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
        System.out.println("[START PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NonNull final String id = TerminalUtil.nextLine();

        @NonNull final ProjectChangeStatusByIdRequest request = new ProjectChangeStatusByIdRequest(getToken());
        request.setId(id);
        request.setStatus(Status.IN_PROGRESS);
        getProjectEndpointClient().changeProjectStatusById(request);
    }

}
