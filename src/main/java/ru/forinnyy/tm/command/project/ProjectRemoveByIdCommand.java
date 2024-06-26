package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-remove-by-id";

    @NotNull
    private static final String DESCRIPTION = "Remove project by id.";

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String userId = getUserId();
        @NotNull final String id = TerminalUtil.nextLine();
        @Nullable final Project project = getProjectService().findOneById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        getProjectTaskService().removeProjectById(userId, project.getId());
    }

}
