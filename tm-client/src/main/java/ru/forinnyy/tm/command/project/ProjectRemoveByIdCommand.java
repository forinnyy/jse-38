package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-remove-by-id";

    @NonNull
    private static final String DESCRIPTION = "Remove project by id.";

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
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NonNull final String userId = getUserId();
        @NonNull final String id = TerminalUtil.nextLine();
        final Project project = getProjectService().findOneById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        getProjectTaskService().removeProjectById(userId, project.getId());
    }

}
