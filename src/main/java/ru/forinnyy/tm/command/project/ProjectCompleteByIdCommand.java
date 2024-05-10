package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectCompleteByIdCommand extends AbstractProjectCommand {

    private static final String NAME = "project-complete-by-id";

    private static final String DESCRIPTION = "Complete project by id.";

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
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        getProjectService().changeProjectStatusById(id, Status.COMPLETED);
    }

}
