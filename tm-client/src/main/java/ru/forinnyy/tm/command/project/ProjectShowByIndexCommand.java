package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectGetByIndexRequest;
import ru.forinnyy.tm.dto.response.ProjectGetByIndexResponse;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectShowByIndexCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-show-by-index";

    @NonNull
    private static final String DESCRIPTION = "Show project by index.";

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
    public void execute() throws AbstractFieldException, AbstractUserException {
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NonNull final Integer index = TerminalUtil.nextNumber() -1;

        @NonNull final ProjectGetByIndexRequest request = new ProjectGetByIndexRequest();
        request.setIndex(index);
        final ProjectGetByIndexResponse response = getProjectEndpointClient().getProjectByIndex(request);
        renderProject(response.getProject());
    }

}
