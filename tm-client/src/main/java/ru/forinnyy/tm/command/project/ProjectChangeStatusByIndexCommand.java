package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectChangeStatusByIndexRequest;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;

public final class ProjectChangeStatusByIndexCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-change-status-by-index";

    @NonNull
    private static final String DESCRIPTION = "Change project status by index.";

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
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NonNull final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NonNull final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);

        @NonNull final ProjectChangeStatusByIndexRequest request = new ProjectChangeStatusByIndexRequest(getToken());
        request.setIndex(index);
        request.setStatus(status);
        getProjectEndpointClient().changeProjectStatusByIndex(request);
    }

}
