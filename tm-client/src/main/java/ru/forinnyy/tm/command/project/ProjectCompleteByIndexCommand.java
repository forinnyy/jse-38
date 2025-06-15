package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectChangeStatusByIndexRequest;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectCompleteByIndexCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-complete-by-index";

    @NonNull
    private static final String DESCRIPTION = "Complete project by index.";

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
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NonNull final Integer index = TerminalUtil.nextNumber() - 1;

        @NonNull final ProjectChangeStatusByIndexRequest request = new ProjectChangeStatusByIndexRequest(getToken());
        request.setIndex(index);
        request.setStatus(Status.COMPLETED);
        getProjectEndpointClient().changeProjectStatusByIndex(request);
    }

}
