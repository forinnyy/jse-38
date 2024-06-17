package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectShowByIdCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-show-by-id";

    @NotNull
    private static final String DESCRIPTION = "Show project by id.";

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
    public void execute() throws AbstractFieldException, AbstractUserException {
        System.out.println("[SHOW PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        @Nullable final Project project = getProjectService().findOneById(userId, id);
        showProject(project);
    }

}
