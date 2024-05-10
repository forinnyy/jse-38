package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    private static final String NAME = "project-remove-by-id";

    private static final String DESCRIPTION = "Remove project by id.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = getProjectService().removeById(id);
        getProjectTaskService().removeProjectById(project.getId());
    }

}
