package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectShowByIndexCommand extends AbstractProjectCommand {

    private static final String NAME = "project-show-by-index";

    private static final String DESCRIPTION = "Show project by index.";

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
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Project project = getProjectService().findOneByIndex(index);
        showProject(project);
    }

}
