package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectRemoveByIndexCommand extends AbstractProjectCommand {

    private static final String NAME = "project-remove-by-index";

    private static final String DESCRIPTION = "Remove project by index.";

    @Override
    public @NotNull String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[REMOVE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Project project = getProjectService().removeByIndex(index);
        final String userId = getUserId();
        getProjectTaskService().removeProjectById(userId, project.getId());
    }

}
