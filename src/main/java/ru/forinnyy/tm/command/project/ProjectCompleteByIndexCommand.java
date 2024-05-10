package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectCompleteByIndexCommand extends AbstractProjectCommand {

    private static final String NAME = "project-complete-by-index";

    private static final String DESCRIPTION = "Complete project by index.";

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
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        getProjectService().changeProjectStatusByIndex(index, Status.COMPLETED);
    }

}
