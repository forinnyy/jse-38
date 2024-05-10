package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectShowByIdCommand extends AbstractProjectCommand {

    private static final String NAME = "project-show-by-id";

    private static final String DESCRIPTION = "Show project by id.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[SHOW PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = getProjectService().findOneById(id);
        showProject(project);
    }

}
