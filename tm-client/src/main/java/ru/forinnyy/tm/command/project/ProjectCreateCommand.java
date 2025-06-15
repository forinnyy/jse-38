package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectCreateRequest;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectCreateCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-create";

    @NonNull
    private static final String DESCRIPTION = "Create new project.";

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
    public void execute() {
        System.out.println("[CREATE PROJECT]");
        System.out.println("ENTER NAME: ");
        @NonNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION: ");
        @NonNull final String description = TerminalUtil.nextLine();

        @NonNull final ProjectCreateRequest request = new ProjectCreateRequest(getToken());
        request.setName(name);
        request.setDescription(description);
        getProjectEndpointClient().createProject(request);
    }

}
