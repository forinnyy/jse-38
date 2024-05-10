package ru.forinnyy.tm.command.project;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectStartByIndexCommand extends AbstractProjectCommand {

    private static final String NAME = "project-start-by-index";

    private static final String DESCRIPTION = "Start project by index.";

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
        System.out.println("[START PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        getProjectService().changeProjectStatusByIndex(index, Status.IN_PROGRESS);
    }

}
